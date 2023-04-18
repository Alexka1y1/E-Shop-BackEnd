package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.stripe.exception.StripeException;

public interface StripeService {
    String stripePayment(Integer tid, FirebaseUserData firebaseUserData) throws StripeException;
}
