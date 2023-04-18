package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.config.EnvConfig;
import com.fsse2212_v2.eshop.data.transaction.exception.TransactionException;
import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.service.StripeService;
import com.fsse2212_v2.eshop.service.TransactionProductService;
import com.fsse2212_v2.eshop.service.TransactionService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StripeServiceImpl implements StripeService {

    private final TransactionService transactionService;
    private final TransactionProductService transactionProductService;

    @Autowired
    public StripeServiceImpl(TransactionService transactionService, TransactionProductService transactionProductService) {
        this.transactionService = transactionService;
        this.transactionProductService = transactionProductService;
    }

    @Override
    public String stripePayment(Integer tid, FirebaseUserData firebaseUserData) throws StripeException {
        Stripe.apiKey = "sk_test_51My4sDFQB50nkr57rt7txYcAIP5diAQXLB7uMAkLCJdvOkiTa8jb9Peg5grIoYKRWSa7mowJA39ldXpe4ntauyjo00JrCXC3i4";
        //Pay Transaction -> called payment Api
        if (!transactionService.payTransactionStatusByUserAndTid(firebaseUserData, tid)) {
            throw new TransactionException();
        }
        List<TransactionProductEntity> transactionProductEntities = transactionProductService.getAllTransactionProductByTid(tid);
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (TransactionProductEntity entity : transactionProductEntities) {
            SessionCreateParams.LineItem item = SessionCreateParams.LineItem.builder().
                    setPrice(entity.getStripePriceId()).
                    setQuantity(Long.valueOf(entity.getQuantity())).build();
            lineItems.add(item);
        }


        String YOUR_DOMAIN = EnvConfig.productionBaseUrl;
//        String YOUR_DOMAIN = EnvConfig.productionBaseUrl;
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/#/thankyou?tid=" + tid)
                        .setCancelUrl(YOUR_DOMAIN + "/#/error")
                        .addAllLineItem(lineItems)
                        .build();
        Session session = Session.create(params); //stripe create the check-out session

//        response.redirect(session.getUrl(), 303);
        return session.getUrl();
    }




}
