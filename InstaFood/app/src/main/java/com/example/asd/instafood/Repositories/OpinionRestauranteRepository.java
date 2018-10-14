package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.OpinionRestauranteDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.OpinionRestaurante;

import java.util.List;

public class OpinionRestauranteRepository
{
    private OpinionRestauranteDao opnionRestauranteDao;
    private LiveData<List<OpinionRestaurante>> opiniones;

    public OpinionRestauranteRepository(Application application)
    {
        opnionRestauranteDao=DatabaseInstafood.getInstance(application).opnionRestaurante();
        opiniones=opnionRestauranteDao.consultarOpiniones();
    }
    public LiveData<List<OpinionRestaurante>> darOpiniones()
    {
        return opiniones;
    }
    public LiveData<List<OpinionRestaurante>> opinionesPorEmail(String email)
    {
        return opnionRestauranteDao.consultarOpinionesPorEmail(email);
    }
    public LiveData<List<OpinionRestaurante>> opinionesPorRestaurante(String nombre)
    {
        return opnionRestauranteDao.consultarOpinionesPorRestaurante(nombre);
    }

    public void ingresarOpinionRestaurante(OpinionRestaurante opinionRestaurante)
    {
        new IngresarOpinionRestaurante(opnionRestauranteDao).execute(opinionRestaurante);
    }
    private static class IngresarOpinionRestaurante extends AsyncTask<OpinionRestaurante,Void,Void>
    {
        private OpinionRestauranteDao opinionRestauranteDao;
        public IngresarOpinionRestaurante(OpinionRestauranteDao opinionRestauranteDao)
        {
            this.opinionRestauranteDao=opinionRestauranteDao;
        }

        @Override
        protected Void doInBackground(OpinionRestaurante... opinionRestaurantes)
        {
            opinionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurantes[0]);
            return null;
        }
    }
    public void actualizarOpinionRestaurante(OpinionRestaurante opinionRestaurante)
    {
        new ActualizarOpinionRestaurante(opnionRestauranteDao).execute(opinionRestaurante);
    }
    private static class ActualizarOpinionRestaurante extends AsyncTask<OpinionRestaurante,Void,Void>
    {
        private OpinionRestauranteDao opinionRestauranteDao;
        public ActualizarOpinionRestaurante(OpinionRestauranteDao opinionRestauranteDao)
        {
            this.opinionRestauranteDao=opinionRestauranteDao;
        }

        @Override
        protected Void doInBackground(OpinionRestaurante... opinionRestaurantes)
        {
            opinionRestauranteDao.actualizarOpinionRestaurante(opinionRestaurantes[0]);
            return null;
        }
    }

}
