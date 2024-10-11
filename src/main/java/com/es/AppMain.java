package com.es;

import com.es.controller.UserController;
import com.es.repository.UserRepository;
import com.es.service.UserService;

import static com.es.utils.MenuUtil.lanzarMenu;


public class AppMain {
    public static void main(String[] args) {

        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);

        // Correo admin: admin@gmail.com
        // Contraseña admin: admin

        // Correo usuario normal: normal@gmail.com
        // Contraseña usuario normal: 1234

        lanzarMenu(controller);
    }
}