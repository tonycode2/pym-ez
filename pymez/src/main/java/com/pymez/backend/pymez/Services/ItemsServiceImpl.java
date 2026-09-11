package com.pymez.backend.pymez.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Exceptions.ItemsException;
import com.pymez.backend.pymez.Models.Mappers.ItemMapper;
import com.pymez.backend.pymez.Repositories.ItemsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemsServiceImpl implements CRUDInterface<ItemCreateDto, ItemUpdateDto, ItemResponseDto> {

    private final ItemsRepository repo;
    private final ItemMapper mapper;

    public ItemsServiceImpl(ItemsRepository repo, ItemMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    /**
     * This function will get 1 item with the id.
     * 
     * @throws ItemsException
     * @return ItemResponseDto
     * @see com.pymez.backend.pymez.Services.CRUDInterface#getById(java.lang.Long)
     */
    public ItemResponseDto getById(Long id) {
        return repo.findById(id)
                .map(mapper::itemModelToItemResponseDto)
                .orElseThrow(() -> new ItemsException("The item was not located. ID: " + id));
    }

    // TODO: pagination and filters
    /**
     * This function will get all the items in the database.
     * 
     * @return List<ItemResponseDto>
     * @see com.pymez.backend.pymez.Services.CRUDInterface#getAll()
     */
    public List<ItemResponseDto> getAll() {
        var itemsList = repo.findAll();
        log.info("{} items found", itemsList.size());
        return itemsList.stream()
                .map(e -> mapper.itemModelToItemResponseDto(e))
                .toList();
    }

    /**
     * This function will save a new item into the database
     * 
     * @return ItemResponseDto
     * @see com.pymez.backend.pymez.Services.CRUDInterface#save(java.lang.Object)
     */
    public ItemResponseDto save(ItemCreateDto requestDto) {
        var itemModel = mapper.itemCreateDtoToItemModel(requestDto);
        var savedItem = repo.save(itemModel);
        log.info("Saved item. ID: {}", savedItem.getId());
        return mapper.itemModelToItemResponseDto(itemModel);
    }

    // TODO: see if using patch instead of put changes this function
    // TODO: see @MappingTarget to update
    /**
     * This function will update an existing item with new incoming information
     * 
     * @throws ItemsException
     * @return ItemResponseDto
     * @see com.pymez.backend.pymez.Services.CRUDInterface#update(java.lang.Object)
     */
    public ItemResponseDto update(ItemUpdateDto updateDto) {
        var existingItem = repo.findById(updateDto.getId())
                .orElseThrow(() -> new ItemsException("The item was not located. ID: " + updateDto.getId()));
        existingItem.setName(updateDto.getName());
        existingItem.setDescription(updateDto.getDescription());
        existingItem.setColor(updateDto.getColor());
        existingItem.setWeight(updateDto.getWeight());
        existingItem.setHeight(updateDto.getHeight());
        existingItem.setCategories(updateDto.getCategories());
        existingItem.setItemPrice(updateDto.getItemPrice());
        existingItem.setSellPrice(updateDto.getSellPrice());
        existingItem.setAvailableQuantity(updateDto.getAvailableQuantity());
        existingItem.setRequestedQuantity(updateDto.getRequestedQuantity());
        existingItem.setIsAvailable(updateDto.getIsAvailable());
        existingItem.setPicUrl(updateDto.getPicUrl());
        existingItem.setDateCreated(updateDto.getDateCreated());
        existingItem.setDateUpdated(updateDto.getDateUpdated());
        existingItem.setLastSoldAt(updateDto.getLastSoldAt());

        var updatedItem = repo.save(existingItem);
        log.info("The item with ID {} was updated", updateDto.getId());
        return mapper.itemModelToItemResponseDto(updatedItem);
    }

    /**
     * This function will delete an item from the database
     * 
     * @throws ItemsException
     * @return void
     * @see com.pymez.backend.pymez.Services.CRUDInterface#delete(java.lang.Long)
     */
    public void delete(Long id) {
        var item = repo.findById(id).orElseThrow(() -> new ItemsException("The item was not located. ID: " + id));
        repo.delete(item);
        log.info("The item with ID {} was deleted", id);
    }
}
