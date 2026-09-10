package com.pymez.backend.pymez.Models.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        ItemUpdateDto dto = new ItemUpdateDto();
        dto.setId(1l);
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

        ItemModel model = mapper.itemUpdateDtoToItemModel(dto);

        assertNotNull(model, "The model should not be null");
        assertEquals(1l, model.getId());
        assertEquals(200, model.getRequestedQuantity());
        assertEquals("Verde", model.getColor());

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
