package com.example.library_system.Controller.User;

import com.example.library_system.Entity.Users;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PostUsersController {
    @Autowired
    UserService userService;
    @PostMapping()
    public Users createUser(@RequestBody Users user){
        return userService.createUser(user);
    }
}
