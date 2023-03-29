package com.fsse2212_v2.eshop.repository;

import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {


}
