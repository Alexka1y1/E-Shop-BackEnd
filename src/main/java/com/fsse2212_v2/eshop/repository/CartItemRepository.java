package com.fsse2212_v2.eshop.repository;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity,Integer> {

    @Query(
            value = "SELECT * FROM cart_item WHERE user_uid = ?1 AND product_pid = ?2",
            nativeQuery = true
    )
    Optional<CartItemEntity> findByUser_UidAndProduct_Pid(Integer uid, Integer pid);

    @Query(
            value = "SELECT * FROM cart_item WHERE user_uid = ?1",
            nativeQuery = true
    )
    List<CartItemEntity> findCartItemEntityByUser_Uid(Integer uid);

    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE user_uid = ?1",
    nativeQuery = true)
    void deleteByUser_Uid(Integer uid);
}
