package com.example.web.model;

import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.web.DetallesPeliculaActivity;
import com.example.web.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        // Utiliza Glide para cargar la imagen
        Glide.with(holder.itemView.getContext())
                .load(pelicula.getRutaPoster())
                .into(holder.imageView);

        // Configura los elementos de la vista
        holder.textViewNombre.setText(pelicula.getNombre());
        holder.textViewNombre.setMovementMethod(LinkMovementMethod.getInstance());
        String calificacionFormateada = "Calificación: " + String.valueOf(pelicula.getCalificacionGenerQal());
        holder.textViewCalificacion.setText(calificacionFormateada); // Ajustado para obtener la calificación general
        holder.textViewNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Al hacer clic en el nombre de la película, inicia DetallesPeliculaActivity
                Intent intent = new Intent(view.getContext(), DetallesPeliculaActivity.class);
                intent.putExtra("nombrePelicula", pelicula.getNombre());
                view.getContext().startActivity(intent);
            }
        });
        // Formatea la reseña y la fecha
        String reseñaFormateada = "Reseña: " + pelicula.getResena();
        String fechaOriginal = pelicula.getFechaLanzamiento();
        String fechaFormateada = "Fecha: " + formatearFecha(fechaOriginal);

        holder.textViewResena.setText(reseñaFormateada);
        holder.textViewFecha.setText(fechaFormateada);

    }

    @Override
    public int getItemCount() {
        return peliculas != null ? peliculas.size() : 0;
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewResena;
        TextView textViewFecha;

        // Cambiado a int para manejar la calificación como un entero
        TextView textViewCalificacion;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewResena = itemView.findViewById(R.id.textViewResena);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewCalificacion = itemView.findViewById(R.id.textViewCalificacion); // Corregido

        }

    }

    private String formatearFecha(String fechaOriginal) {
        try {
            // Formato de entrada
            SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

            // Formato de salida
            SimpleDateFormat formatoFormateado = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Parsea la fecha original y la formatea
            Date fecha = formatoOriginal.parse(fechaOriginal);
            return formatoFormateado.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return fechaOriginal; // Devuelve la fecha original si hay un error
        }
    }
}
