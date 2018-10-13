package com.example.asd.instafood.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
        (
                indices = @Index(value = "nombreTipoComida",unique = true)
        )
public class TipoComida
{
    @PrimaryKey(autoGenerate = true)
    public int iDtipoComida;

    @NonNull
    public String nombreTipoComida;

    public TipoComida(int iDtipoComida, @NonNull String nombreTipoComida) {
        this.iDtipoComida = iDtipoComida;
        this.nombreTipoComida = nombreTipoComida;
    }

    public int getiDtipoComida() {
        return iDtipoComida;
    }

    public void setiDtipoComida(int iDtipoComida) {
        this.iDtipoComida = iDtipoComida;
    }

    @NonNull
    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(@NonNull String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }
}
