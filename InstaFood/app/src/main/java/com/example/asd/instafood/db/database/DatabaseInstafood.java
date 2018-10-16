package com.example.asd.instafood.db.database;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.asd.instafood.db.dao.AnuncianteDao;
import com.example.asd.instafood.db.dao.CalificacionDao;
import com.example.asd.instafood.db.dao.OpinionRestauranteDao;
import com.example.asd.instafood.db.dao.PlatoDao;
import com.example.asd.instafood.db.dao.RestauranteDao;
import com.example.asd.instafood.db.dao.RestauranteFavoritoDao;
import com.example.asd.instafood.db.dao.TipoComidaDao;
import com.example.asd.instafood.db.dao.UsuarioDao;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Calificacion;
import com.example.asd.instafood.db.models.Estados;
import com.example.asd.instafood.db.models.OpinionRestaurante;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.RestauranteFavorito;
import com.example.asd.instafood.db.models.TipoComida;
import com.example.asd.instafood.db.models.TipoUsuario;
import com.example.asd.instafood.db.models.Usuario;

import java.util.Date;


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
public abstract class DatabaseInstafood extends RoomDatabase
{
    private static final String DB_NAME = "restaurantesDatabase.db";

    private static volatile DatabaseInstafood instance;

    static public synchronized DatabaseInstafood getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static DatabaseInstafood create(final Context context)
    {
        //return Room.databaseBuilder(context,Database.class,DB_NAME).build();
        return Room.databaseBuilder(context.getApplicationContext(),DatabaseInstafood.class,DB_NAME).
                fallbackToDestructiveMigration().addCallback(roomCallback).build();
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
    public abstract CalificacionDao calificaciones();
    public abstract OpinionRestauranteDao opnionRestaurante();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private UsuarioDao usuarioDao;
        private PlatoDao platoDao;
        private RestauranteDao restauranteDao;
        private TipoComidaDao tipoComidaDao;
        private AnuncianteDao anuncianteDao;
        private RestauranteFavoritoDao restauranteFavoritoDao;
        private CalificacionDao calificacionDao;
        private OpinionRestauranteDao opnionRestauranteDao;

        private PopulateDbAsyncTask(DatabaseInstafood db)
        {
            usuarioDao=db.usuarios();
            platoDao=db.platos();
            restauranteDao=db.restaurantes();
            tipoComidaDao=db.tipoComidas();
            anuncianteDao=db.anunciantes();
            restauranteFavoritoDao=db.restauranteFavorito();
            calificacionDao=db.calificaciones();
            opnionRestauranteDao=db.opnionRestaurante();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            //Ingreso 3 usuarios normales
            Usuario usuario1=new Usuario("usuario1@usuario1.com", "nombre1", "apellido1",
                    "usuario1", Estados.ESTADO_ACTIVO, TipoUsuario.USUARIO_NORMAL,"foto1");
            usuarioDao.ingresarUsuario(usuario1);

            Usuario usuario2=new Usuario("usuario2@usuario2.com", "nombre2", "apellido2",
                    "usuario2", Estados.ESTADO_ACTIVO, TipoUsuario.USUARIO_NORMAL,"foto");
            usuarioDao.ingresarUsuario(usuario2);

            Usuario usuario3=new Usuario("usuario3@usuario3.com", "nombre3", "apellido3",
                    "usuario3", Estados.ESTADO_ACTIVO, TipoUsuario.USUARIO_NORMAL,"foto3");
            usuarioDao.ingresarUsuario(usuario3);

            // ingreso dos anunciantes
            Usuario usuario4=new Usuario("anunciante1@anunciante1.com", "anunciante1", "anunciante1",
                    "anunciante1", Estados.ESTADO_ACTIVO, TipoUsuario.USUARIO_ANUNCIANTE,"anunciante1");
            usuarioDao.ingresarUsuario(usuario4);

            Anunciante anunciante1= new Anunciante(usuario4.getEmail(),20000,new Date());
            int idAnunciante1= (int)anuncianteDao.ingresarAnunciante(anunciante1);

            Usuario usuario5=new Usuario("usuario5@usuario5.com", "usuario5", "usuario5",
                    "usuario5", Estados.ESTADO_ACTIVO, TipoUsuario.USUARIO_ANUNCIANTE,"usuario5");
            usuarioDao.ingresarUsuario(usuario5);

            Anunciante anunciante2= new Anunciante(usuario5.getEmail(),20000,new Date());
            int idAnunciante2= (int)anuncianteDao.ingresarAnunciante(anunciante2);

            // ingres 6 tipos de comida
            TipoComida tipoComida1= new TipoComida("Comida Rapida");
            int id1= (int) tipoComidaDao.ingresarTipoComida(tipoComida1);
            TipoComida tipoComida2= new TipoComida("Comida China");
            int id2= (int)tipoComidaDao.ingresarTipoComida(tipoComida2);
            TipoComida tipoComida3= new TipoComida("Comida Italiana");
            int id3= (int)tipoComidaDao.ingresarTipoComida(tipoComida3);
            TipoComida tipoComida4= new TipoComida("Comida Tradicional");
            int id4= (int)tipoComidaDao.ingresarTipoComida(tipoComida4);
            TipoComida tipoComida5= new TipoComida("Comida Tolimense");
            tipoComidaDao.ingresarTipoComida(tipoComida5);
            TipoComida tipoComida6= new TipoComida("Comida Tailandesa");
            tipoComidaDao.ingresarTipoComida(tipoComida6);

            //Ingreso dos restaurantes
            Restaurante restaurante1= new Restaurante(idAnunciante1,id1,"Comidas Rapidas La Quinta",
                    "3111654","Hamburguesas, perros y mas", 4.427367,-75.205258,
                    "dsfdsfdvdsvsd","foto");
            int restauranteId1= (int)restauranteDao.ingresarRestaurante(restaurante1);

            Restaurante restaurante2= new Restaurante(idAnunciante2,id4,"Empanadas Toro Rojo",
                    "1775187157","Empanadas y mas", 4.449076 ,-75.200242,
                    "fefefdsfdsfdsf","eeeeeee");
            int restauranteId2= (int) restauranteDao.ingresarRestaurante(restaurante2);

            Restaurante restaurante3= new Restaurante(idAnunciante2,id4,"Carnaval del pollo",
                    "1775187157","Pollos y conejo", 4.425716 ,-75.190868,
                    "av mirolindo","pollo");
            int restauranteId3= (int) restauranteDao.ingresarRestaurante(restaurante3);




            //ingreso 3 platos al restaurante1
            Plato plato= new Plato(restauranteId1,"Hamburguesa","dsdsdsd","foto");
            platoDao.ingresarPlato(plato);
            Plato plato1= new Plato(restauranteId1,"Perro","dsdsdsd","foto");
            platoDao.ingresarPlato(plato1);
            Plato plato2= new Plato(restauranteId1,"Patacon","dsdsdsd","foto");
            platoDao.ingresarPlato(plato2);

            //ingreso 3 platos al restaurante2
            Plato plato3= new Plato(restauranteId2,"Empanada criolla","dsdsdsd","foto");
            platoDao.ingresarPlato(plato3);
            Plato plato4= new Plato(restauranteId2,"Empanada Ropa vieja","dsdsdsd","foto");
            platoDao.ingresarPlato(plato4);
            Plato plato5= new Plato(restauranteId2,"Empanda tradicional","dsdsdsd","foto");
            platoDao.ingresarPlato(plato5);

            //ingreso opiniones al restaurate1
            OpinionRestaurante opinionRestaurante= new OpinionRestaurante(usuario1.getEmail(),restauranteId1,new Date(),
                    "blblbblblblbblblb");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante);
            OpinionRestaurante opinionRestaurante1= new OpinionRestaurante(usuario2.getEmail(),restauranteId1,new Date(),
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante1);
            OpinionRestaurante opinionRestaurante2= new OpinionRestaurante(usuario3.getEmail(),restauranteId1,new Date(),
                    "bbbbbbbbbbbbbbbbbbbbbbbbbb");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante2);
            //ingreso opiniones al restaurate2
            OpinionRestaurante opinionRestaurante3= new OpinionRestaurante(usuario1.getEmail(),restauranteId2,new Date(),
                    " fgbdcbdfdsx");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante3);
            OpinionRestaurante opinionRestaurante4= new OpinionRestaurante(usuario2.getEmail(),restauranteId2,new Date(),
                    "fhtdxsf");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante4);
            OpinionRestaurante opinionRestaurante5= new OpinionRestaurante(usuario3.getEmail(),restauranteId2,new Date(),
                    "xyybgfvhygj");
            opnionRestauranteDao.ingresarOpinionRestaurante(opinionRestaurante5);

            Calificacion calificacion1=new Calificacion(restauranteId1,usuario1.getEmail(),new Date(),3
            , 3,3,3,3);
            calificacionDao.ingresarCalificacion(calificacion1);
            Calificacion calificacion2=new Calificacion(restauranteId2,usuario1.getEmail(),new Date(),3
                    , 3,3,3,3);
            calificacionDao.ingresarCalificacion(calificacion2);

            Calificacion calificacion3=new Calificacion(restauranteId2,usuario2.getEmail(),new Date(),3
                    , 3,3,3,3);
            calificacionDao.ingresarCalificacion(calificacion3);

            Calificacion calificacion4=new Calificacion(restauranteId2,usuario2.getEmail(),new Date(),3
                    , 3,3,3,3);
            calificacionDao.ingresarCalificacion(calificacion4);

            RestauranteFavorito restauranteFavorito= new RestauranteFavorito(restauranteId1,usuario1.getEmail());
            restauranteFavoritoDao.ingresarRestauranteFavorito(restauranteFavorito);

            RestauranteFavorito restauranteFavorito1= new RestauranteFavorito(restauranteId2,usuario1.getEmail());
            restauranteFavoritoDao.ingresarRestauranteFavorito(restauranteFavorito1);

            RestauranteFavorito restauranteFavorito3= new RestauranteFavorito(restauranteId3,usuario1.getEmail());
            restauranteFavoritoDao.ingresarRestauranteFavorito(restauranteFavorito3);


            RestauranteFavorito restauranteFavorito7= new RestauranteFavorito(restauranteId2,usuario2.getEmail());
            restauranteFavoritoDao.ingresarRestauranteFavorito(restauranteFavorito7);

            return null;
        }
    }
}
