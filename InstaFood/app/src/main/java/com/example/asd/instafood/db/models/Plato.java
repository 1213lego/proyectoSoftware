package com.example.asd.instafood.db.models;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity
        (
                foreignKeys = @ForeignKey(entity = Restaurante.class, parentColumns = "restauranteId", childColumns = "restaurante")
        )
public class Plato implements IDto
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int idPlato;

    //llave foranea
    @NonNull
    private int restaurante;

    @NonNull
    private String nombrePlato;

    @NonNull
    private String descripcion;

    @Nullable
    @ColumnInfo(name = "imageArray",typeAffinity = ColumnInfo.BLOB)
    private byte [] imageArray;

    public Plato(@NonNull int restaurante, @NonNull String nombrePlato, @NonNull String descripcion, @Nullable byte[] imageArray)
    {
        this.restaurante = restaurante;
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.imageArray = imageArray;
    }

    @NonNull
    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(@NonNull int idPlato) {
        this.idPlato = idPlato;
    }

    @NonNull
    public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(@NonNull int restaurante) {
        this.restaurante = restaurante;
    }

    @NonNull
    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(@NonNull String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @Nullable
    public byte[] getImageArray() {
        return imageArray;
    }

    public void setImageArray(@Nullable byte[] imageArray) {
        this.imageArray = imageArray;
    }
}
