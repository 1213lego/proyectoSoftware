package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.TipoComidaDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.TipoComida;

import java.util.List;

public class TipoComidaRepository
{
    private TipoComidaDao tipoComidaDao;
    private LiveData<List<TipoComida>> tiposComida;

    public TipoComidaRepository(Application application)
    {
        DatabaseInstafood databaseInstafood= DatabaseInstafood.getInstance(application);
        tipoComidaDao=databaseInstafood.tipoComidas();
        tiposComida =tipoComidaDao.consultarTiposComida();
    }
    public LiveData<List<TipoComida>> darComidas()
    {
        return tiposComida;
    }
    public void ingresarTipoComida(TipoComida tipoComida)
    {
        new IngresarTipoComidaAsynTask(tipoComidaDao).execute(tipoComida);
    }

    private static class IngresarTipoComidaAsynTask extends AsyncTask<TipoComida,Void,Void>
    {
        private TipoComidaDao tipoComidaDao;
        public IngresarTipoComidaAsynTask(TipoComidaDao tipoComidaDao)
        {
            this.tipoComidaDao=tipoComidaDao;
        }

        @Override
        protected Void doInBackground(TipoComida... tipoComidas)
        {
            tipoComidaDao.ingresarTipoComida(tipoComidas[0]);
            return null;
        }

    }

    public void actualizarTipoComida(TipoComida tipoComida)
    {
        new ActualizarComidaAsyncTask(tipoComidaDao).execute(tipoComida);
    }
    private static class ActualizarComidaAsyncTask extends AsyncTask<TipoComida,Void,Void>
    {
        private TipoComidaDao tipoComidaDao;

        public ActualizarComidaAsyncTask(TipoComidaDao tipoComidaDao)
        {
            this.tipoComidaDao=tipoComidaDao;
        }

        @Override
        protected Void doInBackground(TipoComida... tipoComidas)
        {
            tipoComidaDao.actualizarTipoComida(tipoComidas[0]);
            return null;
        }
    }

    public void eliminarTipoComida(TipoComida tipoComida)
    {
        new EliminarComidaAsyncTask(tipoComidaDao).execute(tipoComida);
    }
    private static class EliminarComidaAsyncTask extends AsyncTask<TipoComida,Void,Void>
    {
        private TipoComidaDao tipoComidaDao;

        public EliminarComidaAsyncTask(TipoComidaDao tipoComidaDao)
        {
            this.tipoComidaDao=tipoComidaDao;
        }

        @Override
        protected Void doInBackground(TipoComida... tipoComidas)
        {
            tipoComidaDao.eliminar(tipoComidas[0]);
            return null;
        }
    }

}
