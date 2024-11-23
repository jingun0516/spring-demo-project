package com.estsoft.springdemoproject.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class UserViewControllerTest {

    @InjectMocks
    UserViewController userviewController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userviewController).build();
    }

    @Test
    void login() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/login"));

        resultActions.andExpect(view().name("login2"));

    }

    @Test
    void signup() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/signup"));

        resultActions.andExpect(view().name("signup2"));
    }
}