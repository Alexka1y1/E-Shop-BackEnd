package com.fsse2212_v2.eshop.data.cartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2212_v2.eshop.data.cartItem.data.CartItemResponseData;

import java.math.BigDecimal;

public class CartItemResponseDto {

    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("cart_quantity")
    private Integer quantity;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("stock")
    private Integer stock;

    public CartItemResponseDto(CartItemResponseData cartItemResponseData) {
        this.pid = cartItemResponseData.getProduct().getPid();
        this.name = cartItemResponseData.getProduct().getName();
        this.imageUrl = cartItemResponseData.getProduct().getImageUrl();
        this.quantity = cartItemResponseData.getQuantity();
        this.price = cartItemResponseData.getProduct().getPrice();
        this.stock = cartItemResponseData.getProduct().getStock();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
