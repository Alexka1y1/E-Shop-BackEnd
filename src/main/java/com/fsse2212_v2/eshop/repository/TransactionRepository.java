package com.fsse2212_v2.eshop.repository;

import com.fsse2212_v2.eshop.data.transaction.entity.TransactionEntity;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    Optional<TransactionEntity> findByLoginUserAndTid(UserEntity user, Integer tid);

    @Query(
            value = "SELECT * FROM transaction t INNER JOIN user u ON t.buyer_uid = u.uid WHERE buyer_uid = ?1 AND tid =?2",
            nativeQuery = true
    )
    Optional<TransactionEntity> findByLoginUser_UidAndTid(Integer uid, Integer tid);

}
