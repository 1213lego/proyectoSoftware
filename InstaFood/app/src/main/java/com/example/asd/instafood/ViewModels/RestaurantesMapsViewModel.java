package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.Repository;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class RestaurantesMapsViewModel extends SuperAndroidViewModel
{

    public RestaurantesMapsViewModel(@NonNull Application application)
    {
        super(application);
    }
    public LiveData<List<Restaurante>> darLiveDataRestaurantes()
    {
        return repository.darLiveDataRestaurantes();
    }
    
}
