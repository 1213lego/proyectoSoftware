package com.example.asd.instafood;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.asd.instafood.ViewModels.TipoComidaViewModel;
import com.example.asd.instafood.db.models.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class RegistroRestauranteActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> comidas;


    //TODO: AGREGAR CONEXIÓN A LA DB.

    private TipoComidaViewModel tipoComidaViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);
        comidas = new ArrayList();
        tipoComidaViewModel=ViewModelProviders.of(this).get(TipoComidaViewModel.class);
        spinner = (Spinner) findViewById(R.id.spinnerTipoComida);
        comidas.add("Seleccione");
        tipoComidaViewModel.darTiposComida().observe(this, new Observer<List<TipoComida>>()
        {
            @Override
            public void onChanged(@Nullable List<TipoComida> tipoComidas)
            {
                for(int i=0; i<tipoComidas.size(); i++)
                {
                    comidas.add(tipoComidas.get(i).toString());
                }
            }
        });
        //TODO: REALIZAR LA CONSULTA EN LA TABLA TIPOCOMIDAS Y CARGARLA EN EL ARRAYLIST
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item, comidas);
        spinner.setAdapter(adaptador);
    }



}
