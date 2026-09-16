package com.pymez.backend.pymez.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pymez.backend.pymez.DTOs.Request.ItemCreateDto;
import com.pymez.backend.pymez.DTOs.Request.ItemUpdateDto;
import com.pymez.backend.pymez.DTOs.Response.ItemResponseDto;
import com.pymez.backend.pymez.Services.ItemsServiceImpl;

@WebMvcTest(ItemsController.class)
public class ItemsControllerTest {

    private ItemResponseDto itemResponseDtoSetup, itemResponseDtoSetup2, itemResponseDtoSetupUpdated;
    private ItemCreateDto itemCreateDtoSetup;

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @MockitoBean
    private ItemsServiceImpl serviceMock;

    @BeforeEach
    void setUp() {
        itemResponseDtoSetup = new ItemResponseDto("Keyboard",
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
    }

    @Test
    void testDelete() throws Exception {
        Long idToDelete = 1L;

        doNothing().when(serviceMock).delete(idToDelete);

        mockMvc.perform(delete("/api/v1/items/{id}", idToDelete))
                .andExpect(status().isNoContent());

        verify(serviceMock, times(1)).delete(idToDelete);
    }

    @Test
    void testGetAll() throws Exception {
        List<ItemResponseDto> responseList = List.of(itemResponseDtoSetup, itemResponseDtoSetup2);

        Page<ItemResponseDto> responsePage = new PageImpl<>(responseList);

        when(serviceMock.getAll(0, 10, "id", "asc")).thenReturn(responsePage);

        mockMvc.perform(get("/api/v1/items/filter")
                .param("page", "0")
                .param("size", "10")
                .param("sortedBy", "id")
                .param("sortDir", "asc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].color").value("White"))
                .andExpect(jsonPath("$.content[1].color").value("Black"))
                .andExpect(jsonPath("$.totalElements").value(2));
        verify(serviceMock, times(1)).getAll(0, 10, "id", "asc");
    }

    @Test
    void testGetById() throws Exception {
        when(serviceMock.getById(1L)).thenReturn(itemResponseDtoSetup);
        mockMvc.perform(get("/api/v1/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.color").value("White"));

        verify(serviceMock, times(1)).getById(1l);
    }

    @Test
    void testSave() throws Exception {
        when(serviceMock.save(any(ItemCreateDto.class))).thenReturn(itemResponseDtoSetup);

        String jsonBody = objectMapper.writeValueAsString(itemCreateDtoSetup);

        mockMvc.perform(post("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.color").value("White"));
        verify(serviceMock, times(1)).save(any(ItemCreateDto.class));

    }

    @Test
    void testUpdate() throws Exception {
        ItemUpdateDto itemUpdate = new ItemUpdateDto();
        itemUpdate.setId(1L);
        itemUpdate.setColor("Cyan");
        itemUpdate.setName("Special Keyboard");

        String jsonUpdateItem = objectMapper.writeValueAsString(itemUpdate);

        when(serviceMock.update(any(ItemUpdateDto.class))).thenReturn(itemResponseDtoSetupUpdated);

        mockMvc.perform(patch("/api/v1/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUpdateItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Special Keyboard"))
                .andExpect(jsonPath("$.color").value("Cyan"));

        verify(serviceMock, times(1)).update(any(ItemUpdateDto.class));

    }
}
