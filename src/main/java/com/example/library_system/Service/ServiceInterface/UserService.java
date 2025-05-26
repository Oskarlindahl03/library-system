package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Entity.Users;

import java.util.List;

public interface UserService {
    List<Users> getUserByEmail(String email);

    Users createUser(Users user);

    List<Users> getAllUsers();

    boolean updateUserWithQuery(Long userId, Users user);
}
