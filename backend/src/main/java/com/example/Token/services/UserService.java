package com.example.Token.services;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Token.dto.CredentialsDto;
import com.example.Token.dto.SignUpDto;
import com.example.Token.dto.UserDto;
import com.example.Token.entities.UserEntity;
import com.example.Token.exceptions.AppException;
import com.example.Token.mappers.UserMapper;
import com.example.Token.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        UserEntity userEntity = userRepository.findByLogin(credentialsDto.login())
            .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));
        System.out.println(credentialsDto.password());
        System.out.println(userEntity.getPassword());
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), userEntity.getPassword())) {
            return userMapper.toUserDto(userEntity);
        }
        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> oUser = userRepository.findByLogin(signUpDto.login());
        if (oUser.isPresent()) {
            throw new AppException("Login Already Exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = userMapper.signUpToUser(signUpDto);
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toUserDto(savedUser);
    }
}
