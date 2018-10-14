package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.RestaurantesFavoritosRepository;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;

import java.util.List;

public class RestauranteFavoritoViewModel extends AndroidViewModel
{
    private RestaurantesFavoritosRepository restaurantesFavoritosRepository;

    public RestauranteFavoritoViewModel(@NonNull Application application)
    {
        super(application);
        restaurantesFavoritosRepository=new RestaurantesFavoritosRepository(application);
    }
    public LiveData<List<Restaurante>> darRestaurantesFavoritosEmail(String email)
    {
        return restaurantesFavoritosRepository.darRestaurantesFavoritosEmail(email);
    }
    public void ingresarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        restaurantesFavoritosRepository.ingresarRestauranteFavorito(restauranteFavorito);
    }
    public void eliminarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        restaurantesFavoritosRepository.eliminarRestauranteFavorito(restauranteFavorito);
    }
    public void actualizarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        restaurantesFavoritosRepository.actualizarRestauranteFavorito(restauranteFavorito);
    }
}
