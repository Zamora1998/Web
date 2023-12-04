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

    public int getComentarioID() {
        return comentarioID;
    }

    public void setComentarioID(int comentarioID) {
        this.comentarioID = comentarioID;
    }

    public String getPeliculaID() {
        return peliculaID;
    }

    public void setPeliculaID(String peliculaID) {
        this.peliculaID = peliculaID;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
}

class ActorStaff {

    @SerializedName("nombreActor")
    private String nombreActor;

    @SerializedName("rol")
    private String rol;
    public String getNombreActor() {
        return nombreActor;
    }
    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}

