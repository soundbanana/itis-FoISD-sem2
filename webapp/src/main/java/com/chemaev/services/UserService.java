package com.chemaev.services;

import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto createUser(CreateUserRequestDto newUser);

    List<UserResponseDto> findAll();

    //TODO Add other methods
}
