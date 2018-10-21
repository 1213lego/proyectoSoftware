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
public interface TipoComidaDao extends IDao<TipoComida>
{
    @Query("select * from TipoComida")
    LiveData<List<TipoComida>> consultarTiposComida();
}
