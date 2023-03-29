package com.fsse2212_v2.eshop.data.transaction_product.entity;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "tid",referencedColumnName = "tid",nullable = false)
    private TransactionEntity transaction;

    public TransactionProductEntity() {

    }
    public TransactionProductEntity(TransactionEntity transactionEntity,CartItemEntity cartItem) {
        this.pid = cartItem.getProduct().getPid();
        this.name = cartItem.getProduct().getName();
        this.price = cartItem.getProduct().getPrice();
        this.description = cartItem.getProduct().getDescription();
        this.imageUrl = cartItem.getProduct().getImageUrl();
        this.stock = cartItem.getProduct().getStock();
        this.quantity = cartItem.getQuantity();
        setSubtotal(cartItem);
        this.transaction = transactionEntity;
    }


;
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

    public void setSubtotal(BigDecimal bigDecimal) {
        this.subtotal = bigDecimal;
    }

    public void setSubtotal(CartItemEntity cartItem){
        this.subtotal = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }
}
