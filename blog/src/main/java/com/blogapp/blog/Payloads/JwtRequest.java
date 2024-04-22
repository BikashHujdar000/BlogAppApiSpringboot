package com.blogapp.blog.Payloads;

import lombok.Data;

@Data
public class JwtRequest {

    //email is consider as username 

    private  String username;

    private String password;


    
}
