package com.estsoft.springdemoproject.controller;

import com.estsoft.springdemoproject.repository.Member;
import com.estsoft.springdemoproject.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper; // JSON 직렬화/역직렬화 도구

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllMember() throws Exception {
        // given: 멤버 목록 저장   (생략)

        // when:    GET /members
        ResultActions resultActions = mockMvc.perform(get("/members")
                .accept(MediaType.APPLICATION_JSON));

        // then:    response 검증
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testSaveMember() throws Exception {
        // given:
        Member member = new Member();
        member.setName("ㅎㅇ");

        String memberJson = objectMapper.writeValueAsString(member);

        // when:
        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberJson));

        // then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ㅎㅇ"));
    }

}