package com.blogapp.blog.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogapp.blog.Entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDTO {


    private int post_id;
    private String post_title;
    private String post_content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDTO user;

    private Set<CommentDTO> comments = new HashSet<>();

   



    
}
