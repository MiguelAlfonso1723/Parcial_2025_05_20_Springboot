package com.example.demo.controller;

import com.example.demo.dtos.MaterialDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.logic.Loan;
import com.example.demo.logic.Material;
import com.example.demo.logic.User;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.service.LoanService;
import com.example.demo.service.MaterialService;
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

    @Autowired
    private MaterialService  materialService;

    @Autowired
    private LoanService loanService;

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
    public ResponseEntity<Object> updateUser(@PathVariable("id")  Integer id, @RequestBody User user){
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


    @GetMapping("/materials")
    public ResponseEntity<Object> materials() {

        try {
            List<Material> result = materialService.getMaterials();
            HttpStatus status = result == null ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/materials/{id}")
    public ResponseEntity<Object> materialById(@PathVariable("id") Integer id) {

        try {
            Material result = materialService.getMaterialById(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/materials")
    public ResponseEntity<Object>  createMaterial(@RequestBody Material material){
        try {
            if(materialService.getMaterialByTitle(material.getTitle())==null){
                Material result = materialService.addMaterial(material);
                HttpStatus status = result == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.ACCEPTED;
                return ResponseHandler.generateResponse("Creation Succes..", status, result);
            }else{

                return ResponseHandler.generateResponse("The name already exist", HttpStatus.NOT_FOUND, null);
            }

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Object> deleteMaterial(@PathVariable("id")  Integer id){
        try {
            Material result = materialService.deleteMaterial(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Delete Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Id don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/materials/{id}")
    public ResponseEntity<Object> updateMaterial(@PathVariable("id")  Integer id, @RequestBody Material material){
        try {
            Material result = materialService.updateMaterial(id, material);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/materials/name")
    public ResponseEntity<Object> materialByName(@RequestParam("title") String title){
        try {
            List<Material> result = materialService.materialByTiltle(title);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/materials/fields")
    public ResponseEntity<Object> getFieldsMaterials(){
        try {
            List<MaterialDTO> result = materialService.getFieldsMaterial();
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/loans")
    public ResponseEntity<Object> loans() {

        try {
            loanService.actualizeState();
            List<Loan> result = loanService.loanList();
            HttpStatus status = result == null ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/loans/{id}")
    public ResponseEntity<Object> loanById(@PathVariable("id") Integer id) {

        try {
            Loan result = loanService.getLoanById(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Search Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/loans")
    public ResponseEntity<Object>  createLoan(@RequestBody Loan loan){
        try {
            Loan result = loanService.addLoan(loan);
            HttpStatus status = result == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Creation Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Object> deleteLoan(@PathVariable("id")  Integer id){
        try {
            Loan result = loanService.getLoanById(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Delete Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Id don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<Object> updateLoan(@PathVariable("id")  Integer id, @RequestBody Loan loan){
        try {
            Loan result = loanService.updateLoan(loan, id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/loans/retrased")
    public ResponseEntity<Object> loanRestrased(){
        try {
            List<Loan> result = loanService.retrasedLoans();
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/loans/delivered")
    public ResponseEntity<Object> loanDelivered(){
        try {
            List<Loan> result = loanService.deliveredLoans();
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object>  deliver(Integer id){
        try {
            Loan result = loanService.deliver(id);
            HttpStatus status = result ==null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED;
            return ResponseHandler.generateResponse("Update Succes..", status, result);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage() + "\nThe Name don't exist", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }




}
