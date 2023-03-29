package com.fsse2212_v2.eshop.service;

import com.fsse2212_v2.eshop.data.product.data.ProductRequestData;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;
import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductResponseData createProduct(ProductRequestData requestData);


    List<ProductResponseData> getAllProduct();

    ProductResponseData getSpecificProduct(Integer pid);


    ProductEntity getProductEntityById(Integer pid);

    void saveTheProduct(ProductEntity product);
}
