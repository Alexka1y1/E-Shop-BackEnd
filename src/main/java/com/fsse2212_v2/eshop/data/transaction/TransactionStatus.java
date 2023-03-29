package com.fsse2212_v2.eshop.data.transaction;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TransactionStatus {
    PREPARE,
    PROCESSING,
    SUCCESS,
    FAIL
}
