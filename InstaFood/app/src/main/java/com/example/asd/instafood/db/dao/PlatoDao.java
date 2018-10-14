package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.Plato;

import java.util.List;

@Dao
public interface PlatoDao
{

    @Query("select * from Plato")
    LiveData<List<Plato>> consultarPlatos();

    @Query("select * from Plato where nombrePlato= :nombre")
    LiveData<List<Plato>> consultarPorNombre(String nombre);

    @Insert
    long ingresarPlato(Plato plato);

    @Update
    void actualizarPlato(Plato plato);

    @Delete
    void eliminarPlato(Plato plato);
}
