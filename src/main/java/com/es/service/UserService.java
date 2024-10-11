package com.es.service;

import com.es.model.User;
import com.es.repository.UserRepository;
import com.es.utils.EncryptUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    // Expresión regular para validar un formato de email.
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Expresión regular para validar un formato de nombre (solo letras, máximo 20 caracteres).
    private static final String NAME_REGEX = "^[a-zA-Z]{1,20}$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Valida si el email tiene el formato correcto y si ya existe en el sistema.
    public boolean validarEmail(String email) {
        if (email == null || email.length() > 20) {
            return false;
        }

        if (getUser(email) != null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email.trim());
        return matcher.matches();
    }

    // Valida si el nombre cumple con el formato especificado (solo letras y longitud máxima de 20).
    public static boolean validarNombre(String name) {
        if (name == null) {
            return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(name.trim());
        return matcher.matches();
    }

    // Valida si la contraseña no está vacía y tiene una longitud menor a 20 caracteres.
    public boolean validarPassword(String password) {
        return !password.isEmpty() && password.length() < 20;
    }

    // Verifica si el login es exitoso comparando email y contraseña encriptada.
    public boolean login(String email, String password) {

        User u = userRepository.getUser(email);

        if (u == null) return false;

        String passEncrypted = EncryptUtil.encryptPassword(password);

        return email.equals(u.getEmail()) && passEncrypted.equals(u.getPassword());
    }

    // Devuelve un usuario si existe, o null si el email es inválido o no está registrado.

    public User getUser(String email){
        if (email == null || email.isEmpty()) return null;

        return userRepository.getUser(email);

    }

    // Inserta un nuevo usuario en el sistema si el nombre, email y contraseña son válidos.
    public User insertUser(String id, String email, String nombre, String password, boolean isAdmin) {

        if (validarNombre(nombre) && validarEmail(email) && validarPassword(password)) {

            User u = new User(id, email, nombre, EncryptUtil.encryptPassword(password), isAdmin);
            userRepository.insertUser(u);

            return u;
        } else return null;
    }


    // Elimina un usuario del sistema por su email y devuelve el usuario eliminado.
    public User deleteUser(String email) {
        if (email == null || email.isEmpty()) return null;

        User u = userRepository.getUser(email);

        userRepository.deleteUser(email);

        return u;
    }

    // Actualiza la información de un usuario existente si los datos son válidos.
    public User updateUser(String oldEmail, String id, String email, String nombre, String password, boolean isAdmin) {
        if (validarNombre(nombre) && validarEmail(email) && validarPassword(password)) {
            User u = new User(id, email, nombre, EncryptUtil.encryptPassword(password), isAdmin);

            return userRepository.updateUser(oldEmail, u);

        } else return null;
    }
}
