package com.example.library_system.mapper;

import com.example.library_system.Dto.UserDTO;
import com.example.library_system.Entity.Users;

public class UserMapper {
    public static UserDTO toDTO(Users user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRegistrationDate(user.getRegistrationDate());
        return dto;
    }
}

