package com.estsoft.springdemoproject.blog.repository;

import com.estsoft.springdemoproject.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
