package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.Repositories.RestauranteRepository;
import java.util.List;

public class RestauranteViewModel extends AndroidViewModel
{
    private RestauranteRepository restauranteRespository;
    private LiveData<List<Restaurante>> restaurantes;
    public RestauranteViewModel(@NonNull Application application)
    {
        super(application);
        restauranteRespository=new RestauranteRepository(application);
        restaurantes=restauranteRespository.darRestaurantes();
    }
    public LiveData<List<Restaurante>> darRestaurantes()
    {
        return  restaurantes;
    }
    public LiveData<List<Restaurante>> darRestaurantePorNombre(String nomnre)
    {
        return restauranteRespository.darRestaurantePorNombre(nomnre);
    }
    public void eliminarRestaurante(Restaurante restaurante)
    {
        restauranteRespository.eliminarRestaurante(restaurante);
    }
    public void ingresarRestaurante(Restaurante restaurante)
    {
        restauranteRespository.ingresarRestaurante(restaurante);
    }
    public void actualizarRestaurante(Restaurante restaurante)
    {
        restauranteRespository.actualizarRestaurante(restaurante);
    }
}
