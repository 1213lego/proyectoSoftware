package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

@Dao
public interface RestauranteDao extends IDao<Restaurante>
{
    @Query("select * from Restaurante")
    LiveData<List<Restaurante>> consultarRestaurantes();

    @Query("select *from Restaurante where nombreRestaurante= :nombreRes")
    LiveData<List<Restaurante>> consultarRestauranteNombre(String nombreRes);

    @Query("select * from Plato where Plato.restaurante = :id ")
    LiveData<List<Plato>>  consultarPlatosRestaurante(int id);

    @Query("select * from Restaurante where acos(:latOrigen)+cos(:lonOrigen)<:radio")
    LiveData<Restaurante> consultarRestaurantesCercano(double latOrigen, double lonOrigen, double radio);

}
