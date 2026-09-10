package com.pymez.backend.pymez.DTOs.Response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

public class ItemResponseDto {
    public String name;
    public String description;
    public String color;
    public BigDecimal weight;
    public BigDecimal height;
    public ArrayList<String> categories;
    public BigDecimal itemPrice;
    public BigDecimal sellPrice;
    public Integer availableQuantity;
    public Integer requestedQuantity;
    public Boolean isAvailable;    
    public Instant dateCreated;
    public Instant dateUpdated;
    public Instant lastSoldAt;
}
