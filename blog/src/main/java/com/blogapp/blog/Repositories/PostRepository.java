package com.blogapp.blog.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.User;

public interface PostRepository extends JpaRepository<Post,Integer> {


    List<Post>findByUser(User user);
    List<Post>findByCategory (Category category);

    
}
