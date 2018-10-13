package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.database.TimestampConverter;
import com.example.asd.instafood.models.Anunciante;

import java.util.List;


@TypeConverters(TimestampConverter.class)
@Dao
public interface AnuncianteDao
{
    @Query("select * from Anunciante")
    List<Anunciante> consultarAnunciantes();

    @Query("select * from Anunciante where emailUsuario* :email")
    Anunciante consultarAnuncianteEmail(String email);

    @Insert
    long insertarAnunciante(Anunciante anunciante);

    @Update
    void actulizarAnunciante(Anunciante anunciante);

}
