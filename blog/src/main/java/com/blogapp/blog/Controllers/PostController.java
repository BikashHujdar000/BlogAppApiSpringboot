package com.blogapp.blog.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.PostResposne;
import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.PostDTO;
import com.blogapp.blog.Services.PostService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // create post

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
    @RequestBody PostDTO postDto, 
    @PathVariable("userId") Integer userId,
    @PathVariable("categoryId") Integer categoryId) 
    
    {

        PostDTO postDTO2=  this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.CREATED);

    }


    //update post 
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO ,@PathVariable("postId") Integer postId)
    {
           PostDTO postDTO2 = this.postService.updatePost(postDTO, postId);

           return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.CREATED);
    }





    // get post by id 

    @GetMapping("/posts/{postId}")
   public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId)
   {

    return  new ResponseEntity(this.postService.getPostById(postId),HttpStatus.OK);
   }
    


    // get all post 

    @GetMapping("/posts")
    public ResponseEntity<PostResposne>getAllPosts(
        @RequestParam(value = "pageNumber" ,defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam(value = "pageSize" ,defaultValue = "5",required = false) Integer pageSize
        
          )
        {
    
       return ResponseEntity.ok(this.postService.getAllPost(pageNumber,pageSize));

    }
    


    // delete post 

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable("postId") Integer postId)
    {
            this.postService.deletePost(postId);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Delated",true),HttpStatus.OK);

    }




   // get by category 

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsbyCategory(@PathVariable("categoryId") Integer categoyId)
     {

         List<PostDTO> postDTOs = this.postService.getPostsbyCategory(categoyId);
          return  new ResponseEntity(postDTOs,HttpStatus.OK);

    }




    // get by users 
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getallUserPost(@PathVariable("userId") Integer userId)
     {

     List<PostDTO>postDTOs =   this.postService.getAllPostsByUsers(userId);

     if (postDTOs== null) {

        System.out.println("NoPosts over here");
        
     }
     
     return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
         
    }


    // // get by search keywords 









}
