package com.example.asd.instafood.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.OpinionRestaurante;

import java.util.List;

@Dao
public interface OpnionRestauranteDao
{
    @Query("select * from OpinionRestaurante")
    List<OpinionRestaurante> consultarOpiniones();

    @Query("select * from OpinionRestaurante where usuarioEmail= :email")
    List<OpinionRestaurante> consultarOpinionesPorEmail(String email);

    @Query("select * from OpinionRestaurante INNER JOIN Restaurante on Restaurante.restauranteId= OpinionRestaurante.restaurante " +
            "where Restaurante.nombreRestaurante= :nombre" )
    List<OpinionRestaurante> consultarOpinionesPorRestaurante(String nombre);

    @Insert
    long ingresarOpinionRestaurante(OpinionRestaurante opinionRestaurante);

    @Update
    void actualizarOpinionRestaurante(OpinionRestaurante opinionRestaurante);
}
