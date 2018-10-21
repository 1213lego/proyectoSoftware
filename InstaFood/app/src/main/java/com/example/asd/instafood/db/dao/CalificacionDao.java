package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.database.TimestampConverter;
import com.example.asd.instafood.db.models.Calificacion;

import java.util.List;


@Dao
@TypeConverters(TimestampConverter.class)
public interface CalificacionDao extends IDao<Calificacion>
{
    @Query("select * from Calificacion")
    LiveData<List<Calificacion>> consultarCalificaciones();

}
