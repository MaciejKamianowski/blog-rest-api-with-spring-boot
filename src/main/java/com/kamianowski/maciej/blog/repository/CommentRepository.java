package com.kamianowski.maciej.blog.repository;

import com.kamianowski.maciej.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
