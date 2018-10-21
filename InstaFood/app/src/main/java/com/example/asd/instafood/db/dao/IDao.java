package com.example.asd.instafood.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.example.asd.instafood.db.models.IDto;

public interface IDao<T extends IDto>
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long ingresar(T element);
    @Delete
    void eliminar(T element);
    @Update
    void actualizar(T element);
}
