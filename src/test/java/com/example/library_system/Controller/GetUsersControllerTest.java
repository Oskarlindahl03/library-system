package com.example.library_system.Controller;

import com.example.library_system.Controller.User.GetUsersController;
import com.example.library_system.Entity.Users;
import com.example.library_system.Service.ServiceInterface.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetUsersController.class)
@Import(GetUsersControllerTest.TestConfig.class)
public class GetUsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        Users user = new Users();
        user.setFirstName("Lars");
        user.setLastName("Larsson");
        user.setEmail("lars@example.com");
        user.setPassword("secret");

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Lars"));
    }
    static class TestConfig {
        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }
}

