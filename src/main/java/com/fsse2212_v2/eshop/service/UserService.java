package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData);
}
