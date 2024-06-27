package com.jimcvr.HologJavaVersion.utils.mapper.item;

import com.jimcvr.HologJavaVersion.dto.RequestItemDTO;
import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RequestToItem implements Mapper<RequestItemDTO, Item> {
    @Override
    public Item transform(RequestItemDTO dto) {
        return new Item(
                dto.getName(),
                dto.getDescription(),
                dto.getPicture(),
                dto.getScore(),
                dto.getDate(),
                dto.getStatus(),
                dto.getCustom()
        );
    }
}
