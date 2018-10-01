package com.example.asd.instafood;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener,
        GoogleMap.OnInfoWindowClickListener , GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener , LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Button btnBuscar;
    private EditText txtLocation;
    private Marker markerCurrentLocation;
    public static final int REQUEST_LOCATION_CODE=99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txtLocation=(EditText)findViewById(R.id.txtLocation);
        btnBuscar=(Button)findViewById(R.id.btnSearch);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onClick(View view)
    {
        if(view.getId()==R.id.btnSearch)
        {
            String location= txtLocation.getText().toString();
            List<Address> addressList= null;
            MarkerOptions markerOptions= new MarkerOptions();
            if(!location.equals(""))
            {
                Geocoder geocoder= new Geocoder(this);
                try {
                    addressList=geocoder.getFromLocationName(location,10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int i=0; i<addressList.size(); i++)
                {
                    Address addressActual=addressList.get(i);
                    LatLng latLng= new LatLng(addressActual.getLatitude(),addressActual.getLongitude());
                    markerOptions.position(latLng);
                    markerOptions.title("Tu resultado de busqueda");
                    mMap.addMarker(markerOptions);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                }
            }

        }
    }

    public boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                )
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            return false;

        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission is granted
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED)
                    {
                        if(client==null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permisos denegados", Toast.LENGTH_LONG);
                }
                return;
        }
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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setIndoorEnabled(true);
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);//para cambiar el tipo de mapa

     /*   if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }*/
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        LatLng ibague = new LatLng(4.449682, -75.199003);
        mMap.addMarker(new MarkerOptions().position(ibague).draggable(true).title("Marker in Ibague").
                snippet("Ciudad Musical").icon(BitmapDescriptorFactory.fromResource(R.drawable.ibagueimagen)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ibague));

        //Le decimos a mapa que este a la escucha de los click sobre los marker
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnInfoWindowClickListener(this);

    }
    protected  synchronized void buildGoogleApiClient()
    {
        client=new GoogleApiClient.Builder(this).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).
                addApi(LocationServices.API).build();
        client.connect();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "Latitud: "+ marker.getPosition().latitude + " Longitud: " + marker.getPosition().longitude + " TituloMarker: " + marker.getTitle(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        String newTitle=String.format(Locale.getDefault(),
                getString(R.string.marker_detail_latlng),
                marker.getPosition().latitude,
                marker.getPosition().longitude);
        setTitle(newTitle);
    }
    @Override
    public void onMarkerDragEnd(Marker marker) {

        setTitle(R.string.title_maps);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        DialogFragment.newInstance(marker.getTitle(),marker.getSnippet()).show(getSupportFragmentManager(),null);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest= new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
                )
        {

            LocationServices.FusedLocationApi.requestLocationUpdates(client,locationRequest,this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation=location;
        if(markerCurrentLocation!=null)
        {
            markerCurrentLocation.remove();
        }
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions= new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Aqui Estoy");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerCurrentLocation=mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        if(client!=null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }

}
