package com.estsoft.springdemoproject.blog.controller;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springdemoproject.blog.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class BlogPageControllerTest {

    @InjectMocks
    BlogPageController blogPageController;

    @Mock
    BlogService blogService;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogPageController).build();
    }

    @Test
    void showArticle() throws Exception {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());
        articles.add(new Article());

        Mockito.when(blogService.findAll())
                .thenReturn(articles);

        List<ArticleViewResponse> list = articles.stream()
                .map(ArticleViewResponse::new)
                .toList();

        ResultActions resultActions = mockMvc.perform(get("/articles"));

        //then
        resultActions.andExpect(view().name("articleList"))
                .andExpect(model().attributeExists("articles"))
        ;


    }

    @Test
    void showDetails() {
    }

    @Test
    void addArticle() {
    }
}