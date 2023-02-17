package com.chemaev.controllers;

import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.dto.UserResponseDto;
import com.chemaev.model.User;
import com.chemaev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    @GetMapping("/addUser")
//    public String addUser(@RequestParam Optional<String> name, Optional<String> email) {
//        User user = new User(name.orElse("None"), email.orElse("None"));
//        userRepository.save(user);
//        return "OK";
//    }

//    @GetMapping("/deleteUser")
//    public String deleteUser(@RequestParam Optional<Integer> id) {
//        if (id.isPresent()) {
//            userRepository.deleteById(id.get());
//            return String.format("User %s is successfully deleted", id.get());
//        } else {
//            return "Insert id";
//        }
//    }

//    @GetMapping("/editUser/{id}")
//    public String editUser(@PathVariable Optional<Integer> id,
//                           @RequestParam Optional<String> name, Optional<String> email) {
//        if (id.isPresent()) {
//            Optional<User> curUser = userRepository.findById(id.get());
//            if (curUser.isPresent()) {
//                name.ifPresent(s -> curUser.get().setName(s));
//                email.ifPresent(s -> curUser.get().setEmail(s));
//                userRepository.save(curUser.get());
//                return String.format("User %s is successfully edited", id.get());
//            }
//            return String.format("No user with id %s was found", id.get());
//        } else {
//            return "Insert id";
//        }
//    }

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello %s!", name.orElse("Ivan"));
    }

    @PostMapping("/addUser")
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto user) {
        return UserResponseDto.fromEntity(userRepository.save(
                User.builder()
                        .name(user.getName().trim())
                        .email(user.getEmail().trim())
                        .build()
        ));
    }

    @GetMapping(value = {"/users/{id}", "users"})
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(u -> UserResponseDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

}
