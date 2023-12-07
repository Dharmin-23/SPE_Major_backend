package com.shubham.swapsie.service;

import com.shubham.swapsie.model.LoginRequest;
import com.shubham.swapsie.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getUsers();

    User getUserById(long id);

    User updateUser(long id, User updatedUser);

    String deleteUser(long id);

    User login(LoginRequest loginRequest);
}
