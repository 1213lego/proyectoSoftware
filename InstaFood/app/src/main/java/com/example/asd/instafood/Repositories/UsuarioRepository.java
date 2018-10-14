package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.UsuarioDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Usuario;

import java.util.List;

public class UsuarioRepository
{
    private UsuarioDao usuarioDao;
    private LiveData<List<Usuario>> usuarios;

    public UsuarioRepository(Application application)
    {
        usuarioDao=DatabaseInstafood.getInstance(application).usuarios();
        usuarios=usuarioDao.consultarTodosLosUsuario();
    }


    public LiveData<List<Usuario>> darUsuarios()
    {
        return usuarios;
    }
    public LiveData<Usuario> darUsuarioEmail(String email)
    {
        return usuarioDao.consultarPorEmail(email);
    }
    public void ingresarUsuario(Usuario usuario)
    {
        new IngresarUsuarioAsyncTask(usuarioDao).execute(usuario);
    }
    public void actualizarUsuario(Usuario usuario)
    {
        new ActualizarAsynTask(usuarioDao).execute(usuario);
    }


    private static class IngresarUsuarioAsyncTask extends AsyncTask<Usuario, Void, Void>
    {
        private UsuarioDao usuarioDao;

        private IngresarUsuarioAsyncTask(UsuarioDao usuarioDao)
        {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuario)
        {
            usuarioDao.ingresarUsuario(usuario[0]);
            return null;
        }
    }

    private static class ActualizarAsynTask extends AsyncTask<Usuario, Void, Void>
    {
        private UsuarioDao usuarioDao;

        private ActualizarAsynTask(UsuarioDao usuarioDao)
        {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuario)
        {
            usuarioDao.actualizarUsuario(usuario[0]);
            return null;
        }
    }

}
