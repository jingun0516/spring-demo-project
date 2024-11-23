package com.estsoft.springdemoproject.blog.controller;


import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;
import com.estsoft.springdemoproject.blog.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class CommentArticleControllerTest {

    @InjectMocks
    CommentArticleController commentArticleController;

    @Mock
    CommentService commentService;

    @Mock
    BlogRepository blogRepository;

    @Mock
    CommentRepository commentRepository;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentArticleController).build();
    }

    @Test
    void saveCommentByArticleId() throws Exception {        // Arrange
        Article article = new Article();
        Comment comment = new Comment("Test Comment", article);

        Mockito.when(commentService.saveComment(any(), any()))
                .thenReturn(comment);

        String requestJson = """
            {
                "body": "Test Comment"
            }
        """;

        // when
        ResultActions resultActions = mockMvc.perform(post("/api/articles/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.body").value("Test Comment"));
    }

    @Test
    void selectCommentById() throws Exception {
        Long commentId = 1L;
        Article article = new Article();
        Comment comment = new Comment("Test Comment", article);

        Mockito.when(commentService.findComment(any()))
                .thenReturn(comment);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value("Test Comment"));
    }

    @Test
    void updateCommentById() throws Exception {
        Long commentId = 1L;
        Article article = new Article();

        Comment comment = new Comment("Test", article);
        comment.updateCommentBody("Updated Comment");
        Mockito.when(commentService.update(any(), any()))
                .thenReturn(comment);

        String requestJson = """
            {
                "body": "Updated Comment"
            }
        """;

        // when
        ResultActions resultActions = mockMvc.perform(put("/api/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value("Updated Comment"));

    }

    @Test
    void deleteCommentById() throws Exception {
        Long commentId = 1L;


        // when
        ResultActions resultActions = mockMvc.perform(delete("/api/comments/{commentId}", commentId)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());

        verify(commentService, times(1)).delete(any());

    }
}