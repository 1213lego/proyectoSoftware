package com.example.asd.instafood.UI;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asd.instafood.R;
import com.example.asd.instafood.Repositories.Repository;
public class MainActivity extends AppCompatActivity
{
    private static final int MY_PERMISSIONS_REQUEST_UBICATION= 1;
    private Button btnMap, btnvistaRestaurante,btnRegistrarse, btnIniciarSesion;
   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=(Button) findViewById(R.id.btnVistaRestaurante);
        btnIniciarSesion = (Button)findViewById(R.id.btnIniciarSesion);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_UBICATION);
    }
    public void openMap(View view)
    {
        if(view.getId()==R.id.btnVistaRestaurante)
        {
           Intent intent=new Intent(this,MapsActivity.class);
           startActivity(intent);

        }
    }



    public void openLogin(View view)
    {
        if(view.getId() == R.id.btnIniciarSesion)
        {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }

    }



}
