package com.nitesh.SpringBootFirebase.controller;

import com.nitesh.SpringBootFirebase.model.User;
import com.nitesh.SpringBootFirebase.service.Userservice;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    private Userservice userservice;

    public UserController(Userservice userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userservice.createUser(user);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return userservice.getUser(documentId);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) throws  InterruptedException, ExecutionException {
        return userservice.updateUser(user);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return userservice.deleteUser(documentId);
    }
}
