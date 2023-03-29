package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.cartItem.data.CartItemResponseData;
import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    boolean addCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getAllCartItem(FirebaseUserData firebaseUserData);

    CartItemResponseData updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    boolean deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    Optional<CartItemEntity> getCartItemEntityByUidAndPid(Integer uid, Integer pid);

    List<CartItemEntity> getCartItemEntitiesByUid(Integer uid);

    @Transactional
    void emptyAllCartItemsByUser(Integer uid);

}
