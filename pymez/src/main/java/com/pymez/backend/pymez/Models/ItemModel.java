package com.pymez.backend.pymez.Models;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Float weight;
    private Float height;
    private LinkedHashSet<String> categories;
    @Column(name = "item_price")
    private Float itemPrice;
    @Column(name = "sell_price")
    private Float sellPrice;
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

    

    public ItemModel(Long id, String name, String description, String color, Float weight, Float height,
            LinkedHashSet<String> categories, Float itemPrice, Float sellPrice, Integer availableQuantity,
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
    Modifying the ID will not be permitted
    
    public void setId(Long id){
        this.id = id;
    }
    */

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if(name != this.name) {
            this.name = name;
            modifyUpdatedTime();
        }
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if(description != this.description) {
            this.description = description;
            modifyUpdatedTime();
        }
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        if(color != this.color) {
            this.color = color;
            modifyUpdatedTime();
        }
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        if(weight > 0 && weight != this.weight) {
            this.weight = weight;
            modifyUpdatedTime();
        }
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        if(height > 0 && height != this.height) {
            this.height = height;
            modifyUpdatedTime();
        }
        
    }

    public LinkedHashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(LinkedHashSet<String> categories) {
        if(!categories.equals(this.categories)) {
            this.categories = categories;
            modifyUpdatedTime();
        }
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        if(itemPrice > 0 && itemPrice != this.itemPrice) {
            this.itemPrice = itemPrice;
            modifyUpdatedTime();
        }
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        if(sellPrice > 0 && sellPrice != this.sellPrice) {
            this.sellPrice = sellPrice;
            modifyUpdatedTime();
        }
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        if(availableQuantity > 0 && availableQuantity != this.availableQuantity){
            this.availableQuantity = availableQuantity;
            modifyUpdatedTime();
        }
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        if(requestedQuantity > 0 && requestedQuantity != this.requestedQuantity){
            this.requestedQuantity = requestedQuantity;
            modifyUpdatedTime();
        }
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        modifyUpdatedTime();
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /*
    Modifying the date when the item was created will not be permited
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    */

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
        modifyUpdatedTime();
    }

    private void modifyUpdatedTime(){
        setDateUpdated(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "ItemModel [id=" + id + ", name=" + name + ", description=" + description + ", color=" + color
                + ", weight=" + weight + ", height=" + height + ", itemPrice=" + itemPrice + ", sellPrice=" + sellPrice
                + ", availableQuantity=" + availableQuantity + ", requestedQuantity=" + requestedQuantity
                + ", isAvailable=" + isAvailable + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated
                + ", lastSoldAt=" + lastSoldAt + ", getId()=" + getId() + ", getClass()=" + getClass() + ", getName()="
                + getName() + ", getDescription()=" + getDescription() + ", getColor()=" + getColor() + ", getWeight()="
                + getWeight() + ", getHeight()=" + getHeight() + ", getItemPrice()=" + getItemPrice()
                + ", getSellPrice()=" + getSellPrice() + ", getAvailableQuantity()=" + getAvailableQuantity()
                + ", getRequestedQuantity()=" + getRequestedQuantity() + ", getIsAvailable()=" + getIsAvailable()
                + ", getDateCreated()=" + getDateCreated() + ", hashCode()=" + hashCode() + ", getDateUpdated()="
                + getDateUpdated() + ", getLastSoldAt()=" + getLastSoldAt() + ", toString()=" + super.toString() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((height == null) ? 0 : height.hashCode());
        result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
        result = prime * result + ((sellPrice == null) ? 0 : sellPrice.hashCode());
        result = prime * result + ((availableQuantity == null) ? 0 : availableQuantity.hashCode());
        result = prime * result + ((requestedQuantity == null) ? 0 : requestedQuantity.hashCode());
        result = prime * result + ((isAvailable == null) ? 0 : isAvailable.hashCode());
        result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
        result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
        result = prime * result + ((lastSoldAt == null) ? 0 : lastSoldAt.hashCode());
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (height == null) {
            if (other.height != null)
                return false;
        } else if (!height.equals(other.height))
            return false;
        if (itemPrice == null) {
            if (other.itemPrice != null)
                return false;
        } else if (!itemPrice.equals(other.itemPrice))
            return false;
        if (sellPrice == null) {
            if (other.sellPrice != null)
                return false;
        } else if (!sellPrice.equals(other.sellPrice))
            return false;
        if (availableQuantity == null) {
            if (other.availableQuantity != null)
                return false;
        } else if (!availableQuantity.equals(other.availableQuantity))
            return false;
        if (requestedQuantity == null) {
            if (other.requestedQuantity != null)
                return false;
        } else if (!requestedQuantity.equals(other.requestedQuantity))
            return false;
        if (isAvailable == null) {
            if (other.isAvailable != null)
                return false;
        } else if (!isAvailable.equals(other.isAvailable))
            return false;
        if (dateCreated == null) {
            if (other.dateCreated != null)
                return false;
        } else if (!dateCreated.equals(other.dateCreated))
            return false;
        if (dateUpdated == null) {
            if (other.dateUpdated != null)
                return false;
        } else if (!dateUpdated.equals(other.dateUpdated))
            return false;
        if (lastSoldAt == null) {
            if (other.lastSoldAt != null)
                return false;
        } else if (!lastSoldAt.equals(other.lastSoldAt))
            return false;
        return true;
    }



    
}
