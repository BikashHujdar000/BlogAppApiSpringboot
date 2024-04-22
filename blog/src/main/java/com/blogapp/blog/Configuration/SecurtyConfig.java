package com.blogapp.blog.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogapp.blog.JWT.JwtRequestFilter;
import com.cloudinary.Cloudinary;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurtyConfig {


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
       return new BCryptPasswordEncoder();
    }



    @Bean
    public DefaultSecurityFilterChain SecurityFilterChain(HttpSecurity security) throws Exception
    {
           return security
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/users/","/api/users/login","/api/users/security").permitAll()
                .requestMatchers("/api/**")
                 .authenticated())
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(this.jwtRequestFilter,UsernamePasswordAuthenticationFilter.class)
                .build();



    }

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }


    @Bean
public Cloudinary getCloudinary()
{

    Map config = new HashMap<>();

    config.put("cloud_name", "dul8qqnyk");
    config.put("api_key", "862871565426863");
    config.put("api_secret", "upNKc57dnLjxk4jNYl1kzmJ8qlI");  
    config.put("secure",true);

    return new Cloudinary(config);
}





    
    
}
