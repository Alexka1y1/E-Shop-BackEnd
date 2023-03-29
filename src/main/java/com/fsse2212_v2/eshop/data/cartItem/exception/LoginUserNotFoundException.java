package com.fsse2212_v2.eshop.data.cartItem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoginUserNotFoundException extends RuntimeException{
}
