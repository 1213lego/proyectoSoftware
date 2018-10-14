package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.asd.instafood.db.models.TipoComida;

import java.util.List;

@Dao
public interface TipoComidaDao
{
    @Query("select * from TipoComida")
    LiveData<List<TipoComida>> consultarTiposComida();

    @Insert
    long ingresarTipoComida(TipoComida tipoComida);

    @Update
    void actualizarTipoComida(TipoComida tipoComida);
    @Delete
    void eliminar(TipoComida tipoComida);
}
