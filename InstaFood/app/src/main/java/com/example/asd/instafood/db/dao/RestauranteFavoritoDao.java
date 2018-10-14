package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;

import java.util.List;

@Dao
public interface RestauranteFavoritoDao
{
    @Query
            (
                    "select * from Restaurante " +
                            "INNER JOIN RestauranteFavorito ON RestauranteFavorito.restaurante=Restaurante.restauranteId where " +
                            "RestauranteFavorito.usuario= :email"
            )
    LiveData<List<Restaurante>> consultarRestaurantesFavoritosUsuario(String email);

    @Insert
    long ingresarRestauranteFavorito(RestauranteFavorito restauranteFavorito);

    @Update
    void actualizarRestauranteFavorito(RestauranteFavorito restauranteFavorito);

    @Delete
    void eliminarRestauranteFavorito(RestauranteFavorito restauranteFavorito);
}
