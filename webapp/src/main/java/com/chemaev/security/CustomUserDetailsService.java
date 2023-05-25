//package com.chemaev.security;
//
//import com.chemaev.repository.university.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.getUserByEmail(username).map(CustomUserDetails::new).orElseThrow(
//                () -> new UsernameNotFoundException(String.format("User %s not found", username))
//        );
//    }
//}
