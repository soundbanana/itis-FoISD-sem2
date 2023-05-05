package com.chemaev.services;


import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findById(Integer id);

    UserResponseDto create(CreateUserRequestDto userDto);
}