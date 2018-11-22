package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class AnuncianteViewModel extends SuperAndroidViewModel
{
    public AnuncianteViewModel(@NonNull Application application)
    {
        super(application);
    }
    public LiveData<Usuario> darLiveDataUsuario(String email)
    {
        return repository.darLiveDataUsuarioPorEmail(email);
    }
    public LiveData<Anunciante> darAnunciante(String email)
    {
        return repository.darAnunciante(email);
    }
    public LiveData<List<Restaurante>> darListaRestaurante(String email)
    {
        return repository.darRestaurantesAnunciante(email);
    }

}
