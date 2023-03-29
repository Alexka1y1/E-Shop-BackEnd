package com.fsse2212_v2.eshop.data.transaction_product.dto;

import com.fsse2212_v2.eshop.data.product.dto.ProductResponseDto;
import com.fsse2212_v2.eshop.data.transaction_product.data.TransactionProductData;

import java.math.BigDecimal;

public class TransactionProductDto {
    private Integer tpid;

    private ProductResponseDto product = new ProductResponseDto();

    private Integer quantity;

    private BigDecimal subtotal;


    public TransactionProductDto(TransactionProductData data) {
        this.tpid = data.getTpid();
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();
        this.product.setDescription(data.getDescription());
        this.product.setStock(data.getStock());
        this.product.setName(data.getName());
        this.product.setPid(data.getPid());
        this.product.setPrice(data.getPrice());
        this.product.setImageUrl(data.getImageUrl());
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
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
