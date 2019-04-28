package com.gmail.nsalahub.reposetory.model;

import java.math.BigDecimal;

public class Item {
    long id;
    String name;
    String description;
    String uniqueNumber;
    BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setDescription(String type) {
        this.description = type;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String unique_number) {
        this.uniqueNumber = unique_number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
