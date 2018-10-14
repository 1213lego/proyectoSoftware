package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.RestauranteFavoritoDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;

import java.util.List;


public class RestaurantesFavoritosRepository
{
    private RestauranteFavoritoDao restauranteFavoritoDao;
    public RestaurantesFavoritosRepository(Application application)
    {
        restauranteFavoritoDao=DatabaseInstafood.getInstance(application).restauranteFavorito();
    }
    public LiveData<List<Restaurante>> darRestaurantesFavoritosEmail(String email)
    {
        return restauranteFavoritoDao.consultarRestaurantesFavoritosUsuario(email);
    }
    public void ingresarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        new IngresarRestauranteFavorito(restauranteFavoritoDao).execute(restauranteFavorito);
    }
    private static class IngresarRestauranteFavorito extends AsyncTask<RestauranteFavorito,Void,Void>
    {
        private RestauranteFavoritoDao restauranteFavoritoDao;
        public IngresarRestauranteFavorito(RestauranteFavoritoDao restauranteFavoritoDao)
        {
            this.restauranteFavoritoDao=restauranteFavoritoDao;
        }

        @Override
        protected Void doInBackground(RestauranteFavorito... restauranteFavoritos)
        {
            restauranteFavoritoDao.ingresarRestauranteFavorito(restauranteFavoritos[0]);
            return null;
        }
    }
    public void eliminarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        new EliminarRestauranteFavorito(restauranteFavoritoDao).execute(restauranteFavorito);
    }
    private static class EliminarRestauranteFavorito extends AsyncTask<RestauranteFavorito,Void,Void>
    {
        private RestauranteFavoritoDao restauranteFavoritoDao;
        public EliminarRestauranteFavorito(RestauranteFavoritoDao restauranteFavoritoDao)
        {
            this.restauranteFavoritoDao=restauranteFavoritoDao;
        }

        @Override
        protected Void doInBackground(RestauranteFavorito... restauranteFavoritos)
        {
            restauranteFavoritoDao.eliminarRestauranteFavorito(restauranteFavoritos[0]);
            return null;
        }
    }
    public void actualizarRestauranteFavorito(RestauranteFavorito restauranteFavorito)
    {
        new ActualizarRestauranteFavorito(restauranteFavoritoDao).execute(restauranteFavorito);
    }
    private static class ActualizarRestauranteFavorito extends AsyncTask<RestauranteFavorito,Void,Void>
    {
        private RestauranteFavoritoDao restauranteFavoritoDao;
        public ActualizarRestauranteFavorito(RestauranteFavoritoDao restauranteFavoritoDao)
        {
            this.restauranteFavoritoDao=restauranteFavoritoDao;
        }

        @Override
        protected Void doInBackground(RestauranteFavorito... restauranteFavoritos)
        {
            restauranteFavoritoDao.actualizarRestauranteFavorito(restauranteFavoritos[0]);
            return null;
        }
    }

}
