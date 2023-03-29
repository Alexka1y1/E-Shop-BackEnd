package com.fsse2212_v2.eshop.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2212_v2.eshop.data.transaction.TransactionStatus;
import com.fsse2212_v2.eshop.data.transaction.data.TransactionResponseData;
import com.fsse2212_v2.eshop.data.transaction_product.data.TransactionProductData;
import com.fsse2212_v2.eshop.data.transaction_product.dto.TransactionProductDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    private Integer tid;

    @JsonProperty("buyer_uid")
    private Integer uid;

    @JsonProperty("date_time")
    @JsonFormat(pattern="yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime datetime;

    @JsonProperty("status")
    private TransactionStatus transactionStatus;

    private BigDecimal total;

    @JsonProperty("item")
    private List<TransactionProductDto> transactionProductDtoList;


    public TransactionResponseDto(TransactionResponseData data) {
        this.tid = data.getTid();
        this.uid = data.getLoginUser().getUid();
        this.datetime = data.getDatetime();
        this.transactionStatus = data.getTransactionStatus();
        this.total = data.getTotal();
        this.transactionProductDtoList = convertList(data);
    }

    public List<TransactionProductDto> convertList(TransactionResponseData data){
        List<TransactionProductDto> transactionProductDtos = new ArrayList<>();
        for(TransactionProductData transactionProductData: data.getTransactionProductDataList()){
            transactionProductDtos.add(new TransactionProductDto(transactionProductData));
        }
        return transactionProductDtos;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
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



    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductDto> getTransactionProductDtoList() {
        return transactionProductDtoList;
    }

    public void setTransactionProductDtoList(List<TransactionProductDto> transactionProductDtoList) {
        this.transactionProductDtoList = transactionProductDtoList;
    }
}
