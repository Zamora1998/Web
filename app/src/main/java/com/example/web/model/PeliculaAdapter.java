package com.example.web.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.web.R;
import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<PeliculaModel> peliculas;

    public void setPeliculas(List<PeliculaModel> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        PeliculaModel pelicula = peliculas.get(position);

        // Glide.with() se importa solo una vez
        Glide.with(holder.itemView.getContext())
                .load(pelicula.getRutaPoster())
                .into(holder.imageView);

        holder.textView.setText(pelicula.getNombre());
    }

    @Override
    public int getItemCount() {
        return peliculas != null ? peliculas.size() : 0;
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
