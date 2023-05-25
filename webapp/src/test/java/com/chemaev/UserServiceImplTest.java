package com.chemaev;

import com.chemaev.config.MailConfig;
import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import com.chemaev.model.university.User;
import com.chemaev.repository.university.UserRepository;
import com.chemaev.services.university.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private MailConfig mailConfig;

    @Mock
    private Authentication authentication;

    @Test
    public void testGetByEmail() {
        String email = "test@example.com";
        User user = new User();
        when(userRepository.getUserByEmail(email)).thenReturn(Optional.of(user));

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        UserResponseDto result = userService.getByEmail(email);

        assertEquals(user, result);
        verify(userRepository).getUserByEmail(eq(email));
    }

    @Test
    public void testCreateUser() {
        String code = "code123";
        String encodedPassword = "encodedPassword123";
        String email = "test@example.com";
        String name = "Test User";
        String url = "http://example.com";

        CreateUserRequestDto createUserDto = new CreateUserRequestDto();
        createUserDto.setName(name);
        createUserDto.setEmail(email);
        createUserDto.setPassword("password123");

        User user = User.builder()
                .name(name)
                .email(email)
                .verificationCode(code)
                .password(encodedPassword)
                .build();

        when(encoder.encode(createUserDto.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        UserResponseDto result = userService.createUser(createUserDto, url);

        assertEquals(user, result);
        verify(encoder).encode(eq(createUserDto.getPassword()));
        verify(userRepository).save(eq(user));
    }

    @Test
    public void testVerify_withValidCode() {
        String verificationCode = "code123";
        User user = User.builder()
                .verificationCode(verificationCode)
                .build();

        when(userRepository.findByVerificationCode(verificationCode)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        boolean result = userService.verify(verificationCode);

        assertTrue(result);
        assertNull(user.getVerificationCode());
        assertTrue(user.isEnabled());
        verify(userRepository).findByVerificationCode(eq(verificationCode));
        verify(userRepository).save(eq(user));
    }

    @Test
    public void testVerify_withInvalidCode() {
        String verificationCode = "invalidCode";

        when(userRepository.findByVerificationCode(verificationCode)).thenReturn(null);

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        boolean result = userService.verify(verificationCode);

        assertFalse(result);
        verify(userRepository).findByVerificationCode(eq(verificationCode));
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testFindAll() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        List<UserResponseDto> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
        verify(userRepository).findAll();
    }

    @Test
    public void testFindById_withExistingId() {
        Integer userId = 1;
        User user = new User();
        when(userRepository.findAllById(List.of(userId))).thenReturn(Collections.singletonList(user));

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        Optional<UserResponseDto> result = userService.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findAllById(List.of(userId));
    }

    @Test
    public void testFindById_withNonExistingId() {
        Integer userId = 1;
        when(userRepository.findAllById(List.of(userId))).thenReturn(Collections.emptyList());

        UserServiceImpl userService = new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);

        Optional<UserResponseDto> result = userService.findById(userId);

        assertFalse(result.isPresent());
        verify(userRepository).findAllById(List.of(userId));
    }
}
