package com.jimcvr.HologJavaVersion.utils.mapper.category;

import com.jimcvr.HologJavaVersion.dto.ResponseCategoryDTO;
import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryToResponse implements Mapper<Category, ResponseCategoryDTO> {

    @Override
    public ResponseCategoryDTO transform(Category entity) {
        return new ResponseCategoryDTO(
                entity.getId(),
                entity.getName(),
                entity.getUserId(),
                entity.getIconId()
        );
    }
}
