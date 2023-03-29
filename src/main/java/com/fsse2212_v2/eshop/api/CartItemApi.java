package com.fsse2212_v2.eshop.api;

import com.fsse2212_v2.eshop.data.cartItem.data.CartItemResponseData;
import com.fsse2212_v2.eshop.data.cartItem.dto.CartItemResponseDto;
import com.fsse2212_v2.eshop.data.cartItem.dto.CartItemStatusDto;
import com.fsse2212_v2.eshop.data.cartItem.exception.BadRequestException;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.service.CartItemService;
import com.fsse2212_v2.eshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/cart")
public class CartItemApi {

    public final CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}") // add a new item into a cart
    public CartItemStatusDto addCartItem(JwtAuthenticationToken jwtToken,
                              @PathVariable(name = "pid") Integer pid,
                              @PathVariable(name = "quantity") Integer quantity) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        if(cartItemService.addCartItem(firebaseUserData,pid,quantity)){
            return new CartItemStatusDto();
        }
        throw new BadRequestException();

        // firebase (OAuth server) --> gain with JSON
        // successful login --> return the payload
        // firebaseSDK Software Development Kit
    }

    @GetMapping("")
    public List<CartItemResponseDto> getCartItem(JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        List<CartItemResponseDto> dtoList = new ArrayList<>();
        for (CartItemResponseData data: cartItemService.getAllCartItem(firebaseUserData)){
            dtoList.add(new CartItemResponseDto(data));
        }
        return dtoList;
    }

    @PatchMapping("/{pid}/{quantity}") // update the cart quantity for specific product
    public CartItemResponseDto updateCartQuantity(JwtAuthenticationToken jwtToken,
                                                  @PathVariable Integer pid,
                                                  @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        CartItemResponseData cartItemResponseData = cartItemService.updateCartQuantity(firebaseUserData, pid , quantity);
        return new CartItemResponseDto(cartItemResponseData);
    }

    @DeleteMapping("/{pid}")
    public CartItemStatusDto deleteCartItem(JwtAuthenticationToken jwtToken,
                                              @PathVariable Integer pid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUser(jwtToken);
        if (cartItemService.deleteCartItem(firebaseUserData,pid)){
            return new CartItemStatusDto();
        }
        throw new BadRequestException();
    }
}
