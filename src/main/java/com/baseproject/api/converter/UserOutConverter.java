package com.baseproject.api.converter;

import com.baseproject.api.dto.UserDTO;
import com.baseproject.api.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserOutConverter implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        UserDTO dto = new UserDTO();
        dto.setCode(user.getCode());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
