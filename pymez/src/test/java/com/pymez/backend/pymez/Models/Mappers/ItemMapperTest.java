package com.pymez.backend.pymez.Models.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;

public class ItemMapperTest {

    private final ItemMapper mapper = ItemMapper.INSTANCE;

    private ItemCreateDto itemCreateDtoSetup;
    private ItemModel itemModelSetup;

    @BeforeEach
    void setUp() {
        itemCreateDtoSetup = new ItemCreateDto();
        itemCreateDtoSetup.setName("Teclado");
        itemCreateDtoSetup.setColor("Verde");
        itemCreateDtoSetup.setWeight(new BigDecimal("4.4"));
        itemCreateDtoSetup.setHeight(new BigDecimal("5.5"));
        itemCreateDtoSetup.setCategories(List.of("Tecnologia"));
        itemCreateDtoSetup.setItemPrice(new BigDecimal("55.9"));
        itemCreateDtoSetup.setSellPrice(new BigDecimal("100.9"));
        itemCreateDtoSetup.setAvailableQuantity(19);
        itemCreateDtoSetup.setRequestedQuantity(200);
        itemCreateDtoSetup.setIsAvailable(true);
        itemCreateDtoSetup.setDateCreated(Instant.now());
        itemCreateDtoSetup.setDateUpdated(Instant.now());
        itemCreateDtoSetup.setLastSoldAt(Instant.now());

        itemModelSetup = new ItemModel();
        itemModelSetup.setId(1l);
        itemModelSetup.setName("Teclado");
        itemModelSetup.setColor("Verde");
        itemModelSetup.setWeight(new BigDecimal("4.4"));
        itemModelSetup.setHeight(new BigDecimal("5.5"));
        itemModelSetup.setCategories(List.of("Tecnologia"));
        itemModelSetup.setItemPrice(new BigDecimal("55.9"));
        itemModelSetup.setSellPrice(new BigDecimal("100.9"));
        itemModelSetup.setAvailableQuantity(19);
        itemModelSetup.setRequestedQuantity(200);
        itemModelSetup.setIsAvailable(true);
        itemModelSetup.setDateCreated(Instant.now());
        itemModelSetup.setDateUpdated(Instant.now());
        itemModelSetup.setLastSoldAt(Instant.now());
    }

    @Test
    void testItemCreateDtoToItemModel_ShouldReturnItemModel() {

        ItemModel model = mapper.itemCreateDtoToItemModel(itemCreateDtoSetup);

        assertNotNull(model, "The model should not be null");
        assertEquals("Teclado", model.getName());
        assertEquals(new BigDecimal("55.9"), model.getItemPrice());

        assertNull(model.getId(), "The ID should be null, due to it was ignored in the mapper");
    }

    @Test
    void testItemModelToItemResponseDto_ShouldReturnItemResponseDto() {

        ItemUpdateDto dto = new ItemUpdateDto();
        dto.setName("Mouse");
        dto.setHeight(new BigDecimal("9.7"));
        dto.setItemPrice(new BigDecimal("60.12"));
        dto.setSellPrice(new BigDecimal("90.6"));
        dto.setIsAvailable(false);

        mapper.itemUpdateDtoToItemModel(dto, itemModelSetup);

        assertNotNull(itemModelSetup, "The model should not be null");
        assertEquals(1l, itemModelSetup.getId());
        assertEquals("Mouse", itemModelSetup.getName());
        assertEquals(new BigDecimal("9.7"), itemModelSetup.getHeight());
        assertEquals(new BigDecimal("60.12"), itemModelSetup.getItemPrice());
        assertEquals(new BigDecimal("90.6"), itemModelSetup.getSellPrice());
        assertEquals(19, itemModelSetup.getAvailableQuantity());
        assertEquals("Verde", itemModelSetup.getColor());
        assertFalse(itemModelSetup.getIsAvailable());

        assertNotNull(itemModelSetup.getId(), "The ID should not be null");
    }

    @Test
    void testItemUpdateDtoToItemModel_ShouldReturnItemModel() {

        ItemResponseDto dto = mapper.itemModelToItemResponseDto(itemModelSetup);

        assertNotNull(dto, "The DTO should not be null");
        assertEquals(19, dto.getAvailableQuantity());
        assertEquals(new BigDecimal("5.5"), dto.getHeight());
    }
}
