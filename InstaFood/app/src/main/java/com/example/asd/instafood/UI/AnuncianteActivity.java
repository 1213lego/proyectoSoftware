package com.example.asd.instafood.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asd.instafood.R;

public class AnuncianteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunciante);
        Intent intent=getIntent();
    }

    public void openRegister(View view)
    {
        if(view.getId() == R.id.btnAgregarRestaurante)
        {
            Intent intent = new Intent(this, RegistroRestauranteActivity.class);
            startActivity(intent);
        }
    }



}
