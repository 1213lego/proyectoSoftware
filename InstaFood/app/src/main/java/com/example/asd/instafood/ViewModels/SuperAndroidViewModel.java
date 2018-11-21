package com.example.asd.instafood.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.asd.instafood.Repositories.Repository;
import com.example.asd.instafood.db.models.IDto;

public class SuperAndroidViewModel extends AndroidViewModel
{
    protected Repository repository;
    public SuperAndroidViewModel(@NonNull Application application)
    {
        super(application);
        repository= new Repository(application);
    }
    public void actualizar(IDto iDto)
    {
        repository.actualizar(iDto);
    }
    public void eliminar(IDto iDto)
    {
        repository.eliminar(iDto);
    }
    public void ingresar(IDto iDto)
    {
        repository.ingresar(iDto);
    }
}
