package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

@Dao
public interface RestauranteDao
{
    @Query("select * from Restaurante")
    LiveData<List<Restaurante>> consultarRestaurantes();

    @Query("select *from Restaurante where nombreRestaurante= :nombreRes")
    LiveData<List<Restaurante>> consultarRestauranteNombre(String nombreRes);

    @Insert
    long ingresarRestaurante(Restaurante restaurante);

    @Update
    void  actualizarRestaurante(Restaurante restaurante);

    @Delete
    void eliminarRestaurante(Restaurante restaurante);

}
