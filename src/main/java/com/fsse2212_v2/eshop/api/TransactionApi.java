package com.fsse2212_v2.eshop.api;

import com.fsse2212_v2.eshop.config.EnvConfig;
import com.fsse2212_v2.eshop.data.transaction.data.TransactionResponseData;
import com.fsse2212_v2.eshop.data.transaction.dto.TransactionResponseDto;
import com.fsse2212_v2.eshop.data.transaction.dto.TransactionStatusDto;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.service.TransactionService;
import com.fsse2212_v2.eshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({EnvConfig.devBaseUrl,EnvConfig.productionBaseUrl})
@RequestMapping("/transaction")
public class TransactionApi {
    public final TransactionService transactionService;

    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createNewTransaction(JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        TransactionResponseData transactionResponseData = transactionService.createTransaction(firebaseUserData);
        return new TransactionResponseDto(transactionResponseData);
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(JwtAuthenticationToken jwtToken,
                                                      @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        return new TransactionResponseDto(transactionService.getTransactionByLoginUserAndTid(firebaseUserData,tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusDto updateTransactionStatus(JwtAuthenticationToken jwtToken,
                                                        @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        if (transactionService.payTransactionStatusByUserAndTid(firebaseUserData,tid)){
            return new TransactionStatusDto();
        }
        throw new RuntimeException();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransaction(JwtAuthenticationToken jwtToken,
                                                    @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        return new TransactionResponseDto(transactionService.finishTransaction(firebaseUserData,tid));
    }


}
