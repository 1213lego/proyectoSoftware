package com.example.asd.instafood.UI;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RestaurantesMapsViewModel;
import com.example.asd.instafood.db.models.Restaurante;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class RestaurantesMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
        , GoogleMap.OnInfoWindowCloseListener, GoogleMap.OnInfoWindowLongClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private boolean locationEnable;

    private RestaurantesMapsViewModel restaurantesMapsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationEnable = false;
        setContentView(R.layout.activity_restaurantes_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        restaurantesMapsViewModel = ViewModelProviders.of(this).get(RestaurantesMapsViewModel.class);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);
        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        crearMaker();
        permisos();

    }

    public void permisos() {
        int permissionFineLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionFineLocation != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            }


        } else {
            locationEnable = true;
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    locationEnable = true;
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                    != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                } 
                else 
                    {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                        locationEnable=false;
                    }
                return;
            }
        }
    }

    public void crearMaker()
    {
        // Add a marker in Sydney and move the camera
        LatLng unibague = new LatLng(4.449682, -75.199003);
        MarkerOptions markerOptionsUnibague=new MarkerOptions().position(unibague);
        markerOptionsUnibague.snippet("Universidad de ibague "+ "\n"+ "Ing de sistemas");
        markerOptionsUnibague.title("Universidadad de Ibague");
        markerOptionsUnibague.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconrestaurante));
        Marker markerUnibague= mMap.addMarker(markerOptionsUnibague);
        markerOptionsUnibague.title("prueba");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unibague));


        LatLng toroRojo = new LatLng(4.4490674421427485, -75.200181);
        MarkerOptions markerOptionsToroRojo= new MarkerOptions().position(toroRojo);
        markerOptionsToroRojo.snippet("Venta de empanadas horneadas");
        markerOptionsToroRojo.title("Empanadas Toro Rojo");
        markerOptionsToroRojo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker markerToroRojo= mMap.addMarker(markerOptionsToroRojo);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toroRojo));

        restaurantesMapsViewModel.darLiveDataRestaurantes().observe(this, new Observer<List<Restaurante>>() {
            @Override
            public void onChanged(@Nullable List<Restaurante> restauranteList) {
                for(int i=0 ; i<restauranteList.size();i++)
                {
                    LatLng latLng= new LatLng(restauranteList.get(i).getLatitud(),restauranteList.get(i).getLongitud());
                    MarkerOptions markerOptions= new MarkerOptions().position(latLng);
                    markerOptions.title(restauranteList.get(i).getNombreRestaurante());
                    markerOptions.snippet(restauranteList.get(i).getDescripcionRestaurante());
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconrestaurante));
                    mMap.addMarker(markerOptions);
                }
            }
        });
        int radius = 1000;

        CircleOptions circleOptions = new CircleOptions()
                .center(toroRojo)
                .radius(radius)
                .strokeColor(Color.parseColor("#0D47A1"))
                .strokeWidth(4)
                .fillColor(Color.parseColor("#AF4046FF"));
        Circle circle = mMap.addCircle(circleOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toroRojo, 17));

    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        Toast.makeText(this, "onMarkerClick",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Toast.makeText(this, "onInfoWindowClick",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInfoWindowClose(Marker marker)
    {
        Toast.makeText(this, "onInfoWindowClose",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInfoWindowLongClick(Marker marker)
    {
        Toast.makeText(this, "onInfoWindowLongClick",Toast.LENGTH_LONG).show();
    }


    /** Demonstrates customizing the info window and/or its contents. */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;
        //private final View mContents;

        CustomInfoWindowAdapter()
        {
            mWindow = getLayoutInflater().inflate(R.layout.info_restaurante_mapa_window, null);
            //mContents = getLayoutInflater().inflate(R.layout.info_restaurante_mapa_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            render(marker, mWindow);
            return mWindow;
        }

        private void render(Marker marker, View view)
        {

            ((ImageView) view.findViewById(R.id.badge)).setImageResource(R.drawable.iconrestaurante);

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null)
            {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            }
            else
            {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12)
            {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, snippet.length(), 0);
                snippetUi.setText(snippetText);
            }
            else
            {
                snippetUi.setText("");
            }
        }
    }
    /**
     * Demonstrates converting a {@link Drawable} to a {@link BitmapDescriptor},
     * for use as a marker icon.
     */
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color)
    {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
