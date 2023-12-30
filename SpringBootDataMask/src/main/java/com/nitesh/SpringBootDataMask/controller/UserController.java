package com.nitesh.SpringBootDataMask.controller;

import com.nitesh.SpringBootDataMask.dto.User;
import com.nitesh.SpringBootDataMask.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private Userservice userservice;

    public UserController(Userservice userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userservice.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") int id){
        return userservice.getUser(id);
    }
}
