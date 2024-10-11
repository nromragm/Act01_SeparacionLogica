package com.es.repository;

import com.es.model.User;


public interface UserRepositoryAPI {
    User getUser(String email);

    User insertUser(User u);

    User updateUser(String email, User updatedUser);

    boolean deleteUser(String email);
}
