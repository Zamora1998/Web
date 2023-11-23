package com.example.web;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import retrofit2.converter.gson.GsonConverterFactory;

import com.bumptech.glide.Glide;
import com.example.web.model.ApiResponse;
import com.example.web.model.ApiService;
import com.example.web.model.ApiClient;
import com.example.web.model.ActorModel;
import com.example.web.model.PeliculaModel;
import com.example.web.model.PeliculaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://tiusr30pl.cuc-carrera-ti.ac.cr/APIV5/api/";

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testactivity);

        imageView = findViewById(R.id.imageView); // Reemplaza con el ID correcto de tu ImageView
        textView = findViewById(R.id.textView); // Reemplaza con el ID correcto de tu TextView

        // Configuración de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creación de instancia del servicio
        PeliculaService peliculasService = retrofit.create(PeliculaService.class);

        // Realizar la solicitud
        Call<List<PeliculaModel>> call = peliculasService.obtenerDetallesPeliculas();
        call.enqueue(new Callback<List<PeliculaModel>>() {
            @Override
            public void onResponse(Call<List<PeliculaModel>> call, Response<List<PeliculaModel>> response) {
                if (response.isSuccessful()) {
                    List<PeliculaModel> peliculas = response.body();
                    // Suponemos que solo hay una película en la lista
                    if (peliculas != null && !peliculas.isEmpty()) {
                        PeliculaModel pelicula = peliculas.get(0);

                        // Cargar la imagen usando Glide
                        Glide.with(TestActivity.this)
                                .load(pelicula.getRutaPoster())
                                .into(imageView);

                        // Mostrar los datos de la película en el TextView
                        String datosPelicula = "Nombre: " + pelicula.getNombre() +
                                "\nReseña: " + pelicula.getResena() +
                                "\nCalificación General: " + pelicula.getCalificacionGenerQal() +
                                "\nFecha de Lanzamiento: " + pelicula.getFechaLanzamiento();
                        textView.setText(datosPelicula);
                    }
                } else {
                    // Manejar la respuesta de error
                    Log.e("TestActivity", "Error en la respuesta: " + response.code());
                    Log.e("TestActivity", "Mensaje de error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaModel>> call, Throwable t) {
                // Manejar el fallo en la solicitud
                Log.e("TestActivity", "Error en la solicitud: " + t.getMessage());
            }
        });
    }
}
