package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.AnuncianteDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class AnuncianteRepository
{
    private LiveData<List<Anunciante>> anunciantes;
    private AnuncianteDao anuncianteDao;

    public AnuncianteRepository(Application application)
    {
        DatabaseInstafood databaseInstafood= DatabaseInstafood.getInstance(application);
        anuncianteDao=databaseInstafood.anunciantes();
        anunciantes=anuncianteDao.consultarAnunciantes();
    }
    public LiveData<List<Restaurante>>  darRestaurantesAnunciante(int id)
    {
        return  anuncianteDao.darListaResturantesAnunciante(id);
    }
    public void acualizarAnunciante(Anunciante anunciante)
    {
        new ActualizarAnuncianteAsyncTask(anuncianteDao).execute(anunciante);
    }
    public void ingresarAnunciante(Anunciante anunciante)
    {
        new IngresarAnuncianteAsyncTask(anuncianteDao).execute(anunciante);
    }
    public LiveData<List<Anunciante>> darAnunciantes()
    {
        return anuncianteDao.consultarAnunciantes();
    }
    public LiveData<Anunciante> darAnunciantesEmail(String email)
    {
        return anuncianteDao.consultarAnuncianteEmail(email);
    }

    private static class IngresarAnuncianteAsyncTask extends AsyncTask<Anunciante,Void,Void>
    {
        private AnuncianteDao anuncianteDao;

        public IngresarAnuncianteAsyncTask(AnuncianteDao anuncianteDao)
        {
            this.anuncianteDao=anuncianteDao;
        }

        @Override
        protected Void doInBackground(Anunciante... anunciantes)
        {
            anuncianteDao.ingresarAnunciante(anunciantes[0]);
            return null;
        }
    }
    private static class ActualizarAnuncianteAsyncTask extends AsyncTask<Anunciante, Void,Void>
    {
        private AnuncianteDao anuncianteDao;
        public ActualizarAnuncianteAsyncTask(AnuncianteDao anuncianteDao)
        {
            this.anuncianteDao=anuncianteDao;
        }

        @Override
        protected Void doInBackground(Anunciante... anunciantes) {
            anuncianteDao.actualizarAnunciante(anunciantes[0]);
            return null;
        }
    }

}
