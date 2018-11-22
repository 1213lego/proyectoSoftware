package com.example.asd.instafood.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RestauranteActivityViewModel;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class RestauranteActivity extends AppCompatActivity
{
    private int idRestaurante;
    private RestauranteActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        idRestaurante=getIntent().getIntExtra("Id",-1);
        viewModel=ViewModelProviders.of(this).get(RestauranteActivityViewModel.class);


        cargarInfoRestaurante();
        cargarPlatosRecyclerView();
    }

    private void cargarPlatosRecyclerView()
    {
        viewModel.consultarPlatosRestaurante(idRestaurante).observe(this, new Observer<List<Plato>>() {
            @Override
            public void onChanged(@Nullable List<Plato> platoes) {

            }
        });
    }
    private void cargarInfoRestaurante()
    {
        viewModel.consultarRestauranteID(idRestaurante).observe(this, new Observer<Restaurante>() {
            @Override
            public void onChanged(@Nullable Restaurante restaurante) {
                
            }
        });
    }
}
