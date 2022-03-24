package com.kamianowski.maciej.blog.repository;

import com.kamianowski.maciej.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository // annotation is optional, since SimpleJpaRepository already contains the @Repository annotation
public interface PostRepository extends JpaRepository<Post, Long> {

}
