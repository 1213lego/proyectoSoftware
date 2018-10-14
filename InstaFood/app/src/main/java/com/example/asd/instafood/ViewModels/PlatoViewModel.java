package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.PlatoRepository;
import com.example.asd.instafood.db.models.Plato;

import java.util.List;

public class PlatoViewModel extends AndroidViewModel
{
    private PlatoRepository platoRepository;
    private LiveData<List<Plato>>  platos;
    public PlatoViewModel(@NonNull Application application)
    {
        super(application);
        platoRepository=new PlatoRepository(application);
        platos=platoRepository.darPlatos();

    }

    public LiveData<List<Plato>> darPlatos()
    {
        return platos;
    }
    public LiveData<List<Plato>> darPlatosPorNombre(String nombre)
    {
        return platoRepository.darPlatosPorNombre(nombre);
    }
    public void ingresarPlato(Plato plato)
    {
        platoRepository.ingresarPlato(plato);
    }
    public void eliminarPlato(Plato plato)
    {
        platoRepository.eliminarPlato(plato);
    }
    public void actualizarPlato(Plato plato)
    {
        platoRepository.actualizarPlato(plato);
    }
}
