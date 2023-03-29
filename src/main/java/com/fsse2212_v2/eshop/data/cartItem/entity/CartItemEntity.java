package com.fsse2212_v2.eshop.data.cartItem.entity;

import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity(name="cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_pid",referencedColumnName = "pid",nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "user_uid",referencedColumnName = "uid",nullable = false)
    private UserEntity user;


    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity productEntity, UserEntity user, Integer quantity) {
        this.product = productEntity;
        this.user = user;
        this.quantity = quantity;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }
}
