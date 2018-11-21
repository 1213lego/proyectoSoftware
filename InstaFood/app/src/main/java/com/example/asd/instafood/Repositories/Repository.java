package com.example.asd.instafood.Repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.asd.instafood.db.dao.AnuncianteDao;
import com.example.asd.instafood.db.dao.CalificacionDao;
import com.example.asd.instafood.db.dao.IDao;
import com.example.asd.instafood.db.dao.OpinionRestauranteDao;
import com.example.asd.instafood.db.dao.PlatoDao;
import com.example.asd.instafood.db.dao.RestauranteDao;
import com.example.asd.instafood.db.dao.RestauranteFavoritoDao;
import com.example.asd.instafood.db.dao.TipoComidaDao;
import com.example.asd.instafood.db.dao.UsuarioDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.IDto;
import com.example.asd.instafood.db.models.Restaurante;
import com.example.asd.instafood.db.models.TipoComida;
import com.example.asd.instafood.db.models.Usuario;

import java.lang.reflect.Method;
import java.util.List;

public class Repository
{
    private static final String TAG="Repository";
    private DatabaseInstafood databaseInstafood;
    private AnuncianteDao anuncianteDao;
    private CalificacionDao calificacionDao;
    private OpinionRestauranteDao opinionRestauranteDao;
    private PlatoDao platoDao;
    private RestauranteDao restauranteDao;
    private RestauranteFavoritoDao restauranteFavoritoDao;
    private TipoComidaDao tipoComidaDao;
    private UsuarioDao usuarioDao;
    public  Repository(Context context)
    {
        databaseInstafood=DatabaseInstafood.getInstance(context);
        anuncianteDao=databaseInstafood.getAnuncianteDao();
        calificacionDao=databaseInstafood.getCalificacionDao();
        opinionRestauranteDao=databaseInstafood.getOpinionRestauranteDao();
        platoDao=databaseInstafood.getPlatoDao();
        restauranteDao=databaseInstafood.getRestauranteDao();
        restauranteFavoritoDao=databaseInstafood.getRestauranteFavoritoDao();
        tipoComidaDao=databaseInstafood.getTipoComidaDao();
        usuarioDao=databaseInstafood.getUsuarioDao();
    }
    public void ingresar(final IDto iDtoElemento)
    {
        try
        {
            Class  elemento= iDtoElemento.getClass();
            String nombreDao= elemento.getSimpleName()+"Dao";
            Method methodGetDao= this.getClass().getMethod("get"+nombreDao,null);
            IDao dao= (IDao) methodGetDao.invoke(this,null);
            AsynTaskIngresarIDto asynTaskIngresarIDto= new AsynTaskIngresarIDto(dao);
            asynTaskIngresarIDto.execute(iDtoElemento);
            long id= asynTaskIngresarIDto.getIdIngreso();
            Log.d(TAG,"Todo bien ingresando "+ iDtoElemento.getClass().getSimpleName());
        }
        catch (Exception e)
        {
            Log.e(TAG,"Error ingresando " + iDtoElemento.getClass().getSimpleName(),e);
        }
    }
    private static class AsynTaskIngresarIDto extends AsyncTask<IDto, Void,Void>
    {
        private IDao iDao;
        private long idIngreso;
        public AsynTaskIngresarIDto(IDao iDao)
        {
            this.iDao=iDao;
        }
        @Override
        protected Void doInBackground(IDto... iDtos)
        {
            idIngreso = iDao.ingresar(iDtos[0]);
            Log.d(TAG,"En AsynTaskIngresarIDto se ha ingresado" + iDtos[0].getClass().getSimpleName()+ " "+ idIngreso);
            return null;
        }

        public long getIdIngreso() {
            return idIngreso;
        }
    }

