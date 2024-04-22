package com.blogapp.blog.Entities;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;
    private String userPassword;

    
}
