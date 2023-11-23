
/*
package com.example.web;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.web.model.ApiResponse;
import com.example.web.model.ApiService;
import com.example.web.model.ApiClient;
import com.example.web.model.PeliculaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.material.snackbar.Snackbar;
public class HomeActivity extends AppCompatActivity {

    private ApiService apiService;

    private ImageView imageViewPoster;
    private TextView textViewNombre;
    private TextView textViewCalificacion;
    private TextView textViewResena;
    private TextView textViewActores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        apiService = ApiClient.getURL().create(ApiService.class);

        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewCalificacion = findViewById(R.id.textViewCalificacion);
        textViewResena = findViewById(R.id.textViewResena);
       // textViewActores = findViewById(R.id.textViewActores);

        // Hacer la solicitud para obtener detalles
        obtenerDetalles();
    }

    private void obtenerDetalles() {
        Call<ApiResponse> call = apiService.obtenerDetalles();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Obtener los detalles de la película desde la respuesta
                    PeliculaModel pelicula = (PeliculaModel) response.body().getPelicula();
                    updateUI(pelicula);
                } else {
                    int errorCode = response.code();
                    // Haz algo con el código de error
                    showSnackbar("Error en la solicitud, código: " + errorCode);
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                showSnackbar("Error en la conexión: " + t.getMessage());
            }
        });
    }

    private void updateUI(PeliculaModel pelicula) {
        if (pelicula != null) {
            // Cargar la imagen del póster usando Glide (puedes usar tu propia lógica de carga de imágenes)
            Glide.with(this).load(pelicula.getRutaPoster()).into(imageViewPoster);

            // Mostrar otros detalles en los TextView
            textViewNombre.setText(pelicula.getNombre());
            textViewCalificacion.setText(String.valueOf(pelicula.getCalificacionGenerQal()));
            textViewResena.setText(pelicula.getResena());

            // Construir el texto de los actores
            StringBuilder actoresText = new StringBuilder("Actores:\n");
            for (PeliculaModel.ActorConRol actorConRol : pelicula.getActoresStaff().getActorConRolList()) {
                actoresText.append(actorConRol.getNombreActor()).append(" - ").append(actorConRol.getRol()).append("\n");
            }
            textViewActores.setText(actoresText.toString());
        }
    }
    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}
*/