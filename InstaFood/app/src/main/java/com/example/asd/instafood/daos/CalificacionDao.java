package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.example.asd.instafood.database.TimestampConverter;
import com.example.asd.instafood.models.Calificacion;

import java.util.List;

@Dao
@TypeConverters(TimestampConverter.class)
public interface CalificacionDao
{
    @Query("select * from Calificacion")
    List<Calificacion> consultarCalificaciones();

    @Insert
    long ingresarCalificacion(Calificacion calificacion);

    @Delete
    void eliminarCalificacion(Calificacion calificacion);
}
