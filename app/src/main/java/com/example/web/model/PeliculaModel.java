package com.example.web.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
public class PeliculaModel {
    @SerializedName("peliculaID")
    private int peliculaID;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("resena")
    private String resena;

    @SerializedName("calificacionGenerQal")
    private int calificacionGenerQal;

    @SerializedName("fechaLanzamiento")
    private String fechaLanzamiento;

    @SerializedName("rutaPoster")
    private String rutaPoster;

    @SerializedName("actoresStaff")
    private List<ActorModel> actoresStaff;

    @SerializedName("posterID")
    private String posterID;


    public int getPeliculaID() {
        return peliculaID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResena() {
        return resena;
    }

    public int getCalificacionGenerQal() {
        return calificacionGenerQal;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getRutaPoster() {
        return rutaPoster;
    }

    public List<ActorModel> getActoresStaff() {
        return actoresStaff;
    }

    public String getPosterID() {
        return posterID;
    }
}
