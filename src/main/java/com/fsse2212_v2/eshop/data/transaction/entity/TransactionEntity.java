package com.fsse2212_v2.eshop.data.transaction.entity;

import com.fsse2212_v2.eshop.data.transaction.TransactionStatus;
import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datetime;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "buyerUid", referencedColumnName = "uid", nullable = false)
    private UserEntity loginUser;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProductEntityList;


    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity user) {
        this.datetime = LocalDateTime.now();
        this.transactionStatus = TransactionStatus.PREPARE;
        this.total = BigDecimal.ZERO;
        this.loginUser = user;
        this.transactionProductEntityList= new ArrayList<>();
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

    public UserEntity getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserEntity loginUser) {
        this.loginUser = loginUser;
    }

    public List<TransactionProductEntity> getTransactionProductEntityList() {
        return transactionProductEntityList;
    }

    public void setTransactionProductEntityList(List<TransactionProductEntity> transactionProductEntityList) {
        this.transactionProductEntityList = transactionProductEntityList;
    }
}
