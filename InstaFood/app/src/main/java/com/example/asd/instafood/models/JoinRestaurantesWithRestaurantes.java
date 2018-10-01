package com.example.asd.instafood.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinRestaurantesWithRestaurantes
{
    @Id(autoincrement = true)
    private  Long idPlatoRestaurante;

    private Long idRestaurante;
    private String nombrePlato;


    public JoinRestaurantesWithRestaurantes(Long idRestaurante, String nombrePlato)
    {
        this.idRestaurante = idRestaurante;
        this.nombrePlato = nombrePlato;
    }


    @Generated(hash = 664066471)
    public JoinRestaurantesWithRestaurantes(Long idPlatoRestaurante,
            Long idRestaurante, String nombrePlato) {
        this.idPlatoRestaurante = idPlatoRestaurante;
        this.idRestaurante = idRestaurante;
        this.nombrePlato = nombrePlato;
    }
    @Generated(hash = 1207651378)
    public JoinRestaurantesWithRestaurantes() {
    }
    public Long getIdPlatoRestaurante() {
        return this.idPlatoRestaurante;
    }
    public void setIdPlatoRestaurante(Long idPlatoRestaurante) {
        this.idPlatoRestaurante = idPlatoRestaurante;
    }
    public Long getIdRestaurante() {
        return this.idRestaurante;
    }
    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    public String getNombrePlato() {
        return this.nombrePlato;
    }
    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }



}
