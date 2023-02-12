package com.chemaev.controllers;

import com.chemaev.model.User;
import com.chemaev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"/users/{id}", "users"})
    public Iterable<User> user(@PathVariable(required = false) Optional<Integer> id) {
        if (id.isPresent()) {
            return userRepository.findAllById(List.of(id.get()));
        } else {
            return userRepository.findAll();
        }
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam Optional<String> name, Optional<String> email) {
        User user = new User(name.orElse("None"), email.orElse("None"));
        userRepository.save(user);
        return "OK";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Optional<Integer> id) {
        if (id.isPresent()) {
            userRepository.deleteById(id.get());
            return String.format("User %s is successfully deleted", id.get());
        } else {
            return "Insert id";
        }
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable Optional<Integer> id,
                           @RequestParam Optional<String> name, Optional<String> email) {
        if (id.isPresent()) {
            Optional<User> curUser = userRepository.findById(id.get());
            if (curUser.isPresent()) {
                name.ifPresent(s -> curUser.get().setName(s));
                email.ifPresent(s -> curUser.get().setEmail(s));
                userRepository.save(curUser.get());
                return String.format("User %s is successfully edited", id.get());
            }
            return String.format("No user with id %s was found", id.get());
        } else {
            return "Insert id";
        }
    }

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello %s!", name.orElse("Ivan"));
    }
}
