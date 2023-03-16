package com.chemaev.services.university.impl;

import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import com.chemaev.model.university.User;
import com.chemaev.repository.university.UserRepository;
import com.chemaev.services.university.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserResponseDto createUser(CreateUserRequestDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .build();
        return UserResponseDto.fromEntity(userRepository.save(user));
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

    @Override
    public Optional<UserResponseDto> findById(Integer id) {
        return userRepository.findAllById(List.of(id))
                .stream().map(u -> UserResponseDto.builder()
                        .name(u.getName())
                        .id(u.getId())
                        .email(u.getEmail())
                        .birthday(u.getBirthday())
                        .build()
                ).findFirst();
    }
}
