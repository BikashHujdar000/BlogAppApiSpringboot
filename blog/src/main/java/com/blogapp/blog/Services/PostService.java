package com.blogapp.blog.Services;

import java.util.List;

import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.PostResposne;
import com.blogapp.blog.Payloads.PostDTO;

public interface PostService {

    // create posts
    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);

    // update
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    // delete
    void deletePost(Integer postId);

    // getALlpost

    PostResposne getAllPost(Integer pageNumber,Integer pageSize);

    // get post bu Id
    PostDTO getPostById(Integer postId);

    // get all post by category

    List<PostDTO> getPostsbyCategory(Integer categoryId);

    // get all posts by users

    List<PostDTO> getAllPostsByUsers(Integer userId);

    //search post by keywords

    List<Post> searchPosts(String keywords);

}
