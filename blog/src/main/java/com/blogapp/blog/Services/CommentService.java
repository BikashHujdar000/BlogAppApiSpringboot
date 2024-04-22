package com.blogapp.blog.Services;

import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Payloads.CommentDTO;

public interface CommentService {



    //createComments
    CommentDTO createComment(CommentDTO commentDTO ,Integer postId);

    // delete Comments 

    void deleteComment(Integer commentId);

    
}
