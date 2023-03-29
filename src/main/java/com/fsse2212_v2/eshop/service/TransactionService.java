package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.transaction.data.TransactionResponseData;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;

public interface TransactionService {
    TransactionResponseData createTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByLoginUserAndTid(FirebaseUserData firebaseUserData,
                                                            Integer tid);

    boolean payTransactionStatusByUserAndTid(FirebaseUserData firebaseUserData,
                                             Integer tid);

    TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData,
                                              Integer tid);
}
