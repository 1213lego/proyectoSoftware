package com.example.asd.instafood.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(indexes = { @Index(value = "latitud,longitud", unique = true)})
public class Restaurantes {

    @Id(autoincrement = true)
    private Long idRestaurante;
    @NotNull
    private double latitud;
    @NotNull
    private double longitud;
    @NotNull
    private  String nombreRestaurante;
    @ToMany
    @JoinEntity(
            entity = JoinRestaurantesWithRestaurantes.class,
            sourceProperty = "idRestaurante",
            targetProperty = "nombrePlato"
    )
    private List<Plato> platoList;

    public  Restaurantes( double latitud, double longitud ,String nombreRestaurante)
    {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreRestaurante = nombreRestaurante;
    }


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 978825975)
    private transient RestaurantesDao myDao;
    @Generated(hash = 469461717)
    public Restaurantes(Long idRestaurante, double latitud, double longitud,
            @NotNull String nombreRestaurante) {
        this.idRestaurante = idRestaurante;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreRestaurante = nombreRestaurante;
    }
    @Generated(hash = 441328465)
    public Restaurantes() {
    }
    public Long getIdRestaurante() {
        return this.idRestaurante;
    }
    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    public double getLatitud() {
        return this.latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return this.longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public String getNombreRestaurante() {
        return this.nombreRestaurante;
    }
    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1257195636)
    public List<Plato> getPlatoList() {
        if (platoList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlatoDao targetDao = daoSession.getPlatoDao();
            List<Plato> platoListNew = targetDao
                    ._queryRestaurantes_PlatoList(idRestaurante);
            synchronized (this) {
                if (platoList == null) {
                    platoList = platoListNew;
                }
            }
        }
        return platoList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1362202527)
    public synchronized void resetPlatoList() {
        platoList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    @Override
    public String toString() {
        return "Restaurantes{" +
                "idRestaurante=" + idRestaurante +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", nombreRestaurante='" + nombreRestaurante + '\'' +
                '}';
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2022022324)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRestaurantesDao() : null;
    }
}
