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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asd.instafood.R;
import com.example.asd.instafood.RecyclerViewsAdapters.RestauranteFavoritoAdapter;
import com.example.asd.instafood.ViewModels.AnuncianteViewModel;
import com.example.asd.instafood.ViewModels.UsuarioActivityViewModel;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class AnuncianteActivity extends AppCompatActivity
{
    public static final int REQUEST_CODE_CREAR_RESTAURANTE=3;
    private AnuncianteViewModel viewModel;
    private String emailUsuario;
    private List<Restaurante> restauranteList;
    private RecyclerView recyclerView;
    private ImageView imageViewUsuario;
    private TextView nombre;
    private TextView apellido;
    private Button btnBuscarRes;
    private FloatingActionButton floatingActionButtonAdd;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunciante);
        viewModel=ViewModelProviders.of(this).get(AnuncianteViewModel.class);
        //Obtengo los datos que envian desde otra activity
        Intent intent=getIntent();
        emailUsuario=intent.getStringExtra("Email");
        imageViewUsuario=findViewById(R.id.imgUsuario);
        nombre= findViewById(R.id.txtNombre);
        apellido= findViewById(R.id.txtApellidos);
        btnBuscarRes=(Button) findViewById(R.id.btnBuscarRestaurantes);

        floatingActionButtonAdd=findViewById(R.id.botonNuevoRestaurante);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnuncianteActivity.this,RegistroRestauranteActivity.class);
                intent.putExtra("Email",emailUsuario);
                intent.putExtra("Id",id);
                startActivityForResult(intent,REQUEST_CODE_CREAR_RESTAURANTE);
            }
        });
        viewModel.darAnunciante(emailUsuario).observe(this, new Observer<Anunciante>() {
            @Override
            public void onChanged(@Nullable Anunciante anunciante) {
                if(anunciante!=null)
                {
                    id=anunciante.getIdAnunciante();
                }
            }
        });
        //Para el recyclerview
        recyclerView=findViewById(R.id.recyclerViewMisRestaurantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        cargarAnunciante();
        cargarRestaurantesMisRecyclerview();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
    }

    private void cargarRestaurantesMisRecyclerview()
    {
        final RestauranteFavoritoAdapter restauranteAdapter= new RestauranteFavoritoAdapter();
        recyclerView.setAdapter(restauranteAdapter);
        viewModel.darListaRestaurante(emailUsuario).observe(this, new Observer<List<Restaurante>>()
        {
            @Override
            public void onChanged(@Nullable List<Restaurante> restaurantes)
            {
                restauranteAdapter.cambiarRestaurantes(restaurantes);
                restauranteList=restaurantes;
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
                Restaurante restaurante= restauranteAdapter.darRestaurante(viewHolder.getAdapterPosition());
                viewModel.eliminar(restaurante);
                Toast.makeText(AnuncianteActivity.this, "Restaurante Favorito Eliminado", Toast.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(recyclerView);

        restauranteAdapter.setOnItemClickListener(new RestauranteFavoritoAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Restaurante restaurante)
            {
                // lanzar un intent con la vista de un restaurante
                Toast.makeText(AnuncianteActivity.this, "Lanzar vista del restaurante con los platos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarAnunciante()
    {
        viewModel.darLiveDataUsuario(emailUsuario).observe(this, new Observer<Usuario>()
        {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                if(usuario!=null)
                {
                    nombre.setText(usuario.getNombre());
                    apellido.setText(usuario.getApellido());
                    if(usuario.getImageArray()!=null)
                    {
                        Bitmap bitmap= BitmapFactory.decodeByteArray(usuario.getImageArray(),0,usuario.getImageArray().length);
                        imageViewUsuario.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }
}
