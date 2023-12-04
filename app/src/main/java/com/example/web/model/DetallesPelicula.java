package com.example.web.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetallesPelicula {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("resena")
    private String resena;

    @SerializedName("calificacionGeneral")
    private float calificacionGeneral;

    @SerializedName("fechaLanzamiento")
    private String fechaLanzamiento;

    @SerializedName("rutaPoster")
    private String rutaPoster;

    @SerializedName("comentarios")
    private List<Comentario> comentarios;

    @SerializedName("actoresStaff")
    private List<ActorStaff> actoresStaff;

    // getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public float getCalificacionGeneral() {
        return calificacionGeneral;
    }

    public void setCalificacionGeneral(float calificacionGeneral) {
        this.calificacionGeneral = calificacionGeneral;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getRutaPoster() {
        return rutaPoster;
    }

    public void setRutaPoster(String rutaPoster) {
        this.rutaPoster = rutaPoster;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<ActorStaff> getActoresStaff() {
        return actoresStaff;
    }

    public void setActoresStaff(List<ActorStaff> actoresStaff) {
        this.actoresStaff = actoresStaff;
    }
}

class Comentario {

    @SerializedName("comentarioID")
    private int comentarioID;

    @SerializedName("peliculaID")
    private String peliculaID;

    @SerializedName("usuarioID")
    private String usuarioID;

    @SerializedName("comentario")
    private String comentario;

    @SerializedName("fechaComentario")
    private String fechaComentario;

    // getters y setters
}

class ActorStaff {

    @SerializedName("nombreActor")
    private String nombreActor;

    @SerializedName("rol")
    private String rol;


}
