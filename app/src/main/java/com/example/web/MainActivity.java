package com.example.web;

import android.content.Intent;  // Importa la clase Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.web.model.ApiResponse;
import com.example.web.model.ApiService;
import com.example.web.model.ApiClient;
import com.example.web.model.usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private EditText editTextCorreo;  // Cambiado para reflejar el EditText de correo electrónico
    private EditText editTextPassword; // Cambiado para reflejar el EditText de contraseña

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiClient.getURL().create(ApiService.class);
        editTextCorreo = findViewById(R.id.editTextCorreo); // Refleja el ID correcto
        editTextPassword = findViewById(R.id.editTextPassword); // Refleja el ID correcto

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos de usuario y contraseña de tus EditText
                String correo = editTextCorreo.getText().toString();
                String password = editTextPassword.getText().toString();

                usuario user = new usuario(correo, password);

                Call<ApiResponse> call = apiService.validarlogin(correo, password);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            ApiResponse apiResponse = response.body();
                            if (apiResponse != null && apiResponse.isSuccess()) {
                                // El inicio de sesión fue exitoso
                                String successMessage = apiResponse.getMessage();
                                // Haz algo con el mensaje de éxito
                            } else {
                                // El inicio de sesión falló
                                String errorMessage = apiResponse != null ? apiResponse.getMessage() : "Error desconocido";

                            }
                        } else {

                            int errorCode = response.code();
                            // Haz algo con el código de error
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Manejar el fallo de la solicitud aquí
                    }
                });
            }
        });

        // Agregar código para el botón de registro
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent para abrir la actividad de registro
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
