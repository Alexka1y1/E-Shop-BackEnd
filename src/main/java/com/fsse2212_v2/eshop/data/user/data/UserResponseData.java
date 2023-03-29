package com.fsse2212_v2.eshop.data.user.data;

import com.fsse2212_v2.eshop.data.user.entity.UserEntity;

public class UserResponseData {
    private Integer uid;

    private String firebaseUid;

    private String email;

//    private List<CartItemResponseData> buyingList;

    public UserResponseData(UserEntity user) {
        this.uid = user.getUid();
        this.firebaseUid = user.getFirebaseUid();
        this.email = user.getEmail();
//        this.buyingList = convertList(user);

    }
//
//    public List<CartItemResponseData> convertList (UserEntity user){
//        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
//        for(CartItemEntity cartItemEntity: user.getBuyingList()){
//            cartItemResponseDataList.add(new CartItemResponseData(cartItemEntity));
//        }
//        return cartItemResponseDataList;
//    }

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

//    public List<CartItemResponseData> getBuyingList() {
//        return buyingList;
//    }
//
//    public void setBuyingList(List<CartItemResponseData> buyingList) {
//        this.buyingList = buyingList;
//    }
}
