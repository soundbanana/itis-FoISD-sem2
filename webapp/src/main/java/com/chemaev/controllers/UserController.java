package com.chemaev.controllers;

import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import com.chemaev.model.university.User;
import com.chemaev.repository.university.UserRepository;
import com.chemaev.services.university.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello %s!", name.orElse("Ivan"));
    }

    @PostMapping(value = "/user")
    public String createUser(@ModelAttribute CreateUserRequestDto user, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.createUser(user, url);;
        return "sign_up_success";
    }

    @GetMapping("/verification")
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }

    @GetMapping(value = "/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
    }

    @ResponseBody
    @GetMapping(value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id);
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
