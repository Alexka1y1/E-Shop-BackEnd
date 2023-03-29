package com.fsse2212_v2.eshop.data.transaction_product.data;

import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class TransactionProductData {
    private Integer tpid;

    private Integer pid;

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private Integer stock;

    private Integer quantity;

    private BigDecimal subtotal;


    public TransactionProductData(TransactionProductEntity productEntity) {
        this.tpid = productEntity.getTpid();
        this.pid = productEntity.getPid();
        this.name = productEntity.getName();
        this.description = productEntity.getDescription();
        this.imageUrl = productEntity.getImageUrl();
        this.price = productEntity.getPrice();
        this.stock = productEntity.getStock();
        this.quantity = productEntity.getQuantity();
        this.subtotal = productEntity.getSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
