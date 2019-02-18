package com.baseproject.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {

    private UUID code;

    private String name;

    private String username;

    private String email;

    private String password;

}
