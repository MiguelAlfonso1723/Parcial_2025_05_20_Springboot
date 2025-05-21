package com.example.demo.service;


import com.example.demo.dtos.UserDTO;
import com.example.demo.logic.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }


    public User addUser(User user){
        return userRepository.save(user);
    }

    public User deleteUser(Integer id){
        User product = userRepository.findById(id).get();
        userRepository.delete(product);
        return product;
    }

    public User updateUser(Integer id, User product){
        if(userRepository.findById(id).isPresent()){
            User auxUser =  userRepository.findById(id).get();
            auxUser.setName(product.getName());
            auxUser.setEmail(product.getEmail());
            auxUser.setTelephone(product.getTelephone());
            auxUser.setActive(product.isActive());
            auxUser.setType(product.getType());
            return userRepository.save(auxUser);
        }else{
            return null;
        }

    }

    public List<User> userByName(String name){
        return userRepository.findByName(name);
    }

    public User getUserByName(String name){
        return userRepository.getUserByName(name);
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public List<UserDTO> getFieldsUser(){
        return userRepository.getFieldsUsers();
    }
}
