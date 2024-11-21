package com.estsoft.springdemoproject.blog.domain.dto;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.util.DateFormatUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.estsoft.springdemoproject.util.DateFormatUtil.formatter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private String createdAt;
    private ArticleResponse article;

    public CommentResponseDTO(Comment comment) {
        Article articleFromComment = comment.getArticle();

        commentId = comment.getId();
        articleId = articleFromComment.getId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt().format(formatter);
        article = new ArticleResponse(articleFromComment);
    }
}
