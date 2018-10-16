package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.AnuncianteRepository;
import com.example.asd.instafood.db.models.Anunciante;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.List;

public class AnuncianteViewModel extends AndroidViewModel
{
    private AnuncianteRepository anuncianteRespository;
    private LiveData<List<Anunciante>> anunciantes;

    public AnuncianteViewModel(@NonNull Application application)
    {
        super(application);
        anuncianteRespository=new AnuncianteRepository(application);
        anunciantes=anuncianteRespository.darAnunciantes();
    }

    public LiveData<List<Anunciante>> darAnunciantes()
    {
        return  anunciantes;
    }
    public LiveData<Anunciante> darAnuncianteEmail(String email)
    {
        return anuncianteRespository.darAnunciantesEmail(email);
    }
    public void actualizarAnunciante(Anunciante anunciante)
    {
        anuncianteRespository.acualizarAnunciante(anunciante);
    }
    public void ingresarAnunciante(Anunciante anunciante)
    {
        anuncianteRespository.ingresarAnunciante(anunciante);
    }
    public LiveData<List<Restaurante>>  darRestaurantesAnunciante(int id)
    {
        return  anuncianteRespository.darRestaurantesAnunciante(id);
    }
}
