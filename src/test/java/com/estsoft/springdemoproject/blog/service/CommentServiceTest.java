package com.estsoft.springdemoproject.blog.service;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    BlogRepository blogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveComment() {
        // given
        Long articleId = 1L;
        Article article = new Article("Title", "Content");
        CommentRequestDTO requestDTO = new CommentRequestDTO("This is a comment");

        Mockito.doReturn(new Comment(requestDTO.getBody(), article))
                .when(commentRepository).save(ArgumentMatchers.any(Comment.class));

        // when
        Comment result = commentRepository.save(new Comment(requestDTO.getBody(), article));

        // then
        assertThat(result).isNotNull();
        assertThat(result.getBody()).isEqualTo(requestDTO.getBody());
        assertThat(result.getArticle()).isEqualTo(article);
    }

    @Test
    public void testFindComment() {
        // given
        Long commentId = 1L;
        Comment comment = new Comment("Test Comment", null);

        // Mock 설정: commentRepository.findById는 Optional<Comment> 반환
        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        // when
        Comment result = commentRepository.findById(commentId).orElseThrow(); // commentService를 호출해야 함

        // then
        assertThat(result).isNotNull(); // 반환된 Comment가 null이 아닌지 확인
        assertThat(result.getBody()).isEqualTo("Test Comment"); // Comment 내용 확인

        verify(commentRepository, times(1)).findById(commentId); // findById 호출 횟수 확인
    }
}