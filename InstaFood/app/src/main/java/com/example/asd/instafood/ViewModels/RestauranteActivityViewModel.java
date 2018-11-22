package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class RestauranteActivityViewModel extends SuperAndroidViewModel
{

    public RestauranteActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Plato>> consultarPlatosRestaurante(int id)
    {
        return repository.consultarPlatosRestaurante(id);
    }
    public LiveData<Restaurante> consultarRestauranteID(int id)
    {
        return repository.consultarRestauranteID(id);
    }
}
