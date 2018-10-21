package com.example.asd.instafood.db.dao;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.database.TimestampConverter;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

@TypeConverters(TimestampConverter.class)
@Dao
public interface AnuncianteDao extends IDao<Anunciante>
{
    @Query("select * from Anunciante")
    LiveData<List<Anunciante>> consultarAnunciantes();

    @Query("select * from Anunciante where emailUsuario* :email")
    LiveData<Anunciante> consultarAnuncianteEmail(String email);

    @Query("select * from Restaurante where Restaurante.anunciante= :id")
    LiveData<List<Restaurante>> darListaResturantesAnunciante(int id);

}
