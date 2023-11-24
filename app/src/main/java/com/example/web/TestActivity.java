package com.example.web;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.web.model.PeliculaModel;
import com.example.web.model.PeliculaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.example.web.model.PeliculaAdapter;
import java.util.List;
import androidx.appcompat.widget.Toolbar;

public class TestActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://tiusr30pl.cuc-carrera-ti.ac.cr/APIV5/api/";
    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testactivity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        peliculaAdapter = new PeliculaAdapter();
        recyclerView.setAdapter(peliculaAdapter);

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
                    if (peliculas != null && !peliculas.isEmpty()) {
                        peliculaAdapter.setPeliculas(peliculas);
                        peliculaAdapter.notifyDataSetChanged();
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
