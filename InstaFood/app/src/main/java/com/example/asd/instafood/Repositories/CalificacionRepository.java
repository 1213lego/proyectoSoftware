package com.example.asd.instafood.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.asd.instafood.db.dao.CalificacionDao;
import com.example.asd.instafood.db.database.DatabaseInstafood;
import com.example.asd.instafood.db.models.Calificacion;

import java.util.List;

public class CalificacionRepository
{

    private CalificacionDao calificacionDao;
    private LiveData<List<Calificacion>> calificaciones;

    public CalificacionRepository(Application application)
    {
        calificacionDao=DatabaseInstafood.getInstance(application).calificaciones();
        calificaciones=calificacionDao.consultarCalificaciones();
    }
    public LiveData<List<Calificacion>> darCalificaciones()
    {
        return calificaciones;
    }
    public void ingresarCalificacion(Calificacion calificacion)
    {
        new IngresarCalificacionAsyncTask(calificacionDao).execute(calificacion);
    }
    private static class IngresarCalificacionAsyncTask extends AsyncTask<Calificacion, Void,Void>
    {
        private CalificacionDao calificacionDao;
        public IngresarCalificacionAsyncTask(CalificacionDao calificacionDao)
        {
            this.calificacionDao=calificacionDao;
        }

        @Override
        protected Void doInBackground(Calificacion... calificacions) {
            calificacionDao.ingresarCalificacion(calificacions[0]);
            return null;
        }
    }
    public void actualizarCalificacion(Calificacion calificacion)
    {
        new ActualizarCalificacionAsyncTask(calificacionDao).execute(calificacion);
    }
    private static class ActualizarCalificacionAsyncTask extends AsyncTask<Calificacion, Void,Void>
    {
        private CalificacionDao calificacionDao;
        public ActualizarCalificacionAsyncTask(CalificacionDao calificacionDao)
        {
            this.calificacionDao=calificacionDao;
        }

        @Override
        protected Void doInBackground(Calificacion... calificacions) {
            calificacionDao.actualizarCalificacion(calificacions[0]);
            return null;
        }
    }

    public void eliminarCalificacion(Calificacion calificacion)
    {
        new EliminarCalificacionAsyncTask(calificacionDao).execute(calificacion);
    }
    private static class EliminarCalificacionAsyncTask extends AsyncTask<Calificacion, Void,Void>
    {
        private CalificacionDao calificacionDao;
        public EliminarCalificacionAsyncTask(CalificacionDao calificacionDao)
        {
            this.calificacionDao=calificacionDao;
        }

        @Override
        protected Void doInBackground(Calificacion... calificacions) {
            calificacionDao.eliminarCalificacion(calificacions[0]);
            return null;
        }
    }
}
