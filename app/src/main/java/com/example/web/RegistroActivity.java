package com.example.web;
import com.example.web.model.FormularioValidator;
import com.google.android.material.snackbar.Snackbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.web.model.ApiResponse;
import com.example.web.model.ApiService;
import com.example.web.model.ApiClient;
import com.example.web.model.Registrousuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private ApiService apiService;
    private EditText editTextNombreUsuario;
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registroactivity);

        apiService = ApiClient.getURL().create(ApiService.class);

        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCampos()) {
                    // Obtener los datos del usuario de tus EditText
                    String nombreUsuario = editTextNombreUsuario.getText().toString();
                    String nombre = editTextNombre.getText().toString();
                    String apellidos = editTextApellidos.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String contrasena = editTextPassword.getText().toString();

                    Registrousuario registroUsuario = new Registrousuario(nombreUsuario, nombre, apellidos, email, contrasena);

                    Call<ApiResponse> call = apiService.registrarUsuario(registroUsuario);

                    call.enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful() && response.code() == 201) {
                                // Registro exitoso, redirigir a MainActivity
                                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Cierra la actividad actual
                            } else {
                                int errorCode = response.code();
                                // Haz algo con el código de error
                                showSnackbar("Nombre de Usuario en uso : " + errorCode);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            showSnackbar("Error en la conexión: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }

    private boolean validarCampos() {
        String nombreUsuario = editTextNombreUsuario.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String apellidos = editTextApellidos.getText().toString();
        String email = editTextEmail.getText().toString();
        String contrasena = editTextPassword.getText().toString();

        if (!FormularioValidator.validarNombreUsuario(nombreUsuario)) {
            showSnackbar("Nombre de usuario no válido");
            return false;
        }

        if (!FormularioValidator.validarNombre(nombre)) {
            showSnackbar("Nombre no válido");
            return false;
        }

        if (!FormularioValidator.validarApellidos(apellidos)) {
            showSnackbar("Apellidos no válidos");
            return false;
        }

        if (!FormularioValidator.validarCorreoElectronico(email)) {
            showSnackbar("Correo electrónico no válido");
            return false;
        }

        // Puedes agregar más validaciones según tus necesidades

        return true;
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }


}
