package com.blogapp.blog.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {


    private int category_id;


    @NotBlank(message = "Category name cannotbe empty !!")
    @Size(min = 4,message = "category character grater than 4")
    private String category_name;

     @NotBlank(message = "Fields cannot be empty")
     @Size(min = 10,message = "category character grater  10 words ")
    private String category_description;

}
