package com.fsse2212_v2.eshop.data.cartItem.data;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.util.Result;

public class CartItemStatusData {
    private Result result;

    public CartItemStatusData(CartItemEntity cartItemEntity) {
        this.result = getStatus(cartItemEntity);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getStatus(CartItemEntity cartItemEntity){
        if(cartItemEntity == null){
            return Result.FAIL;
        }
        return Result.SUCCESS;
    }
}
