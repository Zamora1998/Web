package com.example.web.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PeliculaService {
    @GET("PeliculasF/ObtenerDetalles")
    Call<List<PeliculaModel>> obtenerDetallesPeliculas();
}