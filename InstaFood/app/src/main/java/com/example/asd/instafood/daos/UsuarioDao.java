package com.example.asd.instafood.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.models.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao
{
    @Insert
    long insertarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Query("select * from Usuario")
    List<Usuario> consultarTodosLosUsuario();

    @Query("select * from Usuario where email= :emailUsuario")
    Usuario consultarPorEmail(String emailUsuario);




}
