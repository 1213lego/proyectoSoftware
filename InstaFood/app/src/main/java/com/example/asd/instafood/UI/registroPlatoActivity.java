package com.example.asd.instafood.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asd.instafood.R;

public class RegistroPlatoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_plato);
    }

    public void openImageChooser(View view)
    {
        if(view.getId()==R.id.btnExaminar)
        {
            //todo abrir galeria

        }
    }
    public void openRegistroPlato(View view)
    {
        if(view.getId() == R.id.btnAgregarPlato)
        {
            //todo agregar el plato a la db

            Intent intent=new Intent(this,RegistroPlatoActivity.class);
            startActivity(intent);
        }
    }
    public void openAnuncianteActivity(View view)
    {
        if(view.getId() == R.id.btnFinalizar)
        {
            //todo agregar el plato a la db
            Intent intent=new Intent(this,AnuncianteActivity.class);
            startActivity(intent);
        }
    }


}
