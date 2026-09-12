package com.pymez.backend.pymez.Services;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * @return Page<ItemResponseDto>
     * @see com.pymez.backend.pymez.Services.CRUDInterface#getAll()
     */
    public Page<ItemResponseDto> getAll(int page, int size, String sortedBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortedBy).ascending()
                : Sort.by(sortedBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        var filledPage = repo.findAll(pageable).map(mapper::itemModelToItemResponseDto);
        log.info("Page number: {}. Total of pages: {}. Total of elements: {}", filledPage.getNumber(),
                filledPage.getTotalPages(), filledPage.getTotalElements());
        return filledPage;
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

    /**
     * This function will update an existing item with new incoming information.
     * We can use this same function to update any given information. For example,
     * if we want to update just the picture, or the name we can send an object in
     * the body with just that data and it will change just that
     * Patch can be used here
     * 
     * 
     * @throws ItemsException
     * @return ItemResponseDto
     * @see com.pymez.backend.pymez.Services.CRUDInterface#update(java.lang.Object)
     */
    public ItemResponseDto update(ItemUpdateDto updateDto) {
        var existingItem = repo.findById(updateDto.getId())
                .orElseThrow(() -> new ItemsException("The item was not located. ID: " + updateDto.getId()));

        mapper.itemUpdateDtoToItemModel(updateDto, existingItem);

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
