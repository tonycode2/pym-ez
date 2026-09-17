package com.pymez.backend.pymez.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Models.ItemModel;
import com.pymez.backend.pymez.Models.Mappers.ItemMapper;
import com.pymez.backend.pymez.Repositories.ItemsRepository;

@ExtendWith(MockitoExtension.class)
public class ItemsServiceImplTest {

    private ItemModel itemModelSetup1, itemModelSetup2, itemModelSetupUpdated, itemModelSetupNotUpdated;
    private ItemResponseDto itemResponseDtoSetup1, itemResponseDtoSetup2, itemResponseDtoSetupUpdated;
    private ItemCreateDto itemCreateDtoSetup;
    private ItemUpdateDto itemUpdateDtoSetup;

    @BeforeEach
    void setUp() {
        itemModelSetup1 = new ItemModel(1l,
                "Keyboard",
                "Brand new Keyboard",
                "Ferreteria",
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

        itemModelSetup2 = new ItemModel(2l,
                "Keyboard",
                "Brand new Keyboard",
                "Ferreteria",
                "Black",
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

        itemModelSetupUpdated = new ItemModel(3l, "Special Keyboard",
                "Brand new Keyboard",
                "Ferreteria",
                "Cyan",
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

        itemModelSetupNotUpdated = new ItemModel(3l, "Keyboard",
                "Brand new Keyboard",
                "Ferreteria",
                "Black",
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

        itemResponseDtoSetup1 = new ItemResponseDto("Keyboard",
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

        itemResponseDtoSetup2 = new ItemResponseDto("Keyboard",
                "Brand new Keyboard",
                "Black",
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

        itemResponseDtoSetupUpdated = new ItemResponseDto("Special Keyboard",
                "Brand new Keyboard",
                "Cyan",
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

        itemCreateDtoSetup = new ItemCreateDto("Keyboard",
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
        itemUpdateDtoSetup = new ItemUpdateDto();
        itemUpdateDtoSetup.setId(3l);
        itemUpdateDtoSetup.setName("Special Keyboard");
        itemUpdateDtoSetup.setColor("Cyan");
    }

    @Mock
    private ItemsRepository repoMock;

    @Mock
    private ItemMapper mapperMock;

    @InjectMocks
    private ItemsServiceImpl service;

    @Test
    void testDelete_ShouldReturnEmpty() {
        final Long id = 1L;
        when(repoMock.findById(id)).thenReturn(Optional.of(itemModelSetup1));
        service.delete(id);

        Optional<ItemModel> deletedEntity = repoMock.findById(id);

        Assertions.assertThat(deletedEntity.isEmpty());

        Mockito.verify(repoMock).delete(itemModelSetup1);
    }

    @Test
    void testGetAll_ShouldReturnAllItemsPaginated() {
        List<ItemModel> itemsList = Arrays.asList(itemModelSetup1, itemModelSetup2);

        Pageable pageable = PageRequest.of(0, 10);

        Page<ItemModel> mockItemsPage = new PageImpl<>(itemsList, pageable, itemsList.size());

        when(repoMock.findAll(any(Pageable.class))).thenReturn(mockItemsPage);
        when(mapperMock.itemModelToItemResponseDto(itemModelSetup1)).thenReturn(itemResponseDtoSetup1);
        when(mapperMock.itemModelToItemResponseDto(itemModelSetup2)).thenReturn(itemResponseDtoSetup2);
        Page<ItemResponseDto> resultPage = service.getAll(0, 10, "id", "asc");

        assertNotNull(resultPage);
        assertEquals(2, resultPage.getTotalElements(), "Total elements should match input size");
        assertEquals(1, resultPage.getTotalPages(), "Should fit inside 1 page matching constraints");
        assertEquals(2, resultPage.getContent().size(), "Content list matches exact element count");
        assertEquals("Black", resultPage.getContent().get(1).getColor());
    }

    @Test
    void testGetById_ShouldReturnItemResponseDto() {

        // Arrange

        when(repoMock.findById(1l)).thenReturn(Optional.of(itemModelSetup1));
        when(mapperMock.itemModelToItemResponseDto(itemModelSetup1)).thenReturn(itemResponseDtoSetup1);

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
    void testSave_ShouldReturnItemResponseDto() {
        when(repoMock.save(any(ItemModel.class))).thenReturn(itemModelSetup1);
        when(mapperMock.itemCreateDtoToItemModel(itemCreateDtoSetup)).thenReturn(itemModelSetup1);
        when(mapperMock.itemModelToItemResponseDto(itemModelSetup1)).thenReturn(itemResponseDtoSetup1);
        ItemResponseDto responseDto = service.save(itemCreateDtoSetup);

        assertNotNull(responseDto);
        assertEquals("White", responseDto.getColor());

        verify(repoMock, times(1)).save(itemModelSetup1);
    }

    @Test
    void testUpdate_ShouldReturnItemResponseDto() {
        when(repoMock.findById(3l)).thenReturn(Optional.of(itemModelSetupNotUpdated));
        when(repoMock.save(itemModelSetupUpdated)).thenReturn(itemModelSetupUpdated);
        when(mapperMock.itemModelToItemResponseDto(itemModelSetupUpdated))
                .thenReturn(itemResponseDtoSetupUpdated);

        ItemResponseDto response = service.update(itemUpdateDtoSetup);

        assertNotNull(response);
        assertEquals("Cyan", response.getColor());
        assertEquals("Special Keyboard", response.getName());

        verify(repoMock, times(1)).save(itemModelSetupUpdated);
    }
}
