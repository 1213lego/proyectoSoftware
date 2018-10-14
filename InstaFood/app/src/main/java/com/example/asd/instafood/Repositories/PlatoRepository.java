package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.PlatoDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Plato;

import java.util.List;

public class PlatoRepository
{
    private PlatoDao platoDao;
    private LiveData<List<Plato>> platos;

    public PlatoRepository(Application application)
    {
        platoDao=DatabaseInstafood.getInstance(application).platos();
        platos=platoDao.consultarPlatos();
    }
    public LiveData<List<Plato>> darPlatos()
    {
        return platos;
    }
    public LiveData<List<Plato>> darPlatosPorNombre(String nombre)
    {
        return platoDao.consultarPorNombre(nombre);
    }
    public void ingresarPlato(Plato plato)
    {
        new IngresarPlatoAsynTask(platoDao).execute(plato);
    }
    private static class IngresarPlatoAsynTask extends AsyncTask<Plato,Void,Void>
    {
        private PlatoDao platoDao;
        public IngresarPlatoAsynTask(PlatoDao platoDao)
        {
            this.platoDao=platoDao;
        }

        @Override
        protected Void doInBackground(Plato... platoes)
        {
            platoDao.ingresarPlato(platoes[0]);
            return null;
        }
    }

    public void eliminarPlato(Plato plato)
    {
        new EliminaPlatoAsynTask(platoDao).execute(plato);
    }
    private static class EliminaPlatoAsynTask extends AsyncTask<Plato,Void,Void>
    {
        private PlatoDao platoDao;
        public EliminaPlatoAsynTask(PlatoDao platoDao)
        {
            this.platoDao=platoDao;
        }

        @Override
        protected Void doInBackground(Plato... platoes)
        {
            platoDao.eliminarPlato(platoes[0]);
            return null;
        }
    }

    public void actualizarPlato(Plato plato)
    {
        new ActualizarPlatoAsynTask(platoDao).execute(plato);
    }
    private static class ActualizarPlatoAsynTask extends AsyncTask<Plato,Void,Void>
    {
        private PlatoDao platoDao;
        public ActualizarPlatoAsynTask(PlatoDao platoDao)
        {
            this.platoDao=platoDao;
        }

        @Override
        protected Void doInBackground(Plato... platoes)
        {
            platoDao.actualizarPlato(platoes[0]);
            return null;
        }
    }





}
