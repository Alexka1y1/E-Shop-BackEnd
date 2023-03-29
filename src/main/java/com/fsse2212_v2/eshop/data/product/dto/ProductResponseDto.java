package com.fsse2212_v2.eshop.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;

import java.math.BigDecimal;

public class ProductResponseDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("category")
    private String category;

    public ProductResponseDto() {
    }

    public ProductResponseDto(ProductResponseData responseData) {
        this.pid = responseData.getPid();
        this.name = responseData.getName();
        this.description = responseData.getDescription();
        this.imageUrl = responseData.getImageUrl();
        this.price = responseData.getPrice();
        this.stock = responseData.getStock();
        this.category = responseData.getCategory();
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