    public void eliminar(final IDto iDtoElemento)
    {
        try
        {
            Class  elemento= iDtoElemento.getClass();
            String nombreDao= elemento.getSimpleName()+"Dao";
            Method methodGetDao= this.getClass().getMethod("get"+nombreDao,null);
            IDao dao= (IDao) methodGetDao.invoke(this,null);
            AsynTaskEliminarIDto asynTaskEliminarIDto = new AsynTaskEliminarIDto(dao);
            asynTaskEliminarIDto.execute(iDtoElemento);
            Log.d(TAG,"Todo bien eliminado "+ iDtoElemento.getClass().getSimpleName());
        }
        catch (Exception e)
        {
            Log.e(TAG,"Error eliminando" + iDtoElemento.getClass().getSimpleName(),e);
        }
    }
    private static class AsynTaskEliminarIDto extends AsyncTask<IDto, Void,Void>
    {
        private IDao iDao;
        public AsynTaskEliminarIDto(IDao iDao)
        {
            this.iDao=iDao;
        }
        @Override
        protected Void doInBackground(IDto... iDtos)
        {
            iDao.eliminar(iDtos[0]);
            Log.d(TAG,"En AsyntaskEliminarIDto se ha eliminado un " + iDtos[0].getClass().getSimpleName());
            return null;
        }
    }

    public void actualizar(final IDto iDtoElemento)
    {
        try
        {
            Class  elemento= iDtoElemento.getClass();
            String nombreDao= elemento.getSimpleName()+"Dao";
            Method methodGetDao= this.getClass().getMethod("get"+nombreDao,null);
            IDao dao= (IDao) methodGetDao.invoke(this,null);
            AsynTaskActualizarIDto asynTaskActualizarIDto = new AsynTaskActualizarIDto(dao);
            asynTaskActualizarIDto.execute(iDtoElemento);
            Log.d(TAG,"Todo bien actualizando "+ iDtoElemento.getClass().getSimpleName());
        }
        catch (Exception e)
        {
            Log.e(TAG,"Error actualizando" + iDtoElemento.getClass().getSimpleName(),e);
        }
    }
    private static class AsynTaskActualizarIDto extends AsyncTask<IDto, Void,Void>
    {
        private IDao iDao;
        public AsynTaskActualizarIDto(IDao iDao)
        {
            this.iDao=iDao;
        }
        @Override
        protected Void doInBackground(IDto... iDtos)
        {
            iDao.actualizar(iDtos[0]);
            Log.d(TAG,"En AsynTaskActualizarIDto se ha actualizado un " + iDtos[0].getClass().getSimpleName());
            return null;
        }
    }

    public LiveData<List<Restaurante>> darLiveDataRestaurantesFavoritosPorEmail(String email)
    {
        return restauranteFavoritoDao.consultarRestaurantesFavoritosUsuario(email);
    }
    public LiveData<Usuario> darLiveDataUsuarioPorEmail(String email)
    {
        return usuarioDao.consultarPorEmail(email);
    }
    public LiveData<List<Restaurante>> darLiveDataRestaurantes()
    {
        return restauranteDao.consultarRestaurantes();
    }
    public LiveData<List<TipoComida>> darLiveDataTiposComida()
    {
        return tipoComidaDao.consultarTiposComida();
    }
    public LiveData<Anunciante> darAnunciante(String email)
    {
        return anuncianteDao.consultarAnuncianteEmail(email);
    }
    protected AnuncianteDao getAnuncianteDao() {
        return anuncianteDao;
    }

    protected CalificacionDao getCalificacionDao() {
        return calificacionDao;
    }

    protected OpinionRestauranteDao getOpinionRestauranteDao() {
        return opinionRestauranteDao;
    }

    protected PlatoDao getPlatoDao() {
        return platoDao;
    }

    protected RestauranteDao getRestauranteDao() {
        return restauranteDao;
    }

    protected RestauranteFavoritoDao getRestauranteFavoritoDao() {
        return restauranteFavoritoDao;
    }

    protected TipoComidaDao getTipoComidaDao() {
        return tipoComidaDao;
    }

    protected UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }
}
