package com.fsse2212_v2.eshop.data.cartItem.data;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;
import com.fsse2212_v2.eshop.data.user.data.UserResponseData;

public class CartItemResponseData {
    private Integer cid;

    private Integer quantity;

    private ProductResponseData product;

    private UserResponseData user;

    public CartItemResponseData(CartItemEntity cartItem){
        this.cid = cartItem.getCid();
        this.quantity = cartItem.getQuantity();
        this.product = new ProductResponseData(cartItem.getProduct());
        this.user = new UserResponseData(cartItem.getUser());
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

    public ProductResponseData getProduct() {
        return product;
    }

    public void setProduct(ProductResponseData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }
}
