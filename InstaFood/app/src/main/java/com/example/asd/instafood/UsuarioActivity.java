package com.example.asd.instafood;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asd.instafood.ViewModels.RestauranteFavoritoViewModel;
import com.example.asd.instafood.ViewModels.UsuarioViewModel;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class UsuarioActivity extends AppCompatActivity
{
    private UsuarioViewModel usuarioViewModel;
    private RestauranteFavoritoViewModel restauranteFavoritoViewModel;
    private String emailUsuario;

    private RecyclerView recyclerView;
    private ImageView imageViewUsuario;
    private TextView email;
    private TextView nombre;
    private TextView apellido;
    private Button btnBuscarRes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        usuarioViewModel= ViewModelProviders.of(this).get(UsuarioViewModel.class);
        restauranteFavoritoViewModel=ViewModelProviders.of(this).get(RestauranteFavoritoViewModel.class);

        Intent intent=getIntent();
        emailUsuario=intent.getStringExtra("Email");
        Toast.makeText(this,emailUsuario,Toast.LENGTH_LONG).show();

        recyclerView=findViewById(R.id.recyclerViewResFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        imageViewUsuario=findViewById(R.id.imgUsuario);
        email=findViewById(R.id.txtEmail);
        nombre= findViewById(R.id.txtNombre);
        apellido= findViewById(R.id.txtApellidos);
        btnBuscarRes=(Button) findViewById(R.id.btnBuscarRestaurantes);
       
        cargarUsuario(emailUsuario);
        cargarRestaurantesFavoritos(emailUsuario);

    }

    private void cargarRestaurantesFavoritos(String emailUsuario)
    {
        final RestauranteAdapter restauranteAdapter= new RestauranteAdapter();
        recyclerView.setAdapter(restauranteAdapter);
        restauranteFavoritoViewModel.darRestaurantesFavoritosEmail(emailUsuario).observe(this, new Observer<List<Restaurante>>()
        {
            @Override
            public void onChanged(@Nullable List<Restaurante> restaurantes)
            {
                for(int i=0;i<restaurantes.size();i++)
                {
                    //cargar recyclerView
                    restauranteAdapter.cambiarRestaurantes(restaurantes);
                }
            }
        });
    }

    private void cargarUsuario(String correo)
    {
        usuarioViewModel.darUsuarioPorEmail(correo).observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                cargarValoresUsuario(usuario);
            }
        });
    }
    public void cargarValoresUsuario(Usuario usuario)
    {
        email.setText(usuario.getEmail());
        nombre.setText(usuario.getNombre());
        apellido.setText(usuario.getApellido());
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
