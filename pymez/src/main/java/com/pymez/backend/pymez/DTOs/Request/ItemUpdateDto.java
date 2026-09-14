package com.pymez.backend.pymez.DTOs.Request;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ItemUpdateDto {
    @Digits(integer = 10, fraction = 0, message = "The ID must be a digit")
    @Positive(message = "The ID must be a positive number")
    public Long id;
    private String name;
    private String description;
    private String color;
    @Digits(integer = 6, fraction = 4, message = "The weight should be a number")
    @Positive(message = "The weight should be a positive number")
    private BigDecimal weight;
    @Digits(integer = 6, fraction = 4, message = "The height should be a number")
    @Positive(message = "The height should be a positive number")
    private BigDecimal height;
    @Size(min = 1, max = 5, message = "The categories have a min of 1 and a max of 5")
    private List<String> categories;
    @Digits(integer = 10, fraction = 4, message = "The price should be a number")
    @Positive(message = "The price should be a positive number")
    private BigDecimal itemPrice;
    @Digits(integer = 10, fraction = 4, message = "The selling price should be a number")
    @Positive(message = "The selling price should be a positive number")
    private BigDecimal sellPrice;
    @Digits(integer = 6, fraction = 0, message = "The available quantity must be a number")
    @Positive(message = "The available quantity must be a positive number")
    private Integer availableQuantity;
    @Digits(integer = 6, fraction = 0, message = "The requested quantity must be a number")
    @Positive(message = "The requested quantity must be a positive number")
    private Integer requestedQuantity;
    private Boolean isAvailable;
    private String picUrl;
    @PastOrPresent(message = "The date created can not be a past date")
    private Instant dateCreated;
    @PastOrPresent(message = "The date updated can not be a past date")
    private Instant dateUpdated;
    @PastOrPresent(message = "The last sold date can not be a past date")
    private Instant lastSoldAt;

    public ItemUpdateDto() {
    }

    public ItemUpdateDto(
            Long id,
            String name, String description, String color,
            BigDecimal weight,
            BigDecimal height,
            List<String> categories,
            BigDecimal itemPrice,
            BigDecimal sellPrice,
            Integer availableQuantity,
            Integer requestedQuantity,
            Boolean isAvailable,
            String picUrl,
            Instant dateCreated,
            Instant dateUpdated,
            Instant lastSoldAt) {
        this.id = id;
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
        this.picUrl = picUrl;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.lastSoldAt = lastSoldAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Instant dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Instant getLastSoldAt() {
        return lastSoldAt;
    }

    public void setLastSoldAt(Instant lastSoldAt) {
        this.lastSoldAt = lastSoldAt;
    }

}
