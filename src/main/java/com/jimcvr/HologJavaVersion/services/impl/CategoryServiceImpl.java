package com.jimcvr.HologJavaVersion.services.impl;

import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.repository.CategoryRepository;
import com.jimcvr.HologJavaVersion.services.api.CategoryService;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id, String userId) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).filter(cat -> cat.getUserId().equals(userId)).orElseThrow(CategoryNotFoundException::new);
        return category;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Boolean update(long id, Category category) throws CategoryNotFoundException {

        Category categoryToUpdate = categoryRepository
                .findById(id)
                .filter(cat -> cat.getUserId().equals(category.getUserId()))
                .orElseThrow(CategoryNotFoundException::new);

        category.setId(id);
        category.setUserId(categoryToUpdate.getUserId());
        categoryRepository.save(category);

        return true;
    }

    @Override
    public Category delete(String userId, long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).filter(cat -> cat.getUserId().equals(userId)).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);

        return category;
    }
}
