package com.jimcvr.HologJavaVersion.controllers;

import com.jimcvr.HologJavaVersion.dto.RequestItemDTO;
import com.jimcvr.HologJavaVersion.dto.ResponseItemDTO;
import com.jimcvr.HologJavaVersion.models.Item;
import com.jimcvr.HologJavaVersion.services.api.ItemService;
import com.jimcvr.HologJavaVersion.utils.exceptions.CategoryNotFoundException;
import com.jimcvr.HologJavaVersion.utils.exceptions.ItemNotFoundException;
import com.jimcvr.HologJavaVersion.utils.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ItemsController {

    ItemService itemService;
    Mapper<Item, ResponseItemDTO> toResponse;
    Mapper<RequestItemDTO, Item> toItem;

    public ItemsController(ItemService itemService,
                           Mapper<Item, ResponseItemDTO> toResponse,
                           Mapper<RequestItemDTO, Item> toItem) {
        this.itemService = itemService;
        this.toResponse = toResponse;
        this.toItem = toItem;
    }

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<ResponseItemDTO>> getItems() {
        List<Item> items = itemService.findAll();
        List<ResponseItemDTO> resultList = items.stream().map(toResponse::transform).toList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/categories/{categoryId}/items")
    public ResponseEntity<List<ResponseItemDTO>> getItemsByCategory(@PathVariable String userId, @PathVariable long categoryId) {
        List<Item> items = itemService.findByCategoryId(categoryId);
        List<ResponseItemDTO> resultList = items.stream().map(toResponse::transform).toList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<String> createItem(@RequestBody RequestItemDTO itemDTO) {
        Item item = toItem.transform(itemDTO);

        try {
            itemService.create(itemDTO.getCategoryId(), item);
        } catch (CategoryNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Item created", HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/items/{id}")
    public ResponseEntity<String> updateItem(@PathVariable String userId, @RequestBody RequestItemDTO itemDTO, @PathVariable long id) {
        if (itemDTO.getName().isBlank()) {
            return new ResponseEntity("Item not modified", HttpStatus.PRECONDITION_FAILED);
        }

        Item item = toItem.transform(itemDTO);

        try {
            if (itemService.update(userId, id, item))
                return new ResponseEntity<>("Item modified", HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Item not modified", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}/items/{id}")
    public ResponseEntity<ResponseItemDTO> deleteItem(@PathVariable String userId, @PathVariable long id) {
        Item deletedItem = null;
        try {
            deletedItem = itemService.delete(userId, id);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        ResponseItemDTO itemDTO = toResponse.transform(deletedItem);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }
}