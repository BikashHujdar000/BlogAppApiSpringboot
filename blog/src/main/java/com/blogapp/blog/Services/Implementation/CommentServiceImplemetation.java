package com.blogapp.blog.Services.Implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.blogapp.blog.Entities.Comment;
import com.blogapp.blog.Entities.Post;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.CommentDTO;
import com.blogapp.blog.Repositories.CommentRepository;
import com.blogapp.blog.Repositories.PostRepository;
import com.blogapp.blog.Services.CommentService;

@Service
public class CommentServiceImplemetation implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {

          Post post =  this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postID", postId));

          Comment comment = this.modelMapper.map(commentDTO, Comment.class);
          comment.setPost(post);
        
          Comment savedComment =  this.commentRepository.save(comment);


     
        return this.modelMapper.map(savedComment, CommentDTO.class);

    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));

        this.commentRepository.delete(comment);
       
    }
    
}
