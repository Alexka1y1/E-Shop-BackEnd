package com.fsse2212_v2.eshop.data.transaction.data;

import com.fsse2212_v2.eshop.data.transaction.TransactionStatus;
import com.fsse2212_v2.eshop.data.transaction.entity.TransactionEntity;
import com.fsse2212_v2.eshop.data.transaction_product.data.TransactionProductData;
import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.data.user.data.UserResponseData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {
    private Integer tid;

    private LocalDateTime datetime;

    private TransactionStatus transactionStatus;

    private BigDecimal total;

    private UserResponseData loginUser;

    private List<TransactionProductData> transactionProductDataList;

    public TransactionResponseData(TransactionEntity transactionEntity) {
        this.tid = transactionEntity.getTid();
        this.datetime = transactionEntity.getDatetime();
        this.transactionStatus = transactionEntity.getTransactionStatus();
        this.total = transactionEntity.getTotal();
        this.loginUser = new UserResponseData(transactionEntity.getLoginUser());
        this.transactionProductDataList = convertList(transactionEntity);

    }

    public List<TransactionProductData> convertList(TransactionEntity transactionEntity){
        List<TransactionProductData> transactionProductDataArrayList = new ArrayList<>();
        for (TransactionProductEntity transactionProductEntity: transactionEntity.getTransactionProductEntityList()){
            transactionProductDataArrayList.add(new TransactionProductData(transactionProductEntity));
        }
        return transactionProductDataArrayList;
    }

    public List<TransactionProductData> getTransactionProductDataList() {
        return transactionProductDataList;
    }

    public void setTransactionProductDataList(List<TransactionProductData> transactionProductDataList) {
        this.transactionProductDataList = transactionProductDataList;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public UserResponseData getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserResponseData loginUser) {
        this.loginUser = loginUser;
    }
}
