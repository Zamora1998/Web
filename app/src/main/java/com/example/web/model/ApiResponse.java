package com.example.web.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse<T> {  // Agregué <T> para indicar que puede tener un parámetro de tipo
    @SerializedName("pelicula")
    private T pelicula;  // Usé T como el tipo de la propiedad

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public T getPelicula() {
        return pelicula;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
