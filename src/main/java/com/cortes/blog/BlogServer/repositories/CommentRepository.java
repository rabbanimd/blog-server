package com.cortes.blog.BlogServer.repositories;

import com.cortes.blog.BlogServer.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
