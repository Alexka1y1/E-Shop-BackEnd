package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import com.fsse2212_v2.eshop.repository.UserRepository;
import com.fsse2212_v2.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
        Optional<UserEntity> userEntity = userRepository.getUserEntityByFirebaseUid(firebaseUserData.getFirebaseUid());
        if(userEntity.isPresent()){
            return userEntity.get();
        }
            return userRepository.save(new UserEntity(firebaseUserData));
    }
}
