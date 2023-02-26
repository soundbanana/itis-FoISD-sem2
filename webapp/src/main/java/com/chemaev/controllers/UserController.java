package com.chemaev.controllers;

import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;
import com.chemaev.model.User;
import com.chemaev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello %s!", name.orElse("Ivan"));
    }

    @PostMapping(value = "/addUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto newUser) {
        return UserResponseDto.fromEntity(userRepository.save(
                User.builder()
                        .name(newUser.getName().trim())
                        .email(newUser.getEmail().trim())
                        .birthday(newUser.getBirthday())
                        .build()
        ));
    }

    @GetMapping(value = {"/users/", "users"})
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(u -> UserResponseDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .birthday(u.getBirthday())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping(value = {"/users/{id}", "users"})
    public Optional<User> getUserById(@PathVariable(required = false) Optional<Integer> id) {
        return id.map(userRepository::findById).orElse(null);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            return "No user with id " + id + " was found";
        } else {
            userRepository.deleteById(id);
            return "User with id " + id + " was successfully deleted";
        }
    }
}
