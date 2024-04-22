package com.blogapp.blog.Payloads;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * CommentDTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  commentId;
    private String commentContent;
    


    
}