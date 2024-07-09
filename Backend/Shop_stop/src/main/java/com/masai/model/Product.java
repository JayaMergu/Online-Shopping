package com.masai.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @NotBlank(message = "{Product.name.invalid}")
    @NotEmpty(message = "{Product.name.invalid}")
    @NotNull(message = "{Product.name.invalid}")
    private String productName;

    @NotNull(message = "{Product.price.invalid}")
    private Double price;

    private String colour;

    private String dimension;

    @NotBlank(message = "{Product.specification.invalid}")
    @NotEmpty(message = "{Product.specification.invalid}")
    @NotNull(message = "{Product.specification.invalid}")
    private String specification;

    @NotBlank(message = "{Product.manufacturer.invalid}")
    @NotEmpty(message = "{Product.manufacturer.invalid}")
    @NotNull(message = "{Product.manufacturer.invalid}")
    private String manufacturer;

    @Embedded
    private Category category;

    @NotNull(message = "{Product.quantity.invalid}")
    private Integer quantity;

    // Add getters and setters for all fields
    // Example:
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
