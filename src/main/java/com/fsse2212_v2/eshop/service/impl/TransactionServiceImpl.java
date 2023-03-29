package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.data.cartItem.entity.CartItemEntity;
import com.fsse2212_v2.eshop.data.cartItem.exception.BadRequestException;
import com.fsse2212_v2.eshop.data.cartItem.exception.LoginUserNotFoundException;
import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;
import com.fsse2212_v2.eshop.data.product.exception.NoProductStockException;
import com.fsse2212_v2.eshop.data.transaction.TransactionStatus;
import com.fsse2212_v2.eshop.data.transaction.data.TransactionResponseData;
import com.fsse2212_v2.eshop.data.transaction.entity.TransactionEntity;
import com.fsse2212_v2.eshop.data.transaction.exception.NoCartItemException;
import com.fsse2212_v2.eshop.data.transaction.exception.TransactionEntityNotFoundException;
import com.fsse2212_v2.eshop.data.transaction.exception.TransactionException;
import com.fsse2212_v2.eshop.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2212_v2.eshop.data.user.FirebaseUserData;
import com.fsse2212_v2.eshop.data.user.entity.UserEntity;
import com.fsse2212_v2.eshop.repository.TransactionRepository;
import com.fsse2212_v2.eshop.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    private final ProductService productService;
    private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  CartItemService cartItemService,
                                  TransactionRepository transactionRepository,
                                  TransactionProductService transactionProductService,
                                  ProductService productService){
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
        this.productService = productService;
    }

    @Override
    public TransactionResponseData createTransaction(FirebaseUserData firebaseUserData){
        try {
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            // Check login user if it is null
            if (loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }

            List<CartItemEntity> cartItemEntityList = cartItemService.getCartItemEntitiesByUid(loginUserEntity.getUid());
            // Check cartItemList if it is empty
            if(cartItemEntityList.isEmpty()){
                throw new NoCartItemException();
            }

            // Find the transactionEntity by user, and saved in the Repository
            TransactionEntity transactionEntity = transactionRepository.save(new TransactionEntity(loginUserEntity));
            List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();

            for (CartItemEntity cartItemEntity: cartItemEntityList){
                TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity,cartItemEntity);
                transactionProductEntityList.add(transactionProductEntity);
            }

            transactionProductEntityList = transactionProductService.saveTransactionProduct(transactionProductEntityList);
            transactionEntity.setTransactionProductEntityList(transactionProductEntityList);
            transactionEntity.setTotal(calculateTotalPrice(transactionProductEntityList));
            return new TransactionResponseData(transactionRepository.save(transactionEntity));

        }catch (LoginUserNotFoundException e){
            logger.warn("TransactionService/createTransaction : LoginUserNotFoundException");
            throw e;
        }catch (NoCartItemException e){
            logger.warn("TransactionService/createTransaction : NoCartItemException");
            throw e;
        }catch (Exception e){
            logger.warn("TransactionService/createTransaction :" + e.toString());
            throw new BadRequestException();
        }


    }

    @Override
    public TransactionResponseData getTransactionByLoginUserAndTid(FirebaseUserData firebaseUserData,
                                                                   Integer tid){
        try{
            // Check Login User
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            if (loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }
            TransactionEntity transactionEntity = getTransactionByUserAndTid(loginUserEntity.getUid(), tid);
            return new TransactionResponseData(transactionEntity);
        }catch (LoginUserNotFoundException e){
            logger.warn("TransactionService/getTransactionByLoginUserAndTid : LoginUserNotFoundException");
            throw e;
        }catch (TransactionEntityNotFoundException e){
            logger.warn("TransactionService/getTransactionByLoginUserAndTid : TransactionEntityNotFoundException");
            throw e;
        }catch (Exception e){
            logger.warn("TransactionService/getTransactionByLoginUserAndTid :" + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public boolean payTransactionStatusByUserAndTid(FirebaseUserData firebaseUserData,
                                                    Integer tid){
        try{
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            if (loginUserEntity == null){
                throw new LoginUserNotFoundException();
            }
            TransactionEntity transactionEntity = getTransactionByUserAndTid(loginUserEntity.getUid(), tid);
            if(transactionEntity.getTransactionStatus()==TransactionStatus.PREPARE){

                // Check if the product stock >= the transaction product quantity
                // If yes, then deduce the product stock
                for (TransactionProductEntity transactionProduct: transactionEntity.getTransactionProductEntityList()){
                    ProductEntity theProduct = productService.getProductEntityById(transactionProduct.getPid());
                    if(theProduct.getStock()>=transactionProduct.getQuantity()){
                        theProduct.setStock(theProduct.getStock() - transactionProduct.getQuantity());
                        productService.saveTheProduct(theProduct);
                    }else {
                        transactionEntity.setTransactionStatus(TransactionStatus.FAIL);
                        transactionRepository.save(transactionEntity);
                        throw new NoProductStockException();
                    }
                }
                transactionEntity.setTransactionStatus(TransactionStatus.PROCESSING);
                transactionRepository.save(transactionEntity);
                return true;
            }
            throw new TransactionException();

        }catch (LoginUserNotFoundException e){
            logger.warn("TransactionService/updateTransactionStatusByUserAndTid : LoginUserNotFoundException");
            throw e;
        }catch (TransactionEntityNotFoundException e){
            logger.warn("TransactionService/updateTransactionStatusByUserAndTid : TransactionEntityNotFoundException");
            throw e;
        }catch (NoProductStockException e){
            logger.warn("TransactionService/updateTransactionStatusByUserAndTid : NoProductStockException");
            throw e;
        }catch (TransactionException e){
            logger.warn("TransactionService/updateTransactionStatusByUserAndTid : TransactionException");
            throw e;
        }catch (Exception e){
            logger.warn("TransactionService/updateTransactionStatusByUserAndTid :" + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    @Transactional
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData,
                                                     Integer tid){
        try{
            UserEntity loginUserEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            TransactionEntity transactionEntity = getTransactionByUserAndTid(loginUserEntity.getUid(), tid);
            if(transactionEntity.getTransactionStatus()== TransactionStatus.PROCESSING){
                transactionEntity.setTransactionStatus(TransactionStatus.SUCCESS);
                cartItemService.emptyAllCartItemsByUser(loginUserEntity.getUid());
                return new TransactionResponseData(transactionRepository.save(transactionEntity));
            }
            transactionEntity.setTransactionStatus(TransactionStatus.FAIL);
            transactionRepository.save(transactionEntity);
            throw new TransactionException();


        }catch (LoginUserNotFoundException e){
            logger.warn("TransactionService/finishTransaction: LoginUserNotFoundException");
            throw e;
        }catch (TransactionEntityNotFoundException e){
            logger.warn("TransactionService/finishTransaction: TransactionEntityNotFoundException");
            throw e;
        }catch (TransactionException e){
            logger.warn("TransactionService/finishTransaction: TransactionException");
            throw e;
        }catch (Exception e){
            logger.warn("TransactionService/finishTransaction: " + e.toString());
            throw new BadRequestException();
        }
    }

    public BigDecimal calculateTotalPrice(List<TransactionProductEntity> transactionProductEntityList){
        BigDecimal total = new BigDecimal(0);
        for(TransactionProductEntity transactionProduct: transactionProductEntityList){
            total = total.add(transactionProduct.getSubtotal()) ;
        }
        return total;
    }

    public TransactionEntity getTransactionByUserAndTid(Integer uid, Integer tid){
        Optional<TransactionEntity> transaction = transactionRepository.findByLoginUser_UidAndTid(uid,tid);
        if(transaction.isPresent()){
            return transaction.get();
        }
        throw new TransactionEntityNotFoundException();
    }


}
