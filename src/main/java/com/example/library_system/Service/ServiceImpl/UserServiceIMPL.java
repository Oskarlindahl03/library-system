package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> getUserByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }
    @Override
    public Users createUser(Users user){
        return userRepository.save(user);
    }
    @Override
    public List<Users> getAllUsers() {
        List<Users> users = userRepository.findAllUsersCustom();

        if(users == null || users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        return users;
    }
    @Override
    public boolean updateUserWithQuery(Long userId, Users user) {
        int rowsUpdated = userRepository.updateUserById(
                userId,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return rowsUpdated > 0;
    }

}
