package com.example.web.model;

import com.google.gson.annotations.SerializedName;


public class Usuario {
    @SerializedName("nombreUsuario")
    private String nombreUsuario;

    @SerializedName("contrasena")
    private String contrasena;

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }
}
