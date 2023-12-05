package com.example.web.model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static final String BASE_URL = "https://tiusr30pl.cuc-carrera-ti.ac.cr/APIV7/api/"; // URL base de tu API

    private static Retrofit retrofit = null;

    public static Retrofit getURL() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
