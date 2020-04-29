package com.example.demo.service.user;

import com.example.demo.repository.user.UserEntity;
import com.example.demo.web.controller.user.UserRequest;
import com.example.demo.web.controller.user.UserResponse;

public class UserConverter {

    public static User toUser(UserRequest requestBody) {
        var user = new User();
        user.setUsername(requestBody.getUsername());
        user.setPassword(requestBody.getPassword());
        return user;
    }

    public static UserResponse toResponse(User user) {
        var userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }

    public static User toUser(UserEntity userEntity) {
        var user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    public static UserEntity toEntity(User user) {
        var userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
