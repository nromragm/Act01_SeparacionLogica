package com.es.utils;

import com.es.controller.UserController;
import com.es.model.RespuestaHTTP;
import com.es.model.User;

import java.util.Scanner;

public class MenuUtil {

    private static final Scanner scanner = new Scanner(System.in);

    // Lanza el menu
    public static void lanzarMenu(UserController controller) {
        System.out.println("Login: ");

        System.out.print("Ingresa tu correo electrónico: ");
        String email = scanner.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String password = scanner.nextLine();

        RespuestaHTTP respuesta = controller.login(email, password);

        if (respuesta.getCodigoRespuesta() == 200) {
            User u = controller.getUser(email).getUser();
            System.out.println(respuesta.getMensajeRespuesta());
            mostrarMainMenu(u, controller);
        } else {
            System.out.println("ERROR: Inicio de sesión fallido. Por favor, verifica tus credenciales.");
        }
    }

    // Muestra el menu principal
    private static void mostrarMainMenu(User u, UserController controller) {
        String opcion = "";

        while (!opcion.equals("0")) {
            if (u.isAdmin()) {
                showAdminOptions();
                opcion = scanner.nextLine();
                handleAdminSelection(opcion, controller);
            } else {
                showUserOptions();
                opcion = scanner.nextLine();
                handleUserSelection(opcion, controller);
            }
        }
    }

    // Menú de administrador
    private static void showAdminOptions() {
        System.out.println("""
                MENÚ PRINCIPAL (Admin):
                \t1. CONSULTAR USUARIO
                \t2. MODIFICAR USUARIO
                \t3. ELIMINAR USUARIO
                \t4. CREAR USUARIO
                \t0. CERRAR SESIÓN
                """);
    }

    // Menú de usuario regular
    private static void showUserOptions() {
        System.out.println("""
                MENÚ PRINCIPAL:
                \t1. CONSULTAR USUARIO
                \t0. CERRAR SESIÓN
                """);
    }

    // Manejo de opciones del administrador
    private static void handleAdminSelection(String option, UserController controller) {
        switch (option) {
            case "1" -> viewUser(controller);
            case "2" -> editUser(controller);
            case "3" -> deleteUser(controller);
            case "4" -> createUser(controller);
            case "0" -> System.out.println("Has cerrado sesión.");
            default -> System.out.println("ERROR: Selecciona una opción válida entre 0 y 4.");
        }
    }

    // Manejo de opciones del usuario regular
    private static void handleUserSelection(String option, UserController controller) {
        switch (option) {
            case "1" -> viewUser(controller);
            case "0" -> System.out.println("Has cerrado sesión.");
            default -> System.out.println("ERROR: Selecciona una opción válida entre 0 y 4.");
        }
    }

    // Ver detalles del usuario
    private static void viewUser(UserController controller) {
        System.out.print("Introduce el correo del usuario que deseas consultar: ");
        String userEmail = scanner.nextLine();

        RespuestaHTTP response = controller.getUser(userEmail);
        if (response.getCodigoRespuesta() == 200) {
            System.out.println(response.getUser());
        } else {
            System.out.println(response.getMensajeRespuesta());
        }
    }

    // Editar usuario existente
    private static void editUser(UserController controller) {
        System.out.print("Escribe el correo del usuario que deseas actualizar: ");
        String oldEmail = scanner.nextLine();

        RespuestaHTTP response = controller.getUser(oldEmail);
        if (response.getCodigoRespuesta() == 200) {

            System.out.print("Introduce el ID del usuario: ");
            String id = scanner.nextLine();

            System.out.print("Introduce el nombre del usuario: ");
            String nombre = scanner.nextLine();

            System.out.print("Introduce correo electrónico del usuario: ");
            String email = scanner.nextLine();

            System.out.print("Introduce la contraseña del usuario: ");
            String password = scanner.nextLine();

            System.out.print("Quieres que el usuario sea admin? (Si/No): ");
            String respuesta = scanner.nextLine();

            boolean isAdmin = respuesta.equalsIgnoreCase("si");

            RespuestaHTTP updateResponse = controller.updateUser(oldEmail, id, email, nombre, password, isAdmin);

            System.out.println(updateResponse.getMensajeRespuesta());

        } else {
            System.out.println("El correo introducido no existe.");
            System.out.println(response.getMensajeRespuesta());
        }
    }

    // Eliminar un usuario
    private static void deleteUser(UserController controller) {
        System.out.print("Introduce el correo del usuario que deseas eliminar: ");
        String email = scanner.nextLine();

        if (controller.deleteUser(email)) {
            System.out.println("Usuario eliminado correctamente");
        } else {
            System.out.println("Erro al eliminar el usuario");
        }
    }

    // Crear un nuevo usuario
    private static void createUser(UserController controller) {
        System.out.print("Nuevo ID del usuario: ");
        String id = scanner.nextLine();

        System.out.print("Nuevo nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Correo electrónico del nuevo usuario: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña del nuevo usuario: ");
        String passwod = scanner.nextLine();

        System.out.print("Quieres que el usuario sea admin? (Si/No): ");
        String respuesta = scanner.nextLine();

        boolean isAdmin = respuesta.equalsIgnoreCase("si");

        RespuestaHTTP createResponse = controller.insertUser(id, email, nombre, passwod, isAdmin);

        System.out.println(createResponse.getMensajeRespuesta());
    }
}
