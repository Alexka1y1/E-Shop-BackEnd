package com.fsse2212_v2.eshop.data.cartItem.dto;

public class CartItemStatusDto {

    private String result;

    public CartItemStatusDto() {
        this.result = "SUCCESS";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
