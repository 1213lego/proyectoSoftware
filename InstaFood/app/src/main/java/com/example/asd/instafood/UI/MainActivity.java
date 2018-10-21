package com.example.asd.instafood.UI;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
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

    private Button btnMap, btnvistaRestaurante,btnRegistrarse, btnIniciarSesion;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=(Button) findViewById(R.id.btnMap);
        btnIniciarSesion = (Button)findViewById(R.id.btnIniciarSesion);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);
        textView=findViewById(R.id.textCantidad);
        Repository repository= new Repository(getApplication());

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
            Intent intent=new Intent(this,RegistroRestauranteActivity.class);
            intent.putExtra("Email","usuario1@usuario1.com");
            startActivity(intent);
        }
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
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
