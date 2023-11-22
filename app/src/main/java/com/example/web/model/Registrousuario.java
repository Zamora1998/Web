package com.example.web.model;

import com.google.gson.annotations.SerializedName;

public class Registrousuario {

    @SerializedName("nombreUsuario")
    private String nombreUsuario;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellidos")
    private String apellidos;

    @SerializedName("email")
    private String email;

    @SerializedName("contrasena")
    private String contrasena;

    public Registrousuario(String nombreUsuario, String nombre, String apellidos, String email, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
    }
}
