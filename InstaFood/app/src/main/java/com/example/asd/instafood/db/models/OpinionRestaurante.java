package com.example.asd.instafood.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.database.TimestampConverter;

import java.util.Date;

@Entity
        (
                foreignKeys =
                        {
                                @ForeignKey(entity = Usuario.class, parentColumns = "email", childColumns = "usuarioEmail"),
                                @ForeignKey(entity = Restaurante.class, parentColumns = "restauranteId",childColumns = "restaurante")
                        }

        )
public class OpinionRestaurante
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idOpinion;

    //llaves foraneas
    @NonNull
    private String usuarioEmail;
    @NonNull
    private int restaurante;

    @NonNull
    @TypeConverters({TimestampConverter.class})
    private Date fechaOpnion;

    @NonNull
    private String comentario;


    public OpinionRestaurante(@NonNull int idOpinion, @NonNull String usuarioEmail,
                              @NonNull int restaurante, @NonNull Date fechaOpnion, @NonNull String comentario)
    {
        this.idOpinion = idOpinion;
        this.usuarioEmail = usuarioEmail;
        this.restaurante = restaurante;
        this.fechaOpnion = fechaOpnion;
        this.comentario = comentario;
    }

    @NonNull
    public int getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(@NonNull int idOpinion) {
        this.idOpinion = idOpinion;
    }

    @NonNull
    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(@NonNull String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    @NonNull
    public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(@NonNull int restaurante) {
        this.restaurante = restaurante;
    }

    @NonNull
    public Date getFechaOpnion() {
        return fechaOpnion;
    }

    public void setFechaOpnion(@NonNull Date fechaOpnion) {
        this.fechaOpnion = fechaOpnion;
    }

    @NonNull
    public String getComentario() {
        return comentario;
    }

    public void setComentario(@NonNull String comentario) {
        this.comentario = comentario;
    }
}
