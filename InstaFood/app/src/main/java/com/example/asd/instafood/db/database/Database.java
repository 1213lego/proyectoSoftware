package com.example.asd.instafood.db.database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.asd.instafood.db.dao.AnuncianteDao;
import com.example.asd.instafood.db.dao.CalificacionDao;
import com.example.asd.instafood.db.dao.OpnionRestauranteDao;
import com.example.asd.instafood.db.dao.PlatoDao;
import com.example.asd.instafood.db.dao.RestauranteDao;
import com.example.asd.instafood.db.dao.RestauranteFavoritoDao;
import com.example.asd.instafood.db.dao.TipoComidaDao;
import com.example.asd.instafood.db.dao.UsuarioDao;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Calificacion;
import com.example.asd.instafood.db.models.OpinionRestaurante;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;
import com.example.asd.instafood.db.models.TipoComida;
import com.example.asd.instafood.db.models.Usuario;


@android.arch.persistence.room.Database
        (
                entities =
                        {
                                Anunciante.class, Calificacion.class, OpinionRestaurante.class,
                                Plato.class, Restaurante.class, RestauranteFavorito.class,
                                TipoComida.class, Usuario.class
                        },
                version = 1
        )
public abstract class Database extends RoomDatabase
{
    private static final String DB_NAME = "restaurantesDatabase.db";

    private static volatile Database instance;

    static public synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static Database create(final Context context)
    {
        //return Room.databaseBuilder(context,Database.class,DB_NAME).build();
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), Database.class)
                // To simplify the codelab, allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();
    }
    public static void destroyInstance()
    {
        instance=null;
    }
    public abstract UsuarioDao usuarios();
    public abstract PlatoDao platos();
    public abstract RestauranteDao restaurantes();
    public abstract TipoComidaDao tipoComidas();
    public abstract AnuncianteDao anunciantes();
    public abstract RestauranteFavoritoDao restauranteFavorito();
    public abstract CalificacionDao calificacionDao();
    public abstract OpnionRestauranteDao opnionRestaurante();

}
