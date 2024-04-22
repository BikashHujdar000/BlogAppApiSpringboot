package com.blogapp.blog.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.blog.Entities.Comment;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.CommentDTO;
import com.blogapp.blog.Services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/")

public class CommentController {

    @Autowired
    private CommentService commentService;
    
    // //createComments
    
    @PostMapping("/post/{postId}/comment")
    
    public ResponseEntity<CommentDTO> createComment( @RequestBody CommentDTO commentDTO ,@PathVariable ("postId") Integer postId){

        // calling a service 
           CommentDTO commentDTO2 =  this.commentService.createComment(commentDTO, postId);
           
        return new ResponseEntity<CommentDTO>(commentDTO2,HttpStatus.CREATED);
    }

    // // delete Comments 

    // void deleteComment(Integer commentId);

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment Sucessfully Deleated",true),HttpStatus.OK);
    }

    
}
