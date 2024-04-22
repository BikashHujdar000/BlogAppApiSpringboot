package com.blogapp.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.blog.Entities.Category;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.CategoryDTO;
import com.blogapp.blog.Repositories.CategoryRepository;
import com.blogapp.blog.Services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        // creating the category
        Category category = dtoToCategory(categoryDTO);
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryToDto(savedCategory);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        // tasks to do
        // first find that id category
        // then add category data from dto then save and finallly return the datas
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategory_name(categoryDTO.getCategory_name());
        category.setCategory_description(categoryDTO.getCategory_description());

        Category newCategory = this.categoryRepository.save(category);

        return this.categoryToDto(newCategory);

    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        this.categoryRepository.delete(category);

    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = this.categoryRepository.findAll();

        List<CategoryDTO> categoryDTOs = categories.stream().map(x -> this.categoryToDto(x))
                .collect(Collectors.toList());

        return categoryDTOs;

    }

    @Override
    public CategoryDTO getCategoryByID(Integer categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category ", "Id", categoryId));

        return this.categoryToDto(category);

    }

    public Category dtoToCategory(CategoryDTO categoryDTO) {

        Category category = this.modelMapper.map(categoryDTO, Category.class);

        return category;
    }

    public CategoryDTO categoryToDto(Category category) {

        CategoryDTO categoryDTO = this.modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
}
