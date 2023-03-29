package com.fsse2212_v2.eshop.data.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;

import java.math.BigDecimal;

public class ProductToClientDto {

    private Integer pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    private String category;

    @JsonProperty("has_stock")
    private boolean hasStock;

    public ProductToClientDto(ProductResponseData data) {
        this.pid = data.getPid();
        this.name = data.getName();;
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.hasStock = isHasStock(data);
        this.category = data.getCategory();
    }

    public boolean isHasStock(ProductResponseData data){
        return data.getStock() > 0;
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

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
