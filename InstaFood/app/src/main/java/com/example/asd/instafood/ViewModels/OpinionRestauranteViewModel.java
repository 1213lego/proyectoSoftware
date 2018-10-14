package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.OpinionRestauranteRepository;
import com.example.asd.instafood.db.models.OpinionRestaurante;

import java.util.List;

public class OpinionRestauranteViewModel extends AndroidViewModel
{
    private OpinionRestauranteRepository opinionRestauranteRepository;
    private LiveData<List<OpinionRestaurante>> opiniones;

    public OpinionRestauranteViewModel(@NonNull Application application)
    {
        super(application);
        opinionRestauranteRepository=new OpinionRestauranteRepository(application);
        opiniones=opinionRestauranteRepository.darOpiniones();
    }

    private LiveData<List<OpinionRestaurante>> darOpiniones()
    {
        return opiniones;
    }
    public LiveData<List<OpinionRestaurante>> opinionesPorEmail(String email)
    {
        return opinionRestauranteRepository.opinionesPorEmail(email);
    }
    public LiveData<List<OpinionRestaurante>> opinionesPorRestaurante(String nombre)
    {
        return opinionRestauranteRepository.opinionesPorRestaurante(nombre);
    }
    public void actualizarOpinion(OpinionRestaurante opinionRestaurante)
    {
        opinionRestauranteRepository.actualizarOpinionRestaurante(opinionRestaurante);
    }
    public void ingresarOpinion(OpinionRestaurante opinionRestaurante)
    {
        opinionRestauranteRepository.ingresarOpinionRestaurante(opinionRestaurante);
    }
}
