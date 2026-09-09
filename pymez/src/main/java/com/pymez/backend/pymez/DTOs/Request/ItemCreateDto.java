package com.pymez.backend.pymez.DTOs.Request;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ItemCreateDto {
    @NotBlank(message = "The name must not be empty or null")
    public String name;
    @NotBlank(message = "The description must not be empty or null")
    public String description;
    @NotBlank(message = "The color must not be empty or null")
    public String color;
    @Digits(integer = 6, fraction = 4, message = "The weight should be a number")
    @Positive(message = "The weight should be a positive number")
    @NotNull(message = "The weight must not be null")
    public BigDecimal weight;
    @Digits(integer = 6, fraction = 4, message = "The height should be a number")
    @Positive(message = "The height should be a positive number")
    @NotNull(message = "The height must not be null")
    public BigDecimal height;
    @Size(min = 1, max = 5, message = "The categories have a min of 1 and a max of 5")
    public ArrayList<String> categories;
    @Digits(integer = 10, fraction = 4, message = "The price should be a number")
    @Positive(message = "The price should be a positive number")
    @NotNull(message = "The item price must not be null")
    public BigDecimal itemPrice;
    @Digits(integer = 10, fraction = 4, message = "The selling price should be a number")
    @Positive(message = "The selling price should be a positive number")
    @NotNull(message = "The selling must not be null")
    public BigDecimal sellPrice;
    @Digits(integer = 6, fraction = 0, message = "The available quantity must be a number")
    @Positive(message = "The available quantity must be a positive number")
    @NotNull(message = "The available quantity must not be null")
    public Integer availableQuantity;
    @Digits(integer = 6, fraction = 0, message = "The requested quantity must be a number")
    @Positive(message = "The requested quantity must be a positive number")
    @NotNull(message = "The requested quantity must not be null")
    public Integer requestedQuantity;
    @NotNull
    public Boolean isAvailable;
    @PastOrPresent(message = "The date created can not be a past date")
    @NotNull(message = "The date created must not be null")
    public Instant dateCreated;
    @PastOrPresent(message = "The date updated can not be a past date")
    @NotNull(message = "The date updated must not be null")
    public Instant dateUpdated;
    @PastOrPresent(message = "The last sold date can not be a past date")
    public Instant lastSoldAt;

    public ItemCreateDto(String name,
            String description,
            String color,
            BigDecimal weight,
            BigDecimal height,
            ArrayList<String> categories,
            BigDecimal itemPrice,
            BigDecimal sellPrice,
            Integer availableQuantity,
            Integer requestedQuantity,
            Boolean isAvailable,
            Instant dateCreated,
            Instant dateUpdated,
            Instant lastSoldAt) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.categories = categories;
        this.itemPrice = itemPrice;
        this.sellPrice = sellPrice;
        this.availableQuantity = availableQuantity;
        this.requestedQuantity = requestedQuantity;
        this.isAvailable = isAvailable;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.lastSoldAt = lastSoldAt;
    }

    public ItemCreateDto() {
    }

}
