package com.example.library_system.Controller.User;

import com.example.library_system.Entity.Users;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PutUsersController {
    @Autowired
    UserService userService;

    @PutMapping("/update/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users user) {
        boolean updated = userService.updateUserWithQuery(userId, user);
        if (updated) {
            return ResponseEntity.ok().build(); // 200 OK with no body
        } else {
            return ResponseEntity.notFound().build(); // 404 if userId not found
        }
    }
}

