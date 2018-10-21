package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.Repository;
import com.example.asd.instafood.db.models.TipoComida;

import java.util.List;

public class RegistroRestauranteViewModel extends AndroidViewModel
{
    private Repository repository;
    public RegistroRestauranteViewModel(@NonNull Application application)
    {
        super(application);
        repository=new Repository(application);
    }
    public LiveData<List<TipoComida>> darLiveDataTiposComida()
    {
        return repository.darLiveDataTiposComida();
    }
}
