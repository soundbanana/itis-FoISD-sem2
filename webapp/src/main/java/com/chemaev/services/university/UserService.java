package com.chemaev.services.university;


import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserResponseDto createUser(CreateUserRequestDto userDto, String url);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);

    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findById(Integer id);


    //TODO Add other methods
}
