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
                                @ForeignKey(entity = Usuario.class, parentColumns = "email", childColumns = "usuario"),
                                @ForeignKey(entity = Plato.class,parentColumns = "idPlato",childColumns = "plato")
                        }
        )
public class OpinionPlato implements IDto
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long idOpinion;

    //llaves Foraneas
    @NonNull
    private String usuario;
    @NonNull
    private int plato;


    @NonNull
    @TypeConverters({TimestampConverter.class})
    private Date fechaOpnion;

    @NonNull
    private String opinion;

    public OpinionPlato(@NonNull String usuario, @NonNull int plato, @NonNull Date fechaOpnion, @NonNull String opinion)
    {
        this.usuario = usuario;
        this.plato = plato;
        this.fechaOpnion = fechaOpnion;
        this.opinion = opinion;
    }

    @NonNull
    public long getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(@NonNull long idOpinion) {
        this.idOpinion = idOpinion;
    }

    @NonNull
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }

    @NonNull
    public int getPlato() {
        return plato;
    }

    public void setPlato(@NonNull int plato) {
        this.plato = plato;
    }

    @NonNull
    public Date getFechaOpnion() {
        return fechaOpnion;
    }

    public void setFechaOpnion(@NonNull Date fechaOpnion) {
        this.fechaOpnion = fechaOpnion;
    }

    @NonNull
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(@NonNull String opinion) {
        this.opinion = opinion;
    }
}
