package com.fsse2212_v2.eshop.api;

import com.fsse2212_v2.eshop.config.EnvConfig;
import com.fsse2212_v2.eshop.data.transaction.exception.TransactionException;
import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.repository.TransactionProductRepository;
import com.fsse2212_v2.eshop.service.StripeService;
import com.fsse2212_v2.eshop.service.TransactionService;
import com.fsse2212_v2.eshop.util.JwtUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.devBaseUrl,EnvConfig.productionBaseUrl})
public class StripeApi {
    private final StripeService stripeService;

    @Autowired
    public StripeApi(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-checkout-session/{tid}")
    public String createCheckoutSession(@PathVariable Integer tid, JwtAuthenticationToken jwtToken) throws StripeException {
        Stripe.apiKey = "sk_test_51My4sDFQB50nkr57rt7txYcAIP5diAQXLB7uMAkLCJdvOkiTa8jb9Peg5grIoYKRWSa7mowJA39ldXpe4ntauyjo00JrCXC3i4";
        //Pay Transaction
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        String redirectPath = stripeService.stripePayment(tid,firebaseUserData);
        return redirectPath;
    }
}
