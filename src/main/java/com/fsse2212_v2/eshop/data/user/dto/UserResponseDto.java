package com.fsse2212_v2.eshop.data.user.dto;

import com.fsse2212_v2.eshop.data.user.data.UserResponseData;

public class UserResponseDto {
    private Integer uid;

    private String firebaseUid;

    private String email;

//    private List<CartItemResponseDto> buyingList;

    public UserResponseDto(UserResponseData userResponseData) {
        this.uid = userResponseData.getUid();
        this.firebaseUid = userResponseData.getFirebaseUid();
        this.email = userResponseData.getEmail();
//        this.buyingList = convertList(userResponseData);
    }

//    public List<CartItemResponseDto> convertList(UserResponseData userResponseData){
//        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
//        for (CartItemResponseData cartItemResponseData : userResponseData.getBuyingList()){
//            cartItemResponseDtoList.add(new CartItemResponseDto(cartItemResponseData));
//        }
//        return cartItemResponseDtoList;
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

//    public List<CartItemResponseDto> getBuyingList() {
//        return buyingList;
//    }
//
//    public void setBuyingList(List<CartItemResponseDto> buyingList) {
//        this.buyingList = buyingList;
//    }
}
