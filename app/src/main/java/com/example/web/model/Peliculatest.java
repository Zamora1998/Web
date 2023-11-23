package com.example.web.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Peliculatest {

    @SerializedName("ActoresStaff")
    private ActoresStaff actoresStaff;

    @SerializedName("CalificacionGenerQal")
    private int calificacionGenerQal;

    @SerializedName("FechaLanzamiento")
    private String fechaLanzamiento;

    @SerializedName("Nombre")
    private String nombre;

    @SerializedName("PeliculaID")
    private int peliculaID;

    @SerializedName("Resena")
    private String resena;

    @SerializedName("RutaPoster")
    private String rutaPoster;

    public Peliculatest(ActoresStaff actoresStaff, int calificacionGenerQal, String fechaLanzamiento,
                         String nombre, int peliculaID, String resena, String rutaPoster) {
        this.actoresStaff = actoresStaff;
        this.calificacionGenerQal = calificacionGenerQal;
        this.fechaLanzamiento = fechaLanzamiento;
        this.nombre = nombre;
        this.peliculaID = peliculaID;
        this.resena = resena;
        this.rutaPoster = rutaPoster;
    }

    public ActoresStaff getActoresStaff() {
        return actoresStaff;
    }

    public int getCalificacionGenerQal() {
        return calificacionGenerQal;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeliculaID() {
        return peliculaID;
    }

    public String getResena() {
        return resena;
    }

    public String getRutaPoster() {
        return rutaPoster;
    }

    public static class ActoresStaff {

        @SerializedName("ActorConRol")
        private List<ActorConRol> actorConRolList;

        public ActoresStaff(List<ActorConRol> actorConRolList) {
            this.actorConRolList = actorConRolList;
        }

        public List<ActorConRol> getActorConRolList() {
            return actorConRolList;
        }
    }

    public static class ActorConRol {

        @SerializedName("NombreActor")
        private String nombreActor;

        @SerializedName("Rol")
        private String rol;

        public ActorConRol(String nombreActor, String rol) {
            this.nombreActor = nombreActor;
            this.rol = rol;
        }

        public String getNombreActor() {
            return nombreActor;
        }

        public String getRol() {
            return rol;
        }
    }
}
