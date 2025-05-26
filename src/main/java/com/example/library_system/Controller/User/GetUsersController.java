package com.example.library_system.Controller.User;

import com.example.library_system.Dto.UserDTO;
import com.example.library_system.Entity.Users;
import com.example.library_system.mapper.UserMapper;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class GetUsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/email/{email}")
    public List<UserDTO> getUsersByEmail(@PathVariable String email) {
        List<Users> users = userService.getUserByEmail(email);
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}

