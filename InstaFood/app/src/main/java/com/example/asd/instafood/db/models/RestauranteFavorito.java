package com.example.asd.instafood.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity
        (
                primaryKeys = {"restaurante","usuario"},
                foreignKeys =
                        {
                                @ForeignKey(entity = Usuario.class, parentColumns = "email",childColumns = "usuario"),
                                @ForeignKey(entity = Restaurante.class,parentColumns = "restauranteId", childColumns = "restaurante")
                        }
        )
public class RestauranteFavorito
{
    @NonNull
    private int restaurante;
    @NonNull
    private String usuario;

    public RestauranteFavorito(@NonNull int restaurante, @NonNull String usuario) {
        this.restaurante = restaurante;
        this.usuario = usuario;
    }

    @NonNull
    public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(@NonNull int restaurante) {
        this.restaurante = restaurante;
    }

    @NonNull
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }
}
