package com.fsse2212_v2.eshop.api;

import com.fsse2212_v2.eshop.config.EnvConfig;
import com.fsse2212_v2.eshop.data.cartItem.exception.BadRequestException;
import com.fsse2212_v2.eshop.data.product.data.ProductRequestData;
import com.fsse2212_v2.eshop.data.product.data.ProductResponseData;
import com.fsse2212_v2.eshop.data.product.dto.ProductRequestDto;
import com.fsse2212_v2.eshop.data.product.dto.ProductResponseDto;
import com.fsse2212_v2.eshop.data.product.dto.ProductToClientDto;
import com.fsse2212_v2.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.devBaseUrl,EnvConfig.productionBaseUrl})
@RequestMapping("/public/product")
public class ProductApi {
    public final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/add") // adding the product
    public ProductResponseDto creatProduct(@RequestBody ProductRequestDto productRequestDto){
        // For debugging usage
//        ProductRequestData productRequestData = new ProductRequestData(productRequestDto);
//        ProductResponseData productResponseData = productService.createProduct(productRequestData);
//        ProductResponseDto productResponseDto = new ProductResponseDto(productResponseData);
//        return productResponseDto;
        return new ProductResponseDto(productService.createProduct(new ProductRequestData(productRequestDto)));
    }

    @GetMapping("")
    // Get all the product
    public List<ProductToClientDto> getAllProduct(){
        List<ProductToClientDto> productToClientDtoList = new ArrayList<>();
        for (ProductResponseData data: productService.getAllProduct()){
            productToClientDtoList.add(new ProductToClientDto(data));
        }
        return productToClientDtoList;
    }

//    @GetMapping("") Testing
//    // Get all the product
//    public String getAllProduct(){
//        return "productToClientDtoList";
//    }

    @GetMapping("/{id}") // get the product by Id
    public ProductResponseDto getProductBySpecificId(@PathVariable(name = "id") Integer pid){
        return new ProductResponseDto(productService.getSpecificProduct(pid));
    }

}
