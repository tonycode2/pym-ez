package com.pymez.backend.pymez.DTOs.Response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

public class ItemResponseDto {
    private String name;
    private String description;
    private String color;
    private BigDecimal weight;
    private BigDecimal height;
    private ArrayList<String> categories;
    private BigDecimal itemPrice;
    private BigDecimal sellPrice;
    private Integer availableQuantity;
    private Integer requestedQuantity;
    private Boolean isAvailable;
    private String picUrl;
    private Instant dateCreated;
    private Instant dateUpdated;
    private Instant lastSoldAt;

    public ItemResponseDto(String name, String description, String color, BigDecimal weight, BigDecimal height,
            ArrayList<String> categories, BigDecimal itemPrice, BigDecimal sellPrice, Integer availableQuantity,
            Integer requestedQuantity, Boolean isAvailable, String picUrl, Instant dateCreated, Instant dateUpdated,
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
        this.picUrl = picUrl;
        this.requestedQuantity = requestedQuantity;
        this.isAvailable = isAvailable;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.lastSoldAt = lastSoldAt;
    }

    public ItemResponseDto() {
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

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
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
