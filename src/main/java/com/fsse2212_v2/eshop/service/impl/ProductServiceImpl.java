package com.fsse2212_v2.eshop.service.impl;

import com.fsse2212_v2.eshop.data.cartItem.exception.BadRequestException;
import com.fsse2212_v2.eshop.data.product.data.ProductRequestData;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;
import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;
import com.fsse2212_v2.eshop.data.product.exception.ProductAlreadyExistException;
import com.fsse2212_v2.eshop.data.product.exception.ProductNotFoundException;
import com.fsse2212_v2.eshop.repository.ProductRepository;
import com.fsse2212_v2.eshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;
    public static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseData createProduct(ProductRequestData requestData) {
        try {
            if(
            requestData.getName().isEmpty()||
            requestData.getPrice()==null||
            requestData.getStock()==null||
            requestData.getImageUrl().isEmpty()){
                throw new BadRequestException();
            }

            if (checkTheProductIsExist(requestData.getName())) {
                throw new ProductAlreadyExistException();
            }
            ProductEntity product = new ProductEntity(requestData);
            productRepository.save(product);
            return new ProductResponseData(product);

        } catch (BadRequestException e){
            logger.warn("ProductService/createProduct: BadRequestException");
            throw e;
        } catch (ProductAlreadyExistException e) {
            logger.warn("ProductService/createProduct: ProductAlreadyExistException");
            throw e;
        } catch (Exception e) {
            logger.warn("ProductService/createProduct: " + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public List<ProductResponseData> getAllProduct() {
        try {
            List<ProductResponseData> productResponseDataList = new ArrayList<>();
            for (ProductEntity product : productRepository.findAllByNativeQuery()) {
                productResponseDataList.add(new ProductResponseData(product));
            }
            return productResponseDataList;
        } catch (Exception e) {
            logger.warn("ProductService/getAllProduct: " + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public ProductResponseData getSpecificProduct(Integer pid) {
        try {
            return new ProductResponseData(getProductEntityById(pid));
        } catch (ProductNotFoundException e) {
            logger.warn("ProductService/getSpecificProduct: ProductNotFoundException");
            throw e;
        } catch (Exception e) {
            logger.warn("ProductService/getSpecificProduct: " + e.toString());
            throw new BadRequestException();
        }
    }

    @Override
    public ProductEntity getProductEntityById(Integer pid) {
        Optional<ProductEntity> product = productRepository.findProductEntityByPid(pid);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void saveTheProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public boolean checkTheProductIsExist(String name) {
        Optional<ProductEntity> product = productRepository.findProductEntityByName(name);
        return product.isPresent();
    }


}
