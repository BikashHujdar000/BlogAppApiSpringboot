package com.blogapp.blog.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapp.blog.Entities.User;
import com.blogapp.blog.Repositories.UserRepository;

@Service
public class UserAuthService  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userEmail)
     throws UsernameNotFoundException {
    
        User user = this.userRepository.findByUserEmail(userEmail).orElseThrow(()-> new UsernameNotFoundException(userEmail));

        System.out.println("User Details:");
        System.out.println("Username: " + user.getUser_email());
        System.out.println("Password: " + user.getUser_password());

        return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUser_email())
        .password(user.getUser_password())
        .build();

    

        }  
      }
