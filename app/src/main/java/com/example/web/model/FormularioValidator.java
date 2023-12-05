package com.example.web.model;

public class FormularioValidator {

    public static boolean validarNombreUsuario(String nombreUsuario) {

        return nombreUsuario.matches("[a-zA-Z0-9]+") && nombreUsuario.length() >= 5;
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-Z]+") && nombre.length() >= 5;
    }

    public static boolean validarApellidos(String apellidos) {
        // Validar que los apellidos no estén vacíos y tengan una longitud mínima de 5 caracteres
        return !apellidos.isEmpty() && apellidos.length() >= 5;
    }

    public static boolean validarCorreoElectronico(String correoElectronico) {
        // Utilizar la validación incorporada de Android para direcciones de correo electrónico
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correoElectronico).matches();
    }
}
