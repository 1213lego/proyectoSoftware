package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.RestauranteDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class RestauranteRepository
{
    private RestauranteDao restauranteDao;
    private LiveData<List<Restaurante>> restaurantes;

    public RestauranteRepository(Application application)
    {
        DatabaseInstafood databaseInstafood=DatabaseInstafood.getInstance(application);
        restauranteDao=databaseInstafood.restaurantes();
        restaurantes=restauranteDao.consultarRestaurantes();
    }
    public LiveData<List<Restaurante>> darRestaurantes()
    {
        return restaurantes;
    }
    public LiveData<List<Restaurante>> darRestaurantePorNombre(String nomnre)
    {
        return restauranteDao.consultarRestauranteNombre(nomnre);
    }

    public void ingresarRestaurante(Restaurante restaurante)
    {
        new IngresarRestauranteAsyncTask(restauranteDao).execute(restaurante);
    }

    private static class IngresarRestauranteAsyncTask extends AsyncTask<Restaurante,Void,Void>
    {
        private RestauranteDao restauranteDao;
        public IngresarRestauranteAsyncTask(RestauranteDao restauranteDao)
        {
            this.restauranteDao=restauranteDao;
        }

        @Override
        protected Void doInBackground(Restaurante... restaurante)
        {
            restauranteDao.ingresarRestaurante(restaurante[0]);
            return null;
        }
    }
    public void actualizarRestaurante(Restaurante restaurante)
    {
        new ActualizarRestauranteAsyncTask(restauranteDao).execute(restaurante);
    }

    private static class ActualizarRestauranteAsyncTask extends AsyncTask<Restaurante,Void,Void>
    {
        private RestauranteDao restauranteDao;
        public ActualizarRestauranteAsyncTask(RestauranteDao restauranteDao)
        {
            this.restauranteDao=restauranteDao;
        }

        @Override
        protected Void doInBackground(Restaurante... restaurante)
        {
            restauranteDao.actualizarRestaurante(restaurante[0]);
            return null;
        }
    }
    public void eliminarRestaurante(Restaurante restaurante)
    {
        new EliminarRestauranteAsyncTask(restauranteDao).execute(restaurante);
    }

    private static class EliminarRestauranteAsyncTask extends AsyncTask<Restaurante,Void,Void>
    {
        private RestauranteDao restauranteDao;
        public EliminarRestauranteAsyncTask(RestauranteDao restauranteDao)
        {
            this.restauranteDao=restauranteDao;
        }

        @Override
        protected Void doInBackground(Restaurante... restaurante)
        {
            restauranteDao.eliminarRestaurante(restaurante[0]);
            return null;
        }
    }

}
