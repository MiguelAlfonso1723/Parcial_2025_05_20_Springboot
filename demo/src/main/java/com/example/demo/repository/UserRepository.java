package com.example.demo.repository;


import com.example.demo.dtos.UserDTO;
import com.example.demo.logic.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%',:name,'%') ")
    public List<User> findByName(String name);

    @Query("SELECT new com.example.demo.dtos.UserDTO(name, email, telephone, active, type) FROM User")
    public List<UserDTO> getFieldsUsers();

    @Query("SELECT u FROM User u WHERE u.name = :name")
    public User getUserByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(String email);
}
