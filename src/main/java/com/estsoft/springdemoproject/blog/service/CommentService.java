package com.estsoft.springdemoproject.blog.service;

import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public CommentService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Long articleId, CommentRequestDTO requestDTO) {
        Article article = blogRepository.findById(articleId).orElseThrow();

        return commentRepository.save(new Comment(requestDTO.getBody(), article));
    }

    public Comment findComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(new Comment());
    }

    public Comment update(Long commentId, CommentRequestDTO request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        // 수정
        comment.updateCommentBody(request.getBody());

        return commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);   // delete from comment where id = ${commentId}
    }
}
