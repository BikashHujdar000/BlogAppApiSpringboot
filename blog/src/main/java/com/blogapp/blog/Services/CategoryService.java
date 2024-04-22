package com.blogapp.blog.Services;


import java.util.List;

import com.blogapp.blog.Payloads.CategoryDTO;

public interface CategoryService {

    // create category 
   CategoryDTO createCategory(CategoryDTO categoryDTO);

    // update category 
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId);


    // delete category 

    void deleteCategory(Integer categoryId);


    // get Categories 

    List<CategoryDTO> getAllCategories();

    // get Category by id 

    CategoryDTO getCategoryByID(Integer categoyId);
    
}
