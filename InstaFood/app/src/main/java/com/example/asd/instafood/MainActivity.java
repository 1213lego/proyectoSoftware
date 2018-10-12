package com.example.asd.instafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity

{

    private Button btnMap, btnvistaRestaurante,btnRegistrarse, btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=(Button) findViewById(R.id.btnMap);
        btnvistaRestaurante=(Button)findViewById(R.id.btnVistaRes);
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
