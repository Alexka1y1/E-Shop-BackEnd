package com.fsse2212_v2.eshop.data.product.data;

import com.fsse2212_v2.eshop.data.product.dto.ProductRequestDto;

import java.math.BigDecimal;

public class ProductRequestData {
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String stripePriceId;
    private String stripeProductId;

    public ProductRequestData(ProductRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.imageUrl = requestDto.getImageUrl();
        this.price = requestDto.getPrice();
        this.stock = requestDto.getStock();
        this.category = requestDto.getDescription();
        this.stripePriceId = requestDto.getStripePriceId();
        this.stripeProductId = requestDto.getStripeProductId();
    }

    public String getStripeProductId() {
        return stripeProductId;
    }

    public void setStripeProductId(String stripeProductId) {
        this.stripeProductId = stripeProductId;
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

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
    }
}
