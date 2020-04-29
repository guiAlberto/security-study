package com.example.demo.web.controller.user;

import java.util.UUID;
import lombok.Data;

@Data
public class UserResponse {

    private UUID id;
    private String username;
    private String password;

}