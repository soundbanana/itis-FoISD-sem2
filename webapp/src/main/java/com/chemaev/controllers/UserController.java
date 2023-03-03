package com.chemaev.controllers;

import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;
import com.chemaev.model.User;
import com.chemaev.repository.UserRepository;
import com.chemaev.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello %s!", name.orElse("Ivan"));
    }

    @PostMapping(value = "/addUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping(value = {"/users/", "users"})
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
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

    @PutMapping(value = "/updateUser/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@Valid @RequestBody CreateUserRequestDto newUser, @PathVariable Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            return "No user with id " + id + " was found";
        } else {
            User user = User.builder()
                    .id(id)
                    .name(newUser.getName().trim())
                    .email(newUser.getEmail().trim())
                    .birthday(newUser.getBirthday())
                    .build();
            userRepository.save(user);
            return "User with id " + id + " was successfully edited";
        }
    }
}
