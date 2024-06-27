package com.jimcvr.HologJavaVersion.utils.mapper.category;

import com.jimcvr.HologJavaVersion.dto.RequestCategoryDTO;
import com.jimcvr.HologJavaVersion.dto.ResponseCategoryDTO;
import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RequestToCategory implements Mapper<RequestCategoryDTO, Category> {

    @Override
    public Category transform(RequestCategoryDTO dto) {
        return new Category(
                dto.getName(),
                dto.getIconId(),
                new HashSet<Item>()
        );
    }
}
