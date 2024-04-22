package com.blogapp.blog.Entities;
import java.util.List;

import com.blogapp.blog.Payloads.PostDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResposne {

    private List<PostDTO>content;

    private int pageNumber;
    private int pageSize;
    private long totalelements;
    private int totalPages;
    private boolean lastPage;
    




    
}
