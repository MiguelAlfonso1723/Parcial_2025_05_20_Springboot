package com.example.demo.controller;

import com.example.demo.dtos.UserDTO;
import com.example.demo.logic.User;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class LibraryController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> users() {

        try {
            List<User> result = userService.getUsers();
            HttpStatus status = result == null ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> userById(@PathVariable("id") Integer id) {

        try {
            User result = userService.getUserById(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<Object>  createUser(@RequestBody User user){
        try {
            if(userService.getUserByName(user.getName())==null && userService.getUserByEmail(user.getEmail())==null ){
                User result = userService.addUser(user);
                HttpStatus status = result == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.ACCEPTED;
                return ResponseHandler.generateResponse("Creation Succes..", status, result);
            }else{
                System.out.println(user.getName());
                return ResponseHandler.generateResponse("The name already exist or The email already exist", HttpStatus.NOT_FOUND, null);
            }

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id")  Integer id){
        try {
            User result = userService.deleteUser(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Delete Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Id don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id")  Integer id, @RequestBody User user){
        try {
            User result = userService.updateUser(id, user);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/users/name")
    public ResponseEntity<Object> userByName(@RequestParam("name") String name){
        try {
            List<User> result = userService.userByName(name);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/users/fields")
    public ResponseEntity<Object> getFieldsUsers(){
        try {
            List<UserDTO> result = userService.getFieldsUser();
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
