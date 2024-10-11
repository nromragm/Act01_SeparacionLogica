package com.es.controller;

import com.es.model.RespuestaHTTP;
import com.es.model.User;
import com.es.service.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RespuestaHTTP login(String email, String password) {
        try {

            if (email == null || email.isEmpty()) {
                return new RespuestaHTTP(400,"Bad Request");
            }
            if (password == null || password.isEmpty()) {
                return new RespuestaHTTP(400,"Bad Request");
            }

            boolean respuestaService = userService.login(email, password);

            if (respuestaService) {
                return new RespuestaHTTP(200, "Logueado correctamente");

            } else return new RespuestaHTTP(403, "No autorizado");

        } catch (Exception e) {
            return new RespuestaHTTP(500, "Error");
        }
    }

    public RespuestaHTTP getUser(String email) {

        try {
            User u = userService.getUser(email);
            return u != null ?
                    new RespuestaHTTP(200, "TODO OK", u) :
                    new RespuestaHTTP(400, "Bad Request");

        } catch (Exception e) {
            return new RespuestaHTTP(500, "Fatal Internal Error");
        }
    }

    public RespuestaHTTP insertUser(String id, String email, String nombre, String password, boolean isAdmin) {
        try {
            User u = userService.insertUser(id, email, nombre, password, isAdmin);
            return u != null ?
                    new RespuestaHTTP(200, "TODO OK, Usuario creado correctamente", u) :
                    new RespuestaHTTP(400, "Bad Request");

        } catch (Exception e) {
            return new RespuestaHTTP(500, "Fatal Internal Error");
        }
    }

    public RespuestaHTTP updateUser(String oldEmail, String id, String email, String nombre, String password, boolean isAdmin) {
        try {
            User u = userService.updateUser(oldEmail, id, email, nombre, password, isAdmin);
            return u != null ?
                    new RespuestaHTTP(200, "TODO OK, Usuario actualizado correctamente", u) :
                    new RespuestaHTTP(400, "Bad Request");

        } catch (Exception e) {
            return new RespuestaHTTP(500, "Fatal Internal Error");
        }
    }

    public boolean deleteUser(String email) {
        try {
            User u = userService.deleteUser(email);
            return u != null;

        } catch (Exception e) {
            return false;
        }
    }
}
