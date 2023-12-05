package com.example.web.model;

public class FormularioValidator {

    public static boolean validarNombreUsuario(String nombreUsuario) {

        return nombreUsuario.matches("[a-zA-Z0-9]+") && nombreUsuario.length() >= 5;
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-Z]+") && nombre.length() >= 5;
    }

    public static boolean validarApellidos(String apellidos) {
        return !apellidos.isEmpty() && apellidos.length() >= 5;
    }

    public static boolean validarCorreoElectronico(String correoElectronico) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correoElectronico).matches();
    }
}
