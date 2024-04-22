package com.blogapp.blog.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.blog.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {} 