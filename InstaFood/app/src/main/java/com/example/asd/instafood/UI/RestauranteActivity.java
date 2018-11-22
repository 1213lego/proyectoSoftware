package com.example.asd.instafood.UI;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.RecyclerViewsAdapters.PlatosAdapter;
import com.example.asd.instafood.RecyclerViewsAdapters.RestauranteFavoritoAdapter;
import com.example.asd.instafood.ViewModels.RestauranteActivityViewModel;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;

import java.util.List;

public class RestauranteActivity extends AppCompatActivity
{
    private int idRestaurante;
    private RestauranteActivityViewModel viewModel;
    private List<Plato> platoList;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        idRestaurante=getIntent().getIntExtra("Id",-1);
        viewModel=ViewModelProviders.of(this).get(RestauranteActivityViewModel.class);
        recyclerView=findViewById(R.id.recyclerViewPlatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        cargarInfoRestaurante();
        cargarPlatosRecyclerView();
    }

    private void cargarPlatosRecyclerView()
    {
        Toast.makeText(this, "id  " + idRestaurante, Toast.LENGTH_SHORT).show();
        final PlatosAdapter platosAdapter= new PlatosAdapter();
        recyclerView.setAdapter(platosAdapter);
        viewModel.consultarPlatosRestaurante(idRestaurante).observe(this, new Observer<List<Plato>>() {
            @Override
            public void onChanged(@Nullable List<Plato> platoes) {
                platosAdapter.cambiarPlatos(platoes);
                platoList=platoes;
            }
        });
        //da la funcionalidad de swipe and move a cada item del recycler view
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                Plato restaurante= platosAdapter.darPlato(viewHolder.getAdapterPosition());
                //para eliminar

            }
        }).attachToRecyclerView(recyclerView);

        platosAdapter.setOnItemClickListener(new PlatosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Plato plato) {
                //
                Toast.makeText(RestauranteActivity.this, "lanzar vista plato", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cargarInfoRestaurante()
    {
        viewModel.consultarRestauranteID(idRestaurante).observe(this, new Observer<Restaurante>() {
            @Override
            public void onChanged(@Nullable Restaurante restaurante) 
            {
                if(restaurante!=null)
                {
                    
                }
            }
        });
    }
}
