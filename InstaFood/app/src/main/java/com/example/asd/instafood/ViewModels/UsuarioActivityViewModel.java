package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.Repository;
import com.example.asd.instafood.db.models.IDto;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class UsuarioActivityViewModel extends SuperAndroidViewModel
{
    public UsuarioActivityViewModel(@NonNull Application application)
    {
        super(application);
    }
    public LiveData<List<Restaurante>> darLiveDataRestaurantesFavoritosPorEmail(String email)
    {
        return repository.darLiveDataRestaurantesFavoritosPorEmail(email);
    }
    public LiveData<Usuario> darLiveDataUsuarioPorEmail(String email)
    {
        return repository.darLiveDataUsuarioPorEmail(email);
    }
    public LiveData<List<Restaurante>> darLiveDataRestaurantes()
    {
        return repository.darLiveDataRestaurantes();
    }
}
