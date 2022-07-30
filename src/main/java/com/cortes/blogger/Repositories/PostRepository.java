package com.cortes.blogger.Repositories;

import com.cortes.blogger.Data.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
