package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Users;
import com.example.library_system.Exception.*;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceIMPL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getUserByEmail(String email) {
        List<Users> users = userRepository.findByEmailContainingIgnoreCase(email);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with email containing: " + email);
        }
        return users;
    }

    @Override
    @Transactional
    public Users createUser(Users user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException(user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = userRepository.findAllUsersCustom();

        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException("No users found in the system");
        }

        return users;
    }

    @Override
    @Transactional
    public boolean updateUserWithQuery(Long userId, Users user) {
        // Verify user exists first
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        // Check if new email conflicts with existing users
        if (user.getEmail() != null) {
            Users existingUserWithEmail = userRepository.findByEmail(user.getEmail());
            if (existingUserWithEmail != null && !existingUserWithEmail.getUserId().equals(userId)) {
                throw new DuplicateEmailException(user.getEmail());
            }
        }

        int rowsUpdated = userRepository.updateUserById(
                userId,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return rowsUpdated > 0;
    }
}