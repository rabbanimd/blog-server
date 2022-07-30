package com.cortes.blogger.Repositories;

import com.cortes.blogger.Data.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
