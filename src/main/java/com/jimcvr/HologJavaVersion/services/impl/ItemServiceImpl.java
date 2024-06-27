package com.jimcvr.HologJavaVersion.services.impl;

import com.jimcvr.HologJavaVersion.models.Category;
import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.repository.CategoryRepository;
import com.jimcvr.HologJavaVersion.repository.ItemRepository;
import com.jimcvr.HologJavaVersion.services.api.ItemService;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;
import com.jimcvr.HologJavaVersion.utils.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(long id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return item;
    }

    @Override
    public List<Item> findByCategoryId(long categoryId) {
        List<Item> resultList = itemRepository.findAll().stream().filter(item -> item.getCategory().getId() == categoryId).toList();
        return resultList;
    }

    @Override
    public Item create(long categoryId, Item item) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        item.setCategory(category);
        return itemRepository.save(item);
    }

    @Override
    public Boolean update(String userId, long id, Item item) throws ItemNotFoundException {
        Item itemToUpdate = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);

        if (itemToUpdate.getCategory().getUserId().equals(userId)) {
            item.setId(itemToUpdate.getId());
            item.setCategory(itemToUpdate.getCategory());
            itemRepository.save(item);

            return true;
        } else
            return false;
    }

    @Override
    public Item delete(String userId, long id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        if (item.getCategory().getUserId().equals(userId)) {
            itemRepository.delete(item);
        }

        return item;
    }
}