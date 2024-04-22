package com.blogapp.blog.Services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Entities.PostResposne;
import com.blogapp.blog.Entities.User;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.PostDTO;
import com.blogapp.blog.Repositories.CategoryRepository;
import com.blogapp.blog.Repositories.PostRepository;
import com.blogapp.blog.Repositories.UserRepository;
import com.blogapp.blog.Services.PostService;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        // fetching the information of user and category

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepository.save(post);

        // i want to change here something later on time
        return this.modelMapper.map(newPost, PostDTO.class);

    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
     // logic building 
     // first find karna hain post ko 
     //uskey baad ham karengey fields ko pura 
     // jo jo change karna hain sirf usko hin change karengey hamlog 

       Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        
       post.setPost_title(postDTO.getPost_title());
      // post.setImageName(postDTO.getImageName());
       post.setPost_content(postDTO.getPost_content());

       Post updatedPost =  this.postRepository.save(post);
        return  this.modelMapper.map(updatedPost,PostDTO.class);

    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepository.delete(post);

       
       

    }

    @Override
    public PostResposne getAllPost(Integer pageNumber,Integer pageSize) {

        // return the list of postDto to be

        // lets have the values what is taken in sortBy
       

        Pageable p = PageRequest.of(pageNumber, pageSize);

        Page<Post> postPage = this.postRepository.findAll(p);

        // Extract the list of posts from the page
        List<Post> posts = postPage.getContent();

        List<PostDTO> postDTOs = posts.stream().map(x -> this.modelMapper.map(x, PostDTO.class))
                .collect(Collectors.toList());

        PostResposne postResposne = new PostResposne();
        postResposne.setContent(postDTOs);
        postResposne.setPageNumber(postPage.getNumber());
        postResposne.setPageSize(postPage.getSize());
         postResposne.setTotalelements(postPage.getTotalElements());
         postResposne.setTotalPages(postPage.getTotalPages());
         postResposne.setLastPage(postPage.isLast());


        return  postResposne;

       
    }

    @Override
    public PostDTO getPostById(Integer postId) {

        
       Post post =  this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
       return this.modelMapper.map(post, PostDTO.class);
   
    }

    @Override
    public List<PostDTO> getPostsbyCategory(Integer categoryId) {

        // finding the category first
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Post> posts = this.postRepository.findByCategory(category);

        if (posts==null) 
        {
        System.out.println("post service yani repo baat null");    
        }

        List<PostDTO> postDTOs = posts.stream().map((x) -> this.modelMapper.map(x, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
      

    }

    @Override
    public List<PostDTO> getAllPostsByUsers(Integer userId) {
       // okay lets build the method
       // logic
       // find user id and we have jpa provider function callled find by Class Name

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userid", userId));

        List<Post> posts = this.postRepository.findByUser(user);

        List<PostDTO> postDTOs = posts.stream().map((x) -> this.modelMapper.map(x, PostDTO.class))
                .collect(Collectors.toList());

        return postDTOs;
       
    }

    @Override
    public List<Post> searchPosts(String keywords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
