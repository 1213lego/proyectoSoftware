package com.example.asd.instafood.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.AnuncianteViewModel;

public class AnuncianteActivity extends AppCompatActivity
{

    private AnuncianteViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunciante);
        viewModel=ViewModelProviders.of(this).get(AnuncianteViewModel.class);
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
