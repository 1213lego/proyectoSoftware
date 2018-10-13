package com.example.asd.instafood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegistroRestauranteActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> tipoComidas;


    //TODO: AGREGAR CONEXIÓN A LA DB.


    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_restaurante);


        spinner = (Spinner) findViewById(R.id.spinnerTipoComida);
        tipoComidas = new ArrayList();

        tipoComidas.add("Seleccione");


        //TODO: REALIZAR LA CONSULTA EN LA TABLA TIPOCOMIDAS Y CARGARLA EN EL ARRAYLIST


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoComidas);

        spinner.setAdapter(adaptador);
    }



}
