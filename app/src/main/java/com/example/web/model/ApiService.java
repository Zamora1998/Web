package com.example.web.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface ApiService {
    @FormUrlEncoded
    @POST("UsuariosF/ValidarLogin")
    Call<ApiResponse> validarlogin(
            @Field("username") String username,
            @Field("password") String password
    );
    @POST("UsuariosF/RegistrarUsuario")
    Call<ApiResponse> registrarUsuario(@Body Registrousuario registroUsuario);
}

