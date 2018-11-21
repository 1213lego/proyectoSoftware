package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Usuario;

public class LoginActivityViewModel extends SuperAndroidViewModel
{
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Anunciante> darAnunciante(String email)
    {
        return repository.darAnunciante(email);
    }
    public LiveData<Usuario> darUsuario(String email)
    {
        return  repository.darLiveDataUsuarioPorEmail(email);
    }
}
