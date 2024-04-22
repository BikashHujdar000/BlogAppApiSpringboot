package com.blogapp.blog.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.CategoryDTO;
import com.blogapp.blog.Services.CategoryService;
import com.blogapp.blog.Services.Implementation.CategoryServiceImplementation;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/category/")
public class CategotyController {

    @Autowired
    private CategoryService categoryService;

    // create service call

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        CategoryDTO categoryDTO2 = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(categoryDTO2, HttpStatus.CREATED);
    }

    // update service call

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
            @PathVariable("categoryId") Integer categoryId) {

        CategoryDTO categoryDTO3 = this.categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<CategoryDTO>(categoryDTO3, HttpStatus.CREATED);
    }

    // delete service call

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {

        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Sucessfully", true), HttpStatus.OK);
    }

    // get By id servicecall

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Integer categoryId) {

        CategoryDTO categoryDTO3 = this.categoryService.getCategoryByID(categoryId);
        return new ResponseEntity<CategoryDTO>(categoryDTO3, HttpStatus.CREATED);
    }

    // get all service call

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategory() {

        return new ResponseEntity(this.categoryService.getAllCategories(), HttpStatus.OK);

    }

}
