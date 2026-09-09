package com.pymez.backend.pymez.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String color;
    private BigDecimal weight;
    private BigDecimal height;
    @ElementCollection 
    private ArrayList<String> categories;
    @Column(name = "item_price")
    private BigDecimal itemPrice;
    @Column(name = "sell_price")
    private BigDecimal sellPrice;
    @Column(name = "available_quantity")
    private Integer availableQuantity;
    @Column(name = "requested_quantity")
    private Integer requestedQuantity;
    @Column(name = "is_available")
    private Boolean isAvailable;    
    @Column(name = "date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;
    @Column(name = "last_sold_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSoldAt;

    public ItemModel(Long id, String name, String description, String color, BigDecimal weight, BigDecimal height,
            ArrayList<String> categories, BigDecimal itemPrice, BigDecimal sellPrice, Integer availableQuantity,
            Integer requestedQuantity, Boolean isAvailable, LocalDateTime dateCreated, LocalDateTime dateUpdated,
            LocalDateTime lastSoldAt) {
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
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.lastSoldAt = lastSoldAt;
    }

    public ItemModel() {
    }

    

    public Long getId() {
        return id;
    }
    /*
    public void setId(Long id) {
        this.id = id;
    }
    */

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

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public LocalDateTime getLastSoldAt() {
        return lastSoldAt;
    }

    public void setLastSoldAt(LocalDateTime lastSoldAt) {
        this.lastSoldAt = lastSoldAt;
    }

    //toString
    @Override
    public String toString() {
        return "ItemModel [id=" + id + ", name=" + name + ", description=" + description + ", color=" + color
                + ", weight=" + weight + ", height=" + height + ", itemPrice=" + itemPrice + ", sellPrice=" + sellPrice
                + ", availableQuantity=" + availableQuantity + ", requestedQuantity=" + requestedQuantity
                + ", isAvailable=" + isAvailable + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated
                + ", lastSoldAt=" + lastSoldAt + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemModel other = (ItemModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @PrePersist 
    protected void onCreate(){
        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }

    @PreUpdate 
    protected void onUpdate(){
        this.dateUpdated = LocalDateTime.now();
    }

}
