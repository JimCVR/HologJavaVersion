package com.jimcvr.HologJavaVersion.controllers;

import com.jimcvr.HologJavaVersion.dto.RequestCategoryDTO;
import com.jimcvr.HologJavaVersion.dto.ResponseCategoryDTO;
import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.services.api.CategoryService;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class CategoriesController {

    CategoryService categoryService;
    Mapper<RequestCategoryDTO, Category> toCategory;
    Mapper<Category, ResponseCategoryDTO> toResponse;

    public CategoriesController(CategoryService categoryService,
                                Mapper<RequestCategoryDTO, Category> toCategory,
                                Mapper<Category, ResponseCategoryDTO> toResponse) {
        this.categoryService = categoryService;
        this.toCategory = toCategory;
        this.toResponse = toResponse;
    }

    @GetMapping("/{userId}/categories")
    public ResponseEntity<List<ResponseCategoryDTO>> getCategories(@PathVariable String userId) {
        Stream<Category> categories = categoryService
                .findAll()
                .stream()
                .filter(it -> it.getUserId().equals(userId));

        List<ResponseCategoryDTO> resultList = categories.map(category -> toResponse.transform(category)).toList();

        return new ResponseEntity(resultList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/categories/{id}")
    public ResponseEntity<ResponseCategoryDTO> getCategoryById(
            @PathVariable String userId,
            @PathVariable long id) {
        Category category = null;

        try {
            category = categoryService.findById(id, userId);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        ResponseCategoryDTO result = toResponse.transform(category);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{userId}/categories")
    public ResponseEntity<String> createCategory(
            @PathVariable String userId,
            @RequestBody RequestCategoryDTO requestCategoryDTO) {
        if (requestCategoryDTO.getName().isBlank())
            return new ResponseEntity<>("Category not created", HttpStatus.PRECONDITION_FAILED);

        Category category = toCategory.transform(requestCategoryDTO);
        category.setUserId(userId);
        categoryService.create(category);

        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/categories/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String userId,
                                                 @PathVariable Long id,
                                                 @RequestBody RequestCategoryDTO requestCategoryDTO) {
        if (requestCategoryDTO.getName().isBlank()) {
            return new ResponseEntity<>("Category not modified", HttpStatus.PRECONDITION_FAILED);
        }

        Category category = toCategory.transform(requestCategoryDTO);
        category.setUserId(userId);

        try {
            if (categoryService.update(id, category))
                return new ResponseEntity<>("category modified", HttpStatus.OK);

        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}/categories/{id}")
    public ResponseEntity<ResponseCategoryDTO> deleteCategory(@PathVariable String userId, @PathVariable long id) {
        Category deletedCategory = null;

        try {
            deletedCategory = categoryService.delete(userId, id);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        ResponseCategoryDTO responseCategory = toResponse.transform(deletedCategory);

        return new ResponseEntity<>(responseCategory, HttpStatus.OK);

    }
}
