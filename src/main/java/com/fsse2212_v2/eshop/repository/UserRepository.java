package com.fsse2212_v2.eshop.repository;

import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM user WHERE firebase_uid = ?1",nativeQuery = true)
    Optional<UserEntity> getUserEntityByFirebaseUid(String firebaseUid);
}
