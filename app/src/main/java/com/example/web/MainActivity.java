package com.example.web;
import com.google.android.material.snackbar.Snackbar;
import android.content.Intent;  // Importa la clase Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.web.model.ApiResponse;
import com.example.web.model.ApiService;
import com.example.web.model.ApiClient;
import com.example.web.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private EditText editTextCorreo;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiClient.getURL().create(ApiService.class);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = editTextCorreo.getText().toString();
                String password = editTextPassword.getText().toString();

                if (correo.isEmpty() || password.isEmpty()) {
                    // Muestra un mensaje de error si alguno de los campos está vacío
                    showSnackbar("Por favor, completa todos los campos.");
                    return;
                }

                Usuario user = new Usuario(correo, password);

                Call<ApiResponse> call = apiService.validarlogin(user);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            ApiResponse apiResponse = response.body();
                            int statusCode = response.code();
                            if (statusCode == 200) {
                                // El código es 200, redirige a la TestActivity
                                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (statusCode == 404) {
                                // El código es 404, muestra el mensaje de usuario/contraseña incorrectos
                                Log.e("MainActivity", "Usuario y/o Contraseña Incorrectos: " + statusCode);
                                showSnackbar("Usuario y/o Contraseña Incorrectos");
                            }
                        } else {
                            int errorCode = response.code();
                            // Haz algo con el código de error
                            showSnackbar("Usuario/Contraseña Incorrectos");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Manejar el fallo de la solicitud aquí
                        showSnackbar("Error en la conexión: " + t.getMessage());
                    }
                });
            }
        });

        // Agregar código para el botón de registro
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para mostrar un Snackbar
    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}