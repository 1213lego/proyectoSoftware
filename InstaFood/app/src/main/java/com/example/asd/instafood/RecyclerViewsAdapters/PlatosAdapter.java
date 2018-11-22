package com.example.asd.instafood.RecyclerViewsAdapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asd.instafood.R;
import com.example.asd.instafood.db.models.Plato;
import com.example.asd.instafood.db.models.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.PlatoHolder>
{
    private List<Plato> platos= new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PlatoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_plato, viewGroup, false);
        return new PlatoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoHolder platoHolder, int i)
    {
        Plato plato= platos.get(i);
        platoHolder.titulo.setText(plato.getNombrePlato());
        platoHolder.descripcion.setText(plato.getDescripcion());
        if(plato.getImageArray()!=null)
        {
            Bitmap bitmap= BitmapFactory.decodeByteArray(plato.getImageArray(),0,plato.getImageArray().length);
            platoHolder.imageView.setImageBitmap(bitmap);
        }
    }
    public Plato darPlato(int i)
    {
        return platos.get(i);
    }

    @Override
    public int getItemCount()
    {
        return platos.size();
    }

    public void cambiarPlatos(List<Plato> platoList)
    {
        platos=platoList;
        notifyDataSetChanged();
    }
    class PlatoHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo;
        private TextView descripcion;
        private ImageView imageView;

        public PlatoHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imagenPlato);
            titulo=(TextView)itemView.findViewById(R.id.tituloPlato);
            descripcion=(TextView) itemView.findViewById(R.id.descripcionPlato);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int position=getAdapterPosition();
                    if (listener!=null && position!=RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(platos.get(position));
                    }
                }
            });
        }
    }


    public interface OnItemClickListener
    {
        void onItemClick(Plato plato);
    }

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        listener=onItemClickListener;
    }

}
