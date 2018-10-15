package com.example.asd.instafood;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asd.instafood.ViewModels.UsuarioViewModel;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Usuario;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity
{

    private Button btnMap, btnvistaRestaurante,btnRegistrarse, btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=(Button) findViewById(R.id.btnMap);
        btnIniciarSesion = (Button)findViewById(R.id.btnIniciarSesion);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);
    }

    public void openMap(View view)
    {
        if(view.getId()==R.id.btnMap)
        {
           Intent intent=new Intent(this,RestaurantesMapsActivity.class);
           startActivity(intent);
        }
        else if(view.getId()==R.id.btnVistaRes)
        {
            /*UsuarioViewModel usuarioViewModel= ViewModelProviders.of(this).get(UsuarioViewModel.class);
            usuarioViewModel.darUsuarios().observe(this, new Observer<List<Usuario>>() {
                @Override
                public void onChanged(@Nullable List<Usuario> notes) {


                }
            });*/
            Intent intent=new Intent(this,UsuarioActivity.class);
            intent.putExtra("Email","usuario1@usuario1.com");
            startActivity(intent);
        }
    }

    public void toast(String mensaje)
    {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }


    public void openLogin(View view)
    {
        if(view.getId() == R.id.btnIniciarSesion)
        {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);

        }

    }

    public void openRegisterForm(View view)
    {
        if(view.getId() == R.id.btnRegistrarse)
        {
            Intent intent=new Intent(this,RegistroUsuarioActivity.class);
            startActivity(intent);
        }
    }

}
