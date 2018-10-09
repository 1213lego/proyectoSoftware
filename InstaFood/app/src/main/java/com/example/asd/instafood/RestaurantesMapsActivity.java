package com.example.asd.instafood;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantesMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
    , GoogleMap.OnInfoWindowCloseListener, GoogleMap.OnInfoWindowLongClickListener
{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurantes_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(false);
        crearMaker();
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);
        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

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
        markerOptionsUnibague.title("prueb");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unibague));


        LatLng toroRojo = new LatLng(4.4490674421427485, -75.200181);
        MarkerOptions markerOptionsToroRojo= new MarkerOptions().position(toroRojo);
        markerOptionsToroRojo.snippet("Venta de empanadas horneadas");
        markerOptionsToroRojo.title("Empanadas Toro Rojo");
        markerOptionsToroRojo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        Marker markerToroRojo= mMap.addMarker(markerOptionsToroRojo);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toroRojo));
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
