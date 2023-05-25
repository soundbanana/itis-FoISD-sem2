package com.chemaev;

import com.chemaev.controllers.UserController;
import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.dto.university.UserResponseDto;
import com.chemaev.model.university.User;
import com.chemaev.services.university.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {

        User user = new User();
        user.setName("Test");
        given(userService.findAll()).willReturn(List.of(UserResponseDto.fromEntity(user)));

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Test"));
    }

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("Hello Ivan!")));

        mockMvc.perform(get("/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("Hello John!")));
    }

    @Test
    public void testCreateUser() throws Exception {
        CreateUserRequestDto user = new CreateUserRequestDto();
        user.setName("John");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", user.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign_up_success"));

        verify(userService).createUser(eq(user), anyString());
    }

    @Test
    public void testVerify_success() throws Exception {
        String verificationCode = "code123";
        when(userService.verify(verificationCode)).thenReturn(true);

        mockMvc.perform(get("/verification").param("code", verificationCode))
                .andExpect(status().isOk())
                .andExpect(view().name("verification_success"));

        verify(userService).verify(eq(verificationCode));
    }

    @Test
    public void testVerify_failed() throws Exception {
        String verificationCode = "code456";
        when(userService.verify(verificationCode)).thenReturn(false);

        mockMvc.perform(get("/verification").param("code", verificationCode))
                .andExpect(status().isOk())
                .andExpect(view().name("verification_failed"));

        verify(userService).verify(eq(verificationCode));
    }
}
