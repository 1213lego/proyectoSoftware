package com.example.asd.instafood.RecyclerViewsAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asd.instafood.R;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class RestauranteFavoritoAdapter extends RecyclerView.Adapter<RestauranteFavoritoAdapter.RestauranteHolder>
{
    private List<Restaurante> restaurantes= new ArrayList<>();


    @NonNull
    @Override
    public RestauranteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.restaurante_item, viewGroup, false);
        return new RestauranteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteHolder restauranteHolder, int i)
    {
        Restaurante restaurante= restaurantes.get(i);
        restauranteHolder.titulo.setText(restaurante.getNombreRestaurante());
        restauranteHolder.descripcion.setText(restaurante.getDescripcionRestaurante());
        restauranteHolder.telefono.setText(restaurante.getTelefonoRestaurante());
        restauranteHolder.direccion.setText(restaurante.getDireccionRestaurante());
    }

    @Override
    public int getItemCount()
    {
        return restaurantes.size();
    }

    public void cambiarRestaurantes(List<Restaurante> restauranteList)
    {
        restaurantes=restauranteList;
        notifyDataSetChanged();
    }
    class RestauranteHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo;
        private TextView descripcion;
        private TextView telefono;
        private TextView direccion;

        public RestauranteHolder(@NonNull View itemView)
        {
            super(itemView);
            titulo=(TextView)itemView.findViewById(R.id.tituloRestaurante);
            descripcion=(TextView) itemView.findViewById(R.id.descripcionRestaurante);
            telefono=(TextView) itemView.findViewById(R.id.telefonoRestaurante);
            direccion=(TextView) itemView.findViewById(R.id.direccionRestaurante);
        }
    }
}
