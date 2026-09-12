package com.pymez.backend.pymez.Models.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;

public class ItemMapperTest {

    private final ItemMapper mapper = ItemMapper.INSTANCE;

    @Test
    void testItemCreateDtoToItemModel() {
        ItemCreateDto dto = new ItemCreateDto();
        dto.setName("Teclado");
        dto.setColor("Verde");
        dto.setWeight(new BigDecimal(4.4));
        dto.setHeight(new BigDecimal(5.5));
        dto.setCategories(new ArrayList<>(List.of("Tecnologia")));
        dto.setItemPrice(new BigDecimal(55.9));
        dto.setSellPrice(new BigDecimal(100.9));
        dto.setAvailableQuantity(19);
        dto.setRequestedQuantity(200);
        dto.setIsAvailable(true);
        dto.setDateCreated(Instant.now());
        dto.setDateUpdated(Instant.now());
        dto.setLastSoldAt(Instant.now());

        ItemModel model = mapper.itemCreateDtoToItemModel(dto);

        assertNotNull(model, "The model should not be null");
        assertEquals("Teclado", model.getName());
        assertEquals(new BigDecimal(55.9), model.getItemPrice());

        assertNull(model.getId(), "The ID should be null, due to it was ignored in the mapper");
    }

    @Test
    void testItemModelToItemResponseDto() {
        ItemModel model = new ItemModel();
        model.setId(1l);
        model.setName("Teclado");
        model.setColor("Verde");
        model.setWeight(new BigDecimal(4.4));
        model.setHeight(new BigDecimal(5.5));
        model.setCategories(new ArrayList<>(List.of("Tecnologia")));
        model.setItemPrice(new BigDecimal(55.9));
        model.setSellPrice(new BigDecimal(100.9));
        model.setAvailableQuantity(19);
        model.setRequestedQuantity(200);
        model.setIsAvailable(true);
        model.setDateCreated(Instant.now());
        model.setDateUpdated(Instant.now());
        model.setLastSoldAt(Instant.now());

        ItemUpdateDto dto = new ItemUpdateDto();
        dto.setName("Mouse");
        dto.setHeight(new BigDecimal(9.7));
        dto.setItemPrice(new BigDecimal(60.12));
        dto.setSellPrice(new BigDecimal(90.6));
        dto.setIsAvailable(false);

        mapper.itemUpdateDtoToItemModel(dto, model);

        assertNotNull(model, "The model should not be null");
        assertEquals(1l, model.getId());
        assertEquals("Mouse", model.getName());
        assertEquals(9.7, model.getHeight());
        assertEquals(60.12, model.getItemPrice());
        assertEquals(90.6, model.getSellPrice());
        assertEquals(19, model.getAvailableQuantity());
        assertEquals("Verde", model.getColor());
        assertFalse(model.getIsAvailable());

        assertNotNull(model.getId(), "The ID should not be null");
    }

    @Test
    void testItemUpdateDtoToItemModel() {
        ItemModel model = new ItemModel();
        model.setId(1l);
        model.setName("Teclado");
        model.setColor("Verde");
        model.setWeight(new BigDecimal(4.4));
        model.setHeight(new BigDecimal(5.5));
        model.setCategories(new ArrayList<>(List.of("Tecnologia")));
        model.setItemPrice(new BigDecimal(55.9));
        model.setSellPrice(new BigDecimal(100.9));
        model.setAvailableQuantity(19);
        model.setRequestedQuantity(200);
        model.setIsAvailable(true);
        model.setDateCreated(Instant.now());
        model.setDateUpdated(Instant.now());
        model.setLastSoldAt(Instant.now());

        ItemResponseDto dto = mapper.itemModelToItemResponseDto(model);

        assertNotNull(dto, "The DTO should not be null");
        assertEquals(19, dto.getAvailableQuantity());
        assertEquals(new BigDecimal(5.5), dto.getHeight());

    }
}
