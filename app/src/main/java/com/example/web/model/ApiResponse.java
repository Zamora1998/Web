package com.example.web.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse {  // Agregué <T> para indicar que puede tener un parámetro de tipo

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
