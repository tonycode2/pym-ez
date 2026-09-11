package com.pymez.backend.pymez.DTOs.Request;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemUpdateDto extends ItemCreateDto {
    @NotNull(message = "The ID must not be null")
    @Digits(integer = 10, fraction = 0, message = "The ID must be a digit")
    @Positive(message = "The ID must be a positive number")
    public Long id;

    public ItemUpdateDto(String name, String description, String color, BigDecimal weight, BigDecimal height,
            ArrayList<String> categories, BigDecimal itemPrice, BigDecimal sellPrice, Integer availableQuantity,
            Integer requestedQuantity, Boolean isAvailable, String picUrl, Instant dateCreated, Instant dateUpdated,
            Instant lastSoldAt, Long id) {
        super(name, description, color, weight, height, categories, itemPrice, sellPrice, availableQuantity,
                requestedQuantity, isAvailable, picUrl, dateCreated, dateUpdated, lastSoldAt);
        this.id = id;
    }

    public ItemUpdateDto(Long id) {
        this.id = id;
    }

    public ItemUpdateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
