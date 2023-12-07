package com.shubham.swapsie.service.impl;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.LoginRequest;
import com.shubham.swapsie.model.User;
import com.shubham.swapsie.repository.UserRepository;
import com.shubham.swapsie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id : "+id+" does not exist"));
    }

    @Override
    public User updateUser(long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("User with id : "+id+" does not exist"));
        user.setFName(updatedUser.getFName());
        user.setLName(updatedUser.getLName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public String deleteUser(long id) {
        User user = userRepository.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("User with id : "+id+" does not exist"));
        userRepository.delete(user);
        return "User "+user.getFName()+" "+user.getLName()+" with id : "+user.getId()+" deleted successfully";
    }

    @Override
    public User login(LoginRequest loginRequest) {

        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if(user == null)
            throw new RuntimeException("Did not find user with these credentials");
        return user;
    }


}
