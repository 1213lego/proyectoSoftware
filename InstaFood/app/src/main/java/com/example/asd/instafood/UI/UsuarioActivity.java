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
import com.example.asd.instafood.ViewModels.UsuarioActivityViewModel;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class UsuarioActivity extends AppCompatActivity
{
    private UsuarioActivityViewModel usuarioActivityViewModel;
    private String emailUsuario;
    private List<Restaurante> restauranteList;

    private RecyclerView recyclerView;
    private ImageView imageViewUsuario;
    private TextView email;
    private TextView nombre;
    private TextView apellido;
    private Button btnBuscarRes;
    private FloatingActionButton floatingActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        usuarioActivityViewModel=ViewModelProviders.of(this).get(UsuarioActivityViewModel.class);

        //Obtengo los datos que envian desde otra activity
        Intent intent=getIntent();
        emailUsuario=intent.getStringExtra("Email");

        imageViewUsuario=findViewById(R.id.imgUsuario);
        email=findViewById(R.id.txtEmail);
        nombre= findViewById(R.id.txtNombre);
        apellido= findViewById(R.id.txtApellidos);
        btnBuscarRes=(Button) findViewById(R.id.btnBuscarRestaurantes);

        floatingActionButtonAdd=findViewById(R.id.botonNuevoRestaurante);
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // para añadir restuarante a la lista de restaurantes favoritos
                //lanzar un intent abriendo la lista de restaurantes para que el usuario la seleccione y la agregue
                Toast.makeText(UsuarioActivity.this,
                        "Lanzar un activity donde pueda eelegir los resturantes favoritos de una lista",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Para el recyclerview
        recyclerView=findViewById(R.id.recyclerViewResFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        cargarUsuario();
        cargarRestaurantesFavoritosRecyclerview();

    }

    private void cargarRestaurantesFavoritosRecyclerview()
    {
        final RestauranteFavoritoAdapter restauranteAdapter= new RestauranteFavoritoAdapter();
        recyclerView.setAdapter(restauranteAdapter);
        usuarioActivityViewModel.darLiveDataRestaurantesFavoritosPorEmail(emailUsuario).observe(this, new Observer<List<Restaurante>>()
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
                RestauranteFavorito restauranteFavorito= new RestauranteFavorito(restaurante.getRestauranteId(),emailUsuario);
                usuarioActivityViewModel.eliminar(restauranteFavorito);
                Toast.makeText(UsuarioActivity.this, "Restaurante Favorito Eliminado", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        restauranteAdapter.setOnItemClickListener(new RestauranteFavoritoAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(Restaurante restaurante)
            {
                // lanzar unn intent con la vista de un restaurante mas detallada, en esta vista deben estan los platos del restaurante
                Toast.makeText(UsuarioActivity.this, "Lanzar vista del restaurante con los platos", Toast.LENGTH_SHORT).show();
                byte [] aaa= restaurante.getImageArray();
                if (aaa != null) {
                    Toast.makeText(UsuarioActivity.this, " " + aaa.length, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UsuarioActivity.this, "no hay imagne", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cargarUsuario()
    {
        usuarioActivityViewModel.darLiveDataUsuarioPorEmail(emailUsuario).observe(this, new Observer<Usuario>()
        {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                if(usuario!=null)
                {
                    email.setText(usuario.getEmail());
                    nombre.setText(usuario.getNombre());
                    apellido.setText(usuario.getApellido());
                }
            }
        });
    }
    private void cargarValoresUsuario(Usuario usuario)
    {
    }

    private void buscarRestaurante(View view)
    {
        if(view.getId()==R.id.btnBuscarRestaurantes)
        {
            Intent intent= new Intent(this,RestaurantesMapsActivity.class);
            startActivity(intent);
        }
    }
}
