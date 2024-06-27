package com.jimcvr.HologJavaVersion.utils.mapper.item;

import com.jimcvr.HologJavaVersion.dto.ResponseItemDTO;
import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItemToResponse implements Mapper<Item, ResponseItemDTO> {
    @Override
    public ResponseItemDTO transform(Item entity) {
        return new ResponseItemDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPicture(),
                entity.getScore(),
                entity.getDate(),
                entity.getStatus(),
                entity.getCustom(),
                entity.getCategory().getId()
        );
    }
}
