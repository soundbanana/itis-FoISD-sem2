package com.chemaev.services.University.impl;

import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;
import com.chemaev.model.University.User;
import com.chemaev.repository.University.UserRepository;
import com.chemaev.services.University.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto newUser) {
        return UserResponseDto.fromEntity(userRepository.save(
                User.builder()
                        .name(newUser.getName().trim())
                        .email(newUser.getEmail().trim())
                        .birthday(newUser.getBirthday())
                        .build()
        ));
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(u -> UserResponseDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .birthday(u.getBirthday())
                        .build())
                .collect(Collectors.toList());
    }
}
