package com.example.web;

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

        // Obtener el nombre de la película enviado desde ListaPeliculasActivity
        Intent intent = getIntent();
        if (intent.hasExtra("nombrePelicula")) {
            String nombrePelicula = intent.getStringExtra("nombrePelicula");

            // Obtener el TextView del layout
            TextView textViewNombrePelicula = findViewById(R.id.textViewNombrePelicula);

            // Mostrar el nombre de la película en el TextView
            textViewNombrePelicula.setText(nombrePelicula);

            // Realizar la llamada a la API
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

        // Utiliza la URL completa incluyendo el nombre de la película como parámetro de la Query
        String urlCompleta = "api/PeliculaDetalle/ObtenerDetallesPorNombre?nombrePelicula=" + nombrePelicula;
        Call<DetallesPelicula> call = apiService.obtenerDetallesPelicula(urlCompleta);

        call.enqueue(new Callback<DetallesPelicula>() {
            @Override
            public void onResponse(Call<DetallesPelicula> call, Response<DetallesPelicula> response) {
                if (response.isSuccessful() || response.code() == 200) {
                    // El código de respuesta es 200 (OK), mostrar detalles
                    DetallesPelicula detallesPelicula = response.body();
                    mostrarDetallesPelicula(detallesPelicula);
                } else if (response.code() == 404) {
                    // El código de respuesta es 404 (Not Found), manejar el caso de película no encontrada
                    Log.d("DetallesPeliculaActivity", "Película no encontrada (404)");
                    // Puedes mostrar un mensaje de error o realizar alguna otra acción
                } else {
                    // Otro código de respuesta, manejar según sea necesario
                    Log.e("DetallesPeliculaActivity", "Error en la respuesta de la API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DetallesPelicula> call, Throwable t) {
                // Manejar el caso en el que la llamada a la API falla
                Log.e("DetallesPeliculaActivity", "Error en la llamada a la API: " + t.getMessage(), t);
            }
        });
    }

    private void mostrarDetallesPelicula(DetallesPelicula detallesPelicula) {
        try {
            ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
            TextView textViewNombrePelicula = findViewById(R.id.textViewNombrePelicula);
            TextView textViewResena = findViewById(R.id.textViewResena);
            TextView textViewCalificacion = findViewById(R.id.textViewCalificacion);
            TextView textViewFechaLanzamiento = findViewById(R.id.textViewFechaLanzamiento);
            RecyclerView recyclerViewActores = findViewById(R.id.recyclerViewActores);
            RecyclerView recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios);

            // Cargar la imagen usando Glide
            Glide.with(this)
                    .load(detallesPelicula.getRutaPoster())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageViewPoster);

            // Actualizar los TextView con los detalles de la película
            textViewNombrePelicula.setText(detallesPelicula.getNombre());
            textViewResena.setText("Reseña: " + detallesPelicula.getResena());
            textViewCalificacion.setText("Calificación: " + detallesPelicula.getCalificacionGeneral());
            textViewFechaLanzamiento.setText("Fecha de Lanzamiento: " + formatearFecha(detallesPelicula.getFechaLanzamiento()));

            // Configurar el RecyclerView de actores
            LinearLayoutManager layoutManagerActores = new LinearLayoutManager(this);
            recyclerViewActores.setLayoutManager(layoutManagerActores);
            recyclerViewActores.setHasFixedSize(true);

            // Configurar el RecyclerView de comentarios
            LinearLayoutManager layoutManagerComentarios = new LinearLayoutManager(this);
            recyclerViewComentarios.setLayoutManager(layoutManagerComentarios);
            recyclerViewComentarios.setHasFixedSize(true);

            // Actualizar el RecyclerView de actores
            ActorAdapter actorAdapter = new ActorAdapter(detallesPelicula.getActoresStaff());
            recyclerViewActores.setAdapter(actorAdapter);

            // Actualizar el RecyclerView de comentarios
            ComentarioAdapter comentarioAdapter = new ComentarioAdapter(detallesPelicula.getComentarios());
            recyclerViewComentarios.setAdapter(comentarioAdapter);

        } catch (Exception e) {
            // Imprimir el stack trace de la excepción
            Log.e("DetallesPeliculaActivity", "Error en la respuesta de la API MOSTRAR DETALLES", e);
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
