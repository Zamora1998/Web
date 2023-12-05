package com.example.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.web.model.ActorAdapter;
import com.example.web.model.ApiService;
import com.example.web.model.ComentarioAdapter;
import com.example.web.model.DetallesPelicula;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallesPeliculaActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://tiusr30pl.cuc-carrera-ti.ac.cr/APIV5/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        Intent intent = getIntent();
        if (intent.hasExtra("nombrePelicula")) {
            String nombrePelicula = intent.getStringExtra("nombrePelicula");

            TextView textViewNombrePelicula = findViewById(R.id.textViewNombrePelicula);
            textViewNombrePelicula.setText(nombrePelicula);

            obtenerDetallesPelicula(nombrePelicula);
        }
    }

    private void obtenerDetallesPelicula(String nombrePelicula) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        String urlCompleta = "api/PeliculaDetalle/ObtenerDetallesPorNombre?nombrePelicula=" + nombrePelicula;
        Call<DetallesPelicula> call = apiService.obtenerDetallesPelicula(urlCompleta);

        call.enqueue(new Callback<DetallesPelicula>() {
            @Override
            public void onResponse(Call<DetallesPelicula> call, Response<DetallesPelicula> response) {
                if (response.isSuccessful() || response.code() == 200) {
                    DetallesPelicula detallesPelicula = response.body();
                    mostrarDetallesPelicula(detallesPelicula);
                    Log.d("DetallesPeliculaActivity", "Detalles de la película: " + detallesPelicula.getCalificacionGeneral());
                } else if (response.code() == 404) {
                    Log.d("DetallesPeliculaActivity", "Película no encontrada (404)");
                } else {
                    Log.e("DetallesPeliculaActivity", "Error en la respuesta de la API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DetallesPelicula> call, Throwable t) {
                Log.e("DetallesPeliculaActivity", "Error en la llamada a la API: " + t.getMessage(), t);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void mostrarDetallesPelicula(DetallesPelicula detallesPelicula) {
        try {
            ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
            TextView textViewNombrePelicula = findViewById(R.id.textViewNombrePelicula);
            TextView textViewResena = findViewById(R.id.textViewResena);
            TextView textViewCalificacion = findViewById(R.id.textViewCalificacion);
            TextView textViewFechaLanzamiento = findViewById(R.id.textViewFechaLanzamiento);
            RecyclerView recyclerViewActores = findViewById(R.id.recyclerViewActores);
            RecyclerView recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios);

            Glide.with(this)
                    .load(detallesPelicula.getRutaPoster())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageViewPoster);

            textViewNombrePelicula.setText(detallesPelicula.getNombre());
            textViewResena.setText("Reseña: " + detallesPelicula.getResena());
            textViewCalificacion.setText("Calificación: " + detallesPelicula.getCalificacionGeneral());
            textViewFechaLanzamiento.setText("Fecha de Lanzamiento: " + formatearFecha(detallesPelicula.getFechaLanzamiento()));

            LinearLayoutManager layoutManagerActores = new LinearLayoutManager(this);
            recyclerViewActores.setLayoutManager(layoutManagerActores);
            recyclerViewActores.setHasFixedSize(true);

            LinearLayoutManager layoutManagerComentarios = new LinearLayoutManager(this);
            recyclerViewComentarios.setLayoutManager(layoutManagerComentarios);
            recyclerViewComentarios.setHasFixedSize(true);

            ActorAdapter actorAdapter = new ActorAdapter(detallesPelicula.getActoresStaff());
            recyclerViewActores.setAdapter(actorAdapter);

            ComentarioAdapter comentarioAdapter = new ComentarioAdapter(detallesPelicula.getComentarios());
            recyclerViewComentarios.setAdapter(comentarioAdapter);
        } catch (Exception e) {
            Log.e("DetallesPeliculaActivity", "Error en la respuesta de la API MOSTRAR DETALLES", e);

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
