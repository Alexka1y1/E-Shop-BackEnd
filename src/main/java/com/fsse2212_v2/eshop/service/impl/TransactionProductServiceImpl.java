package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.repository.TransactionProductRepository;
import com.fsse2212_v2.eshop.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    public final TransactionProductRepository transactionProductRepository;

    @Autowired
    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }


   @Override
    public List<TransactionProductEntity> saveTransactionProduct(List<TransactionProductEntity> transactionProducts){
        return (List<TransactionProductEntity>) transactionProductRepository.saveAll(transactionProducts);

    }

}
