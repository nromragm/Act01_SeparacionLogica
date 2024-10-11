package com.es.repository;

import com.es.model.User;
import com.es.utils.EncryptUtil;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserRepositoryAPI {

    ArrayList<User> bddUsers;

    public UserRepository() {
        this.bddUsers = new ArrayList<User>();
        bddUsers.add(new User("1", "admin@gmail.com", "admin", EncryptUtil.encryptPassword("admin"), true));
        bddUsers.add(new User("2", "normal@gmail.com", "Paco", EncryptUtil.encryptPassword("1234"), false));
    }

    @Override
    public User getUser(String email) {

        return bddUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

    }


    @Override
    public User insertUser(User u){
        bddUsers.add(u);

        return u;
    }


    @Override
    public User updateUser(String email, User updatedUser) {
        User user = bddUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user != null) {
            int index = bddUsers.indexOf(user);
            bddUsers.set(index, updatedUser);
            return user;
        }

        return null;
    }

    @Override
    public boolean deleteUser(String email) {
        User user = bddUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (user != null) {
            bddUsers.remove(user);
            return true;
        }

        return false;
    }

}
