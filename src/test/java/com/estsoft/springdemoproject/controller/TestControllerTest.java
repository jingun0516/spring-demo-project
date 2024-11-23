package com.estsoft.springdemoproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        // Given: query 파라미터 값 설정
        String query = "query";

        // When: /test 엔드포인트로 GET 요청
        ResultActions resultActions = mockMvc.perform(get("/test")
                .param("query", query));

        resultActions.andExpect(jsonPath("$.query").value(query));  // 응답 JSON에서 query 값 확인
    }
}