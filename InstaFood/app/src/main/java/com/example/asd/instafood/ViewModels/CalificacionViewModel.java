package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.CalificacionRepository;
import com.example.asd.instafood.db.dao.CalificacionDao;
import com.example.asd.instafood.db.models.Calificacion;

import java.util.List;

public class CalificacionViewModel extends AndroidViewModel
{

    private CalificacionRepository calificacionRepository;
    private LiveData<List<Calificacion>> calificaciones;

    public CalificacionViewModel(@NonNull Application application)
    {
        super(application);
        calificacionRepository= new CalificacionRepository(application);
        calificaciones=calificacionRepository.darCalificaciones();
    }

    public LiveData<List<Calificacion>> darCalificaciones() {
        return calificaciones;
    }
    public void ingresarCalificacion(Calificacion calificacion)
    {
        calificacionRepository.ingresarCalificacion(calificacion);
    }
    public void actualizarCalificacion(Calificacion  calificacion)
    {
        calificacionRepository.actualizarCalificacion(calificacion);
    }
    public void eliminarCalificacion(Calificacion  calificacion)
    {
        calificacionRepository.eliminarCalificacion(calificacion);
    }

}
