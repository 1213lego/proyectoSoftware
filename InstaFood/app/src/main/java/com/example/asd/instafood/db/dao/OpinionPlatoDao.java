package com.example.asd.instafood.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.example.asd.instafood.db.models.OpinionPlato;
import java.util.List;

@Dao
public interface OpinionPlatoDao extends IDao<OpinionPlato>
{
    @Query("select * from OpinionPlato")
    LiveData<List<OpinionPlato>> consultarOpiniones();

    @Query("select * from OpinionPlato where OpinionPlato.usuario= :usuario")
    LiveData<List<OpinionPlato>> consultarOpinionPorUsuario(String usuario);

    @Query("select * from OpinionPlato where OpinionPlato.plato= :plato")
    LiveData<List<OpinionPlato>> consultarOpinionPorPlatp(int plato);
}
