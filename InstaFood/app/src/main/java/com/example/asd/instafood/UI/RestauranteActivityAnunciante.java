package com.example.asd.instafood.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.RecyclerViewsAdapters.PlatosAdapter;
import com.example.asd.instafood.ViewModels.RestauranteActivityViewModel;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class RestauranteActivityAnunciante extends AppCompatActivity
{
    public static  final  int REQUEST_CODE_CREAR_PlATO=4;
    private int idRestaurante;
    private TextView labName;
    private TextView labDescripcion;
    private ImageView imageView;
    private RestauranteActivityViewModel viewModel;
    private List<Plato> platoList;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButtonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_anunciante);
        idRestaurante=getIntent().getIntExtra("Id",-1);
        viewModel=ViewModelProviders.of(this).get(RestauranteActivityViewModel.class);
        recyclerView=findViewById(R.id.recyclerViewPlatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        labName=findViewById(R.id.labName);
        labDescripcion=findViewById(R.id.labDesEditable);
        imageView=findViewById(R.id.imgLogoRest);
        floatingActionButtonAdd=findViewById(R.id.botonNuevoPlato);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RestauranteActivityAnunciante.this,RegistroPlatoActivity.class);
                intent.putExtra("Id",idRestaurante);
                startActivityForResult(intent,REQUEST_CODE_CREAR_PlATO);
            }
        });
        cargarInfoRestaurante();
        cargarPlatosRecyclerView();
    }

    private void cargarPlatosRecyclerView()
    {
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
                Toast.makeText(RestauranteActivityAnunciante.this, "lanzar vista plato", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
    }
    private void cargarInfoRestaurante()
    {
        viewModel.consultarRestauranteID(idRestaurante).observe(this, new Observer<Restaurante>() {
            @Override
            public void onChanged(@Nullable Restaurante restaurante)
            {
                if(restaurante!=null)
                {
                    labDescripcion.setText(restaurante.getDescripcionRestaurante());
                    labName.setText(restaurante.getNombreRestaurante());
                    if(restaurante.getImageArray()!=null)
                    {
                        Bitmap bitmap= BitmapFactory.decodeByteArray(restaurante.getImageArray(),0,restaurante.getImageArray().length);
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }
}
