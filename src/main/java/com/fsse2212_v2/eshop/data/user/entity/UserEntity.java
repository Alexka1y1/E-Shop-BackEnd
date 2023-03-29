package com.fsse2212_v2.eshop.data.user.entity;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.transaction.entity.TransactionEntity;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String firebaseUid;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<CartItemEntity> buyingList;

    @OneToMany(mappedBy = "loginUser")
    private List<TransactionEntity> transactionEntityList;


    public UserEntity() {
    }

    public UserEntity(FirebaseUserData data) {
        this.firebaseUid = data.getFirebaseUid();
        this.email = data.getEmail();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItemEntity> getBuyingList() {
        return buyingList;
    }

    public void setBuyingList(List<CartItemEntity> buyingList) {
        this.buyingList = buyingList;
    }

    public List<TransactionEntity> getTransactionEntityList() {
        return transactionEntityList;
    }

    public void setTransactionEntityList(List<TransactionEntity> transactionEntityList) {
        this.transactionEntityList = transactionEntityList;
    }
}
