package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.Usuario;

import java.util.List;


@Dao
public interface UsuarioDao extends IDao<Usuario>
{
    @Query("select * from Usuario")
    LiveData<List<Usuario>> consultarTodosLosUsuario();

    @Query("select * from Usuario where email= :email LIMIT 1")
    LiveData<Usuario> consultarPorEmail(String email);

}
