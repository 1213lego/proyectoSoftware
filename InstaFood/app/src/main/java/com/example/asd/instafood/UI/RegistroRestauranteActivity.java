package com.example.asd.instafood.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.ViewModels.RegistroRestauranteViewModel;
import com.example.asd.instafood.db.models.TipoComida;

import java.util.ArrayList;
import java.util.List;

public class RegistroRestauranteActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> comidas;


    //TODO: AGREGAR CONEXIÓN A LA DB.

    private RegistroRestauranteViewModel registroRestauranteViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);
        comidas = new ArrayList();
        registroRestauranteViewModel=ViewModelProviders.of(this).get(RegistroRestauranteViewModel.class);
        spinner = (Spinner) findViewById(R.id.listaRestaurantes);
        comidas.add("Seleccione");
        registroRestauranteViewModel.darLiveDataTiposComida().observe(this, new Observer<List<TipoComida>>()
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

    public void openImageChooser(View view)
    {
        if(view.getId()==R.id.btnExaminar)
        {



           //todo abrir galeria

        }
    }

    public void openMap(View view) {
        if (view.getId() == R.id.btnUbicarRestaurante)
        {
            Intent intent = new Intent(this, RestaurantesMapsActivity.class);
            startActivity(intent);
            //TODO: ABRIR LA VISTA DE RESTAURANTE QUE PERMITA AL USUARIO UBICAR UN PUNTERO EN EL MAPA Y RECOGER SU DIRECCION.
        }
    }
    public void openRegistroPlato(View view)
    {
        if(view.getId() == R.id.btnSiguiente)
        {
            Intent intent=new Intent(this,RegistroPlatoActivity.class);
            startActivity(intent);
        }
    }





}
