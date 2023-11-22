package com.example.web.model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
public class usuario  implements  Serializable{
    @SerializedName("Usuario")
    public String user;
    @SerializedName("contrasena")
    public String password;

    public usuario (String user, String password)
    {
        this.password=password;
        this.user=user;
    }
}
