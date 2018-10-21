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

public class UsuarioActivityViewModel extends AndroidViewModel
{
    private Repository repository;

    public UsuarioActivityViewModel(@NonNull Application application)
    {
        super(application);
        repository=new Repository(application);
    }

    public void actualizar(IDto iDto)
    {
        repository.actualizar(iDto);
    }
    public void eliminar(IDto iDto)
    {
        repository.eliminar(iDto);
    }
    public void ingresar(IDto iDto)
    {
        repository.ingresar(iDto);
    }
    public LiveData<List<Restaurante>> darLiveDataRestaurantesFavoritosPorEmail(String email)
    {
        return repository.darLiveDataRestaurantesFavoritosPorEmail(email);
    }
    public LiveData<Usuario> darLiveDataUsuarioPorEmail(String email)
    {
        return repository.darLiveDataUsuarioPorEmail(email);
    }
}
