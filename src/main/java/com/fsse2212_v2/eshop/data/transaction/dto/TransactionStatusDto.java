package com.fsse2212_v2.eshop.data.transaction.dto;

import com.fsse2212_v2.eshop.util.Result;

public class TransactionStatusDto {

    private Result result;

    public TransactionStatusDto() {
        this.result = Result.SUCCESS;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
