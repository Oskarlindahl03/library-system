package com.example.library_system.Controller.User;

import com.example.library_system.Entity.Users;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GetUsersController {
    @Autowired
    UserService userService;
    @GetMapping("/email/{email}")
    public List<Users> getUsersByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
}
