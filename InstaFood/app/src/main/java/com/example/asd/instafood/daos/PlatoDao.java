package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.models.Plato;

import java.util.List;

@Dao
public interface PlatoDao
{

    @Query("select * from Plato")
    List<Plato> consultarPlatos();

    @Query("select * from Plato where nombrePlato= :nombre")
    List<Plato> consultarPorNombre(String nombre);

    @Insert
    long ingresarPlato(Plato plato);

    @Update
    void actualizarPlato(Plato plato);
}
