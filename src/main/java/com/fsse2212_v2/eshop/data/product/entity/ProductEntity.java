package com.fsse2212_v2.eshop.data.product.entity;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.product.data.ProductRequestData;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Integer pid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "image_url", nullable = false, length = 10000)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "category",nullable = false)
    private String category;

    @Column(nullable = true)
    private  String stripePriceId;

    @Column
    private  String stripeProductId;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItemEntityList;


    public ProductEntity() {
    }

    public ProductEntity(ProductRequestData requestData) {
        this.name = requestData.getName();
        this.description = requestData.getDescription();
        this.imageUrl = requestData.getImageUrl();
        this.price = requestData.getPrice();
        this.stock = requestData.getStock();
        this.category =requestData.getCategory();
        this.stripePriceId = requestData.getStripePriceId();
        this.stripeProductId = requestData.getStripeProductId();
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

    public List<CartItemEntity> getCartItemEntityList() {
        return cartItemEntityList;
    }

    public void setCartItemEntityList(List<CartItemEntity> cartItemEntityList) {
        this.cartItemEntityList = cartItemEntityList;
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

    public String getStripeProductId() {
        return stripeProductId;
    }

    public void setStripeProductId(String stripeProductId) {
        this.stripeProductId = stripeProductId;
    }


}
