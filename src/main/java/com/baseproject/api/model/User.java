package com.baseproject.api.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class User {

    @Id
    private ObjectId id;
    private UUID code;

    private String name;
    private String email;
    private String username;
    private String password;
    private String identifier;

    private String address;
    private String uf;
    private String city;
    private String country;

    private Status status;

    public enum Status {
        ENABLED, DISABLED
    }

    public void enable() { setStatus(Status.ENABLED); }

    public void disabled() { setStatus(Status.DISABLED); }


}
