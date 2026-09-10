package com.pymez.backend.pymez.Models.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "id", ignore = true)
    ItemModel itemCreateDtoToItemModel(ItemCreateDto itemCreateDto);

    ItemModel itemUpdateDtoToItemModel(ItemUpdateDto itemUpdateDto);

    ItemResponseDto itemModelToItemResponseDto(ItemModel itemModel);
}
