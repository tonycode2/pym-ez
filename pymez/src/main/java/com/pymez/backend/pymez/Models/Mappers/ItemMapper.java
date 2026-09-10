package com.pymez.backend.pymez.Models.Mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Javadoc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR) // Change this to SETTER if we get a circular dependencies
                                                           // error
@Javadoc(value = "This is the mapper that will transform DTOs to Models, and Models to DTOs for the Items", authors = {
        "Anthony Alvarez" }, since = "0.1")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    /**
     * This function will transform an ItemCreateDto to an ItemModel.
     * 
     * @see @Mapping annotation will ignore the id field during the conversion. This
     *      is because the ItemCreateDto does not have that field. Also, it's not
     *      necessary due to the GeneratedValue annotation in the model
     * @param itemCreateDto
     * @return ItemModel
     */
    @Mapping(target = "id", ignore = true)
    ItemModel itemCreateDtoToItemModel(ItemCreateDto itemCreateDto);

    /**
     * This function will transform an ItemUpdateDto to an ItemModel
     * 
     * @param itemUpdateDto
     * @return ItemModel
     */
    ItemModel itemUpdateDtoToItemModel(ItemUpdateDto itemUpdateDto);

    /**
     * This function will transform an ItemModel to an ItemResponseDto.
     * 
     * @see In In this case it's not necessary to ignore anything
     * @param itemModel
     * @return ItemResponseDto
     */
    ItemResponseDto itemModelToItemResponseDto(ItemModel itemModel);
}
