package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.TipoComidaRepository;
import com.example.asd.instafood.db.models.TipoComida;

import java.util.List;

public class TipoComidaViewModel extends AndroidViewModel
{
    private TipoComidaRepository tipoComidaRepository;
    private LiveData<List<TipoComida>> tiposComida;

    public TipoComidaViewModel(@NonNull Application application)
    {
        super(application);
        tipoComidaRepository=new TipoComidaRepository(application);
        tiposComida=tipoComidaRepository.darComidas();
    }
    public LiveData<List<TipoComida>> darTiposComida()
    {
        return tiposComida;
    }
    public void actualizarTipoComida(TipoComida tipoComida)
    {
        tipoComidaRepository.actualizarTipoComida(tipoComida);
    }
    public void eliminarComida(TipoComida tipoComida)
    {
        tipoComidaRepository.eliminarTipoComida(tipoComida);
    }
    public void ingresarTipoComida(TipoComida tipoComida)
    {
        tipoComidaRepository.ingresarTipoComida(tipoComida);
    }

}
