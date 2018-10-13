package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.models.Restaurante;

import java.util.List;

@Dao
public interface RestauranteDao
{
    @Query("select * from Restaurante")
    List<Restaurante> consultarRestaurantes();

    @Query("select *from Restaurante where nombreRestaurante= :nombreRes")
    List<Restaurante> consultarRestauranteNombre(String nombreRes);

    @Insert
    long ingresarRestaurante(Restaurante restaurante);

    @Update
    void  actualizarRestaurante(Restaurante restaurante);

}
