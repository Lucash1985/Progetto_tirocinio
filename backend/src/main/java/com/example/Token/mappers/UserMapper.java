package com.example.Token.mappers;

import org.springframework.stereotype.Component;

import com.example.Token.dto.SignUpDto;
import com.example.Token.dto.UserDto;
import com.example.Token.entities.UserEntity;

@Component
public class UserMapper {

    public UserDto toUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .login(userEntity.getLogin())
                .token(null) // Assuming token is not set here, you might want to set it in a different service
                .build();
    }

    public UserEntity signUpToUser(SignUpDto signUpDto) {
        if (signUpDto == null) {
            return null;
        }

        return UserEntity.builder()
                .firstName(signUpDto.firstName())
                .lastName(signUpDto.lastName())
                .login(signUpDto.login())
                .password(null) // Password will be set in the service
                .build();
    }
}
