package com.jimcvr.HologJavaVersion.services.api;

import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(long id, String userId) throws CategoryNotFoundException;
    Category create(Category category);
    Boolean update(long id,Category category) throws CategoryNotFoundException;
    Category delete(String userId,long id) throws CategoryNotFoundException;

}
