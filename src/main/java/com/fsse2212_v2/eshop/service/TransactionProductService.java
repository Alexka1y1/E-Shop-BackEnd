package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {

    List<TransactionProductEntity> saveTransactionProduct(List<TransactionProductEntity> transactionProducts);
}
