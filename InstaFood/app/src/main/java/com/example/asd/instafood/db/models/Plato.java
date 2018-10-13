package com.example.asd.instafood.db.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
        (
                foreignKeys = @ForeignKey(entity = Restaurante.class, parentColumns = "restauranteId", childColumns = "restaurante")
        )
public class Plato
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

    @NonNull
    private String rutaFotoPlato;

    public Plato(@NonNull int idPlato, @NonNull int restaurante, @NonNull String nombrePlato,
                 @NonNull String descripcion, @NonNull String rutaFotoPlato) {
        this.idPlato = idPlato;
        this.restaurante = restaurante;
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.rutaFotoPlato = rutaFotoPlato;
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

    @NonNull
    public String getRutaFotoPlato() {
        return rutaFotoPlato;
    }

    public void setRutaFotoPlato(@NonNull String rutaFotoPlato) {
        this.rutaFotoPlato = rutaFotoPlato;
    }
}
