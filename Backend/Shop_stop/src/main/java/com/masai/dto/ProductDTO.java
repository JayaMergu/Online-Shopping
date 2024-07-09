package com.masai.dto;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ProductDTO {

    private Integer productId;
    private String productName;
    private Double price;
    private String colour;
    private String dimension;
    private String manufacturer;
    private Integer quantity;
    

    // Setters for all fields
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getters are already defined as per previous response
    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getColour() {
        return colour;
    }

    public String getDimension() {
        return dimension;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

