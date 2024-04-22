package com.blogapp.blog.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapp.blog.Entities.User;

public interface UserRepository  extends JpaRepository<User,Integer> {


  @Query("SELECT u FROM User u WHERE u.user_email = :email")
Optional<User> findByUserEmail(@Param("email") String userEmail);
    


    
}
