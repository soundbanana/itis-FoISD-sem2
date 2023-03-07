package com.chemaev.services.university;

import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto createUser(CreateUserRequestDto newUser);

    List<UserResponseDto> findAll();

    //TODO Add other methods
}
