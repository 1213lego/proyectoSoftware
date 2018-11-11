package com.example.asd.instafood.db.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


@Entity
        (
                indices = @Index(value= "restauranteId",unique = true),
                foreignKeys =
                        {
                                @ForeignKey(entity = Anunciante.class, parentColumns = "idAnunciante", childColumns = "anunciante"),
                                @ForeignKey(entity = TipoComida.class, parentColumns = "iDtipoComida", childColumns = "tipoComida")
                        }
        )
public class Restaurante implements IDto
{
    @NonNull
    @ColumnInfo(name = "restauranteId")
    @PrimaryKey(autoGenerate = true)
    private int restauranteId;

    //llaves foranea
    @NonNull
    private int anunciante;
    @NonNull
    private int tipoComida;

    @NonNull
    private String nombreRestaurante;
    @NonNull
    private String telefonoRestaurante;
    @NonNull
    private String descripcionRestaurante;
    @NonNull
    private double latitud;
    @NonNull
    private double longitud;
    @NonNull
    private String direccionRestaurante;

    @Nullable
    @ColumnInfo(name = "imageArray",typeAffinity = ColumnInfo.BLOB)
    private byte [] imageArray;

    public Restaurante(@NonNull int anunciante, @NonNull int tipoComida, @NonNull String nombreRestaurante,
                       @NonNull String telefonoRestaurante, @NonNull String descripcionRestaurante,
                       @NonNull double latitud, @NonNull double longitud, @NonNull String direccionRestaurante, @Nullable byte[] imageArray)
    {
        this.anunciante = anunciante;
        this.tipoComida = tipoComida;
        this.nombreRestaurante = nombreRestaurante;
        this.telefonoRestaurante = telefonoRestaurante;
        this.descripcionRestaurante = descripcionRestaurante;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccionRestaurante = direccionRestaurante;
        this.imageArray = imageArray;
    }

    @NonNull
    public int getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(@NonNull int restauranteId) {
        this.restauranteId = restauranteId;
    }

    @NonNull
    public int getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(@NonNull int anunciante) {
        this.anunciante = anunciante;
    }

    @NonNull
    public int getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(@NonNull int tipoComida) {
        this.tipoComida = tipoComida;
    }

    @NonNull
    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(@NonNull String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    @NonNull
    public String getTelefonoRestaurante() {
        return telefonoRestaurante;
    }

    public void setTelefonoRestaurante(@NonNull String telefonoRestaurante) {
        this.telefonoRestaurante = telefonoRestaurante;
    }

    @NonNull
    public String getDescripcionRestaurante() {
        return descripcionRestaurante;
    }

    public void setDescripcionRestaurante(@NonNull String descripcionRestaurante) {
        this.descripcionRestaurante = descripcionRestaurante;
    }

    @NonNull
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull double latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull double longitud) {
        this.longitud = longitud;
    }

    @NonNull
    public String getDireccionRestaurante() {
        return direccionRestaurante;
    }

    public void setDireccionRestaurante(@NonNull String direccionRestaurante) {
        this.direccionRestaurante = direccionRestaurante;
    }

    @Nullable
    public byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(@Nullable byte[] imageArray) {
        this.imageArray = imageArray;
    }
}
