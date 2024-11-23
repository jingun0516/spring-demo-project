package com.estsoft.springdemoproject.controller;

import com.estsoft.springdemoproject.blog.controller.BlogPageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    PageController pageController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(pageController).build();
    }

    @Test
    public void testShowPage() throws Exception {
        // when: GET 요청
        ResultActions resultActions = mockMvc.perform(get("/thymeleaf/example"));

        // then: 뷰 이름 검증 및 모델 데이터 검증
        resultActions.andExpect(view().name("examplePage")) // 반환된 뷰 이름 확인
                .andExpect(model().attributeExists("person")) // 모델에 person 존재 여부 확인
                .andExpect(model().attributeExists("today")) // 모델에 today 존재 여부 확인
                .andExpect(model().attribute("person", hasProperty("name", is("김자바")))) // person의 name 속성 확인
                .andExpect(model().attribute("person", hasProperty("age", is(20)))) // person의 age 속성 확인
                .andExpect(model().attribute("person", hasProperty("hobbies", hasItem("달리기")))); // person의 hobbies 속성 확인
    }
}