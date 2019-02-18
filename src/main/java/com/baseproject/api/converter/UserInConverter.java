package com.baseproject.api.converter;

import com.baseproject.api.dto.UserDTO;
import com.baseproject.api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UserInConverter implements Converter<UserDTO, User> {

    private PasswordEncoder passWordEncoder;

    @PostConstruct
    void init() { passWordEncoder = new BCryptPasswordEncoder(); }

    @Override
    public User convert(UserDTO source) {
        User user = new User();
        user.enable();
        user.setCode(UUID.randomUUID());
        user.setName(source.getName());
        user.setUsername(source.getUsername());
        user.setPassword(passWordEncoder.encode(source.getPassword()));
        user.setEmail(source.getEmail());
        return user;
    }
}
