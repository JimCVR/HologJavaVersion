package com.jimcvr.HologJavaVersion.services.api;

import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;
import com.jimcvr.HologJavaVersion.utils.exceptions.ItemNotFoundException;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(long id) throws ItemNotFoundException;
    List<Item> findByCategoryId(long categoryId);
    Item create(long categoryId, Item item) throws CategoryNotFoundException;
    Boolean update(String userId, long id, Item item) throws ItemNotFoundException;
    Item delete(String userId, long id) throws ItemNotFoundException;
}
