package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.UsuarioRepository;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel
{
    private UsuarioRepository usuarioRepository;
    private LiveData<List<Usuario>> usuarios;

    public UsuarioViewModel(@NonNull Application application)
    {
        super(application);
        usuarioRepository=new UsuarioRepository(application);
        usuarios=usuarioRepository.darUsuarios();
    }

    public LiveData<List<Usuario>> darUsuarios()
    {
        return usuarios;
    }
    public LiveData<Usuario> darUsuarioPorEmail(String email)
    {
        return usuarioRepository.darUsuarioEmail(email);
    }
    public void actualizarUsuario(Usuario usuario)
    {
        usuarioRepository.actualizarUsuario(usuario);
    }
    public void ingresarUsuario(Usuario usuario)
    {
        usuarioRepository.ingresarUsuario(usuario);
    }
}
