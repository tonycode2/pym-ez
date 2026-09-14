package com.pymez.backend.pymez.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;
import com.pymez.backend.pymez.Models.Mappers.ItemMapper;
import com.pymez.backend.pymez.Repositories.ItemsRepository;

@ExtendWith(MockitoExtension.class)
public class ItemsServiceImplTest {

    @Mock
    private ItemsRepository repoMock;

    @Mock
    private ItemMapper mapperMock;

    @InjectMocks
    private ItemsServiceImpl service;

    @Test
    void testDelete() {

    }

    @Test
    void testGetAll() {

    }

    @Test
    void testGetById() {

        // Arrange
        ItemModel model = new ItemModel(1l,
                "Keyboard",
                "Brand new Keyboard",
                "White",
                new BigDecimal("9.9"),
                new BigDecimal("7.7"),
                List.of(),
                new BigDecimal("66.87"),
                new BigDecimal("99.99"),
                10,
                20,
                true,
                "URL",
                Instant.now(),
                Instant.now(),
                Instant.now());

        ItemResponseDto responseDto = new ItemResponseDto("Keyboard",
                "Brand new Keyboard",
                "White",
                new BigDecimal("9.9"),
                new BigDecimal("7.7"),
                List.of(),
                new BigDecimal("66.87"),
                new BigDecimal("99.99"),
                10,
                20,
                true,
                "URL",
                Instant.now(),
                Instant.now(),
                Instant.now());

        when(repoMock.findById(1l)).thenReturn(Optional.of(model));
        when(mapperMock.itemModelToItemResponseDto(model)).thenReturn(responseDto);

        // Act
        ItemResponseDto result = service.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Keyboard", result.getName());
        assertEquals(new BigDecimal("99.99"), result.getSellPrice());

        // Verify if mock was called just once
        verify(repoMock, times(1)).findById(1L);

    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdate() {

    }
}
