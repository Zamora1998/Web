package com.example.web.model;

// ComentarioAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.web.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder> {

    private List<Comentario> comentariosList;

    public ComentarioAdapter(List<Comentario> comentariosList) {
        this.comentariosList = comentariosList;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario comentario = comentariosList.get(position);

        String fechaFormateada = formatearFecha(comentario.getFechaComentario());
        holder.textViewFechaComentario.setText("Fecha: " + fechaFormateada);
        holder.textViewComentario.setText("Comentario: " + comentario.getComentario());
    }

    @Override
    public int getItemCount() {
        return comentariosList.size();
    }

    public static class ComentarioViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFechaComentario;
        TextView textViewComentario;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFechaComentario = itemView.findViewById(R.id.textViewFechaComentario);
            textViewComentario = itemView.findViewById(R.id.textViewComentario);
        }
    }
    private String formatearFecha(String fechaOriginal) {
        try {
            SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

            SimpleDateFormat formatoFormateado = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            Date fecha = formatoOriginal.parse(fechaOriginal);
            return formatoFormateado.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return fechaOriginal;
        }
    }
}
