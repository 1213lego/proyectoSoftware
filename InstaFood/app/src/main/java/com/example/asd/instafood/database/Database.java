package com.example.asd.instafood.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.asd.instafood.daos.AnuncianteDao;
import com.example.asd.instafood.daos.PlatoDao;
import com.example.asd.instafood.daos.RestauranteDao;
import com.example.asd.instafood.daos.TipoComidaDao;
import com.example.asd.instafood.daos.UsuarioDao;
import com.example.asd.instafood.models.Anunciante;
import com.example.asd.instafood.models.OpinionRestaurante;
import com.example.asd.instafood.models.Plato;
import com.example.asd.instafood.models.Restaurante;
import com.example.asd.instafood.models.TipoComida;
import com.example.asd.instafood.models.Usuario;

@android.arch.persistence.room.Database(entities = { Usuario.class, Anunciante.class, OpinionRestaurante.class,
                                                    Plato.class, Restaurante.class, TipoComida.class}, version = 1)
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

}
