package com.example.web.model;

import com.google.gson.annotations.SerializedName;

public class ActorModel {
    @SerializedName("nombreActor")
    private String nombreActor;

    @SerializedName("rol")
    private String rol;

    public String getNombreActor() {
        return nombreActor;
    }

    public String getRol() {
        return rol;
    }
}
