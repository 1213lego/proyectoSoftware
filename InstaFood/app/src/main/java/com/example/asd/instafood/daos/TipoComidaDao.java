package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.models.TipoComida;

import java.util.List;

@Dao
public interface TipoComidaDao
{
    @Query("select * from TipoComida")
    List<TipoComida> consultarTiposComida();

    @Insert
    long ingresarTipoComida(TipoComida tipoComida);

    @Update
    void actulizarTipoComida(TipoComida tipoComida);
}
