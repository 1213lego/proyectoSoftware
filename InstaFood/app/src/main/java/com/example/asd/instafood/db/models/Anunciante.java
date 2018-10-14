package com.example.asd.instafood.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.asd.instafood.db.database.TimestampConverter;

import java.util.Date;

@Entity
        (
                indices = @Index(value = {"idAnunciante", "emailUsuario"}, unique = true),
                foreignKeys = @ForeignKey(entity = Usuario.class, parentColumns ="email", childColumns = "emailUsuario")
        )
public class Anunciante
{
    @PrimaryKey(autoGenerate = true)
    private int idAnunciante;

    //llave foranea
    private String emailUsuario;
    private double preciooSucripcion;
    @TypeConverters({TimestampConverter.class})
    private Date fechaSuscripcion;

    public Anunciante(String emailUsuario, double preciooSucripcion, Date fechaSuscripcion)
    {
        this.emailUsuario = emailUsuario;
        this.preciooSucripcion = preciooSucripcion;
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public int getIdAnunciante()
    {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante)
    {
        this.idAnunciante = idAnunciante;
    }

    public String getEmailUsuario()
    {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario)
    {
        this.emailUsuario = emailUsuario;
    }

    public double getPreciooSucripcion() {
        return preciooSucripcion;
    }

    public void setPreciooSucripcion(double preciooSucripcion)
    {
        this.preciooSucripcion = preciooSucripcion;
    }

    public Date getFechaSuscripcion()
    {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion)
    {
        this.fechaSuscripcion = fechaSuscripcion;
    }
}
