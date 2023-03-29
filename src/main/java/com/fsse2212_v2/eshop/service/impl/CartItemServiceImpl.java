package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.data.cartItem.data.CartItemResponseData;
import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.cartItem.exception.BadRequestException;
import com.fsse2212_v2.eshop.data.cartItem.exception.CartItemCannotFoundException;
import com.fsse2212_v2.eshop.data.cartItem.exception.InvalidQuantityException;
import com.fsse2212_v2.eshop.data.cartItem.exception.LoginUserNotFoundException;
import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;
import com.fsse2212_v2.eshop.data.product.exception.ProductNotFoundException;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import com.fsse2212_v2.eshop.repository.CartItemRepository;
import com.fsse2212_v2.eshop.service.CartItemService;
import com.fsse2212_v2.eshop.service.ProductService;
import com.fsse2212_v2.eshop.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private static Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    public CartItemServiceImpl(UserService userService,ProductService productService,CartItemRepository cartItemRepository){
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    public boolean addCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        try{
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData); // the loginUser
            if(loginUserEntity==null){
                throw new LoginUserNotFoundException();
            }
            ProductEntity productEntity = productService.getProductEntityById(pid);

            if(validateQuantity(quantity, productEntity.getStock())){
                throw new InvalidQuantityException();
            }

            Optional<CartItemEntity> cartItemEntityOptional = getCartItemEntityByUidAndPid(loginUserEntity.getUid(),pid);
            if(cartItemEntityOptional.isEmpty()){
                cartItemRepository.save(new CartItemEntity(productEntity,loginUserEntity,quantity));
            return true;
            }

            CartItemEntity cartItemEntity = cartItemEntityOptional.get();

            if(cartItemEntity.getQuantity()+quantity > productEntity.getStock()){
                throw new InvalidQuantityException();
            }
            cartItemEntity.setQuantity(cartItemEntity.getQuantity()+quantity);
            cartItemRepository.save(cartItemEntity);
            return true;

        }
        catch (ProductNotFoundException e){
            logger.warn("CartItemService/addCartItem: ProductNotFoundException");
            throw e;
        }
        catch (LoginUserNotFoundException e){
            logger.warn("CartItemService/addCartItem: LoginUserNotFoundException");
            throw e;
        }catch (InvalidQuantityException e){
            logger.warn("CartItemService/addCartItem: InvalidQuantityException");
            throw e;
        }catch (Exception e){
            logger.warn("CartItemService/addCartItem:" + e.toString());
            throw new BadRequestException();
        }

    }

    @Override
    public List<CartItemResponseData> getAllCartItem(FirebaseUserData firebaseUserData){
        try{
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData); // checked if the user existed
            if(loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }
            List<CartItemEntity> cartItemEntityList = getCartItemEntitiesByUid(loginUserEntity.getUid());
            List<CartItemResponseData> responseDataList = new ArrayList<>();
            for(CartItemEntity cartItem: cartItemEntityList){
                responseDataList.add(new CartItemResponseData(cartItem));
            }
            return responseDataList;
        }catch (LoginUserNotFoundException e){
            logger.warn("CartItemService/getAllCartItem: LoginUserNotFoundException");
            throw e;
        }catch (Exception e){
            logger.warn("CartItemService/getAllCartItem: "+ e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public CartItemResponseData updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){

        try {
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            if(loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }
            ProductEntity productEntity = productService.getProductEntityById(pid);

            if(validateQuantity(quantity, productEntity.getStock())){
                throw new InvalidQuantityException();
            }

            Optional<CartItemEntity> cartItemEntityOptional = getCartItemEntityByUidAndPid(loginUserEntity.getUid(),pid);
            if (cartItemEntityOptional.isEmpty()){
                throw new CartItemCannotFoundException();
            }
            CartItemEntity cartItemEntity = cartItemEntityOptional.get();
            cartItemEntity.setQuantity(quantity);
            return new CartItemResponseData(cartItemRepository.save(cartItemEntity));

        }catch(LoginUserNotFoundException e) {
            logger.warn("CartItemService/getAllCartItem: LoginUserNotFoundException");
            throw e;
        }catch (ProductNotFoundException e){
            logger.warn("CartItemService/updateCartQuantity: ProductNotFoundException");
            throw e;
        }catch (InvalidQuantityException e){
            logger.warn("CartItemService/updateCartQuantity: InvalidQuantityException");
            throw e;
        }catch (CartItemCannotFoundException e){
            logger.warn("CartItemService/updateCartQuantity: CartItemCannotFoundException");
            throw e;
        }catch (Exception e){
            logger.warn("CartItemService/updateCartQuantity: " + e.toString());
            throw new BadRequestException();
        }

    }

   @Override
    public boolean deleteCartItem(FirebaseUserData firebaseUserData, Integer pid){
        try{
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            if (loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }

            Optional<CartItemEntity> theCartItem = getCartItemEntityByUidAndPid(loginUserEntity.getUid(), pid);
            if (theCartItem.isPresent()){
                cartItemRepository.delete(theCartItem.get());
                return true;
            }
            throw new CartItemCannotFoundException();

        }catch (LoginUserNotFoundException e){
            logger.warn("CartItemService/deleteCartItem: LoginUserNotFoundException");
            throw e;
        }catch (CartItemCannotFoundException e){
            logger.warn("CartItemService/deleteCartItem: CartItemCannotFoundException");
            throw e;
        }catch (Exception e){
            logger.warn("CartItemService/deleteCartItem: " + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public Optional<CartItemEntity> getCartItemEntityByUidAndPid(Integer uid, Integer pid){
        return cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);
    }

    @Override
    public List<CartItemEntity> getCartItemEntitiesByUid(Integer uid){
        return cartItemRepository.findCartItemEntityByUser_Uid(uid);
    }

    @Override
    @Transactional
    public void emptyAllCartItemsByUser(Integer uid){
        cartItemRepository.deleteByUser_Uid(uid);
    }


    public boolean validateQuantity(Integer quantity, Integer stock){
        return quantity <= 0 || quantity > stock;
    }


}
