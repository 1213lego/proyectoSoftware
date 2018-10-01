package com.example.asd.instafood.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity(indexes = { @Index(value = "nombrePlato", unique = true)})
public class Plato
{
    @Id(autoincrement = true)
    private Long idPlato;
    private String nombrePlato;
    private String tipoPlato;
    private String descripcion;



    public Plato(String nombrePlato, String tipoPlato, String descripcion)
    {
        this.nombrePlato = nombrePlato;
        this.tipoPlato = tipoPlato;
        this.descripcion = descripcion;
    }


    @Generated(hash = 1990499583)
    public Plato(Long idPlato, String nombrePlato, String tipoPlato,
            String descripcion) {
        this.idPlato = idPlato;
        this.nombrePlato = nombrePlato;
        this.tipoPlato = tipoPlato;
        this.descripcion = descripcion;
    }
    @Generated(hash = 1527140625)
    public Plato() {
    }
    public Long getIdPlato() {
        return this.idPlato;
    }
    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }
    public String getNombrePlato() {
        return this.nombrePlato;
    }
    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }
    public String getTipoPlato() {
        return this.tipoPlato;
    }
    public void setTipoPlato(String tipoPlato) {
        this.tipoPlato = tipoPlato;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
