package com.example.library_system.Controller.User;

import com.example.library_system.Entity.Users;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PostUsersController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        try {
            Users createdUser = userService.createUser(user);
            return ResponseEntity.status(201).body(createdUser);  // HTTP 201 Created
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build(); // HTTP 400 Bad Request
        }
    }
}
