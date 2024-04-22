package com.blogapp.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.blog.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    
}
