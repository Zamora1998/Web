package com.example.web.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface ApiService {
    @PUT("UsuariosF/ValidarLogin")
    Call<ApiResponse> validarlogin(@Body Usuario usuario);
    @POST("UsuariosF/RegistrarUsuario")
    Call<ApiResponse> registrarUsuario(@Body Registrousuario registroUsuario);
    @GET
    Call<DetallesPelicula> obtenerDetallesPelicula(@Url String urlCompleta);

}

