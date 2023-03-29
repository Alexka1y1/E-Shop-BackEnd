package com.fsse2212_v2.eshop.repository;

import com.fsse2212_v2.eshop.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(value =
            "SELECT * FROM product",
            nativeQuery = true)
    List<ProductEntity> findAllByNativeQuery();

    @Query(value =
            "SELECT * FROM product " +
                    "WHERE pid = ?1",
            nativeQuery = true)
    Optional<ProductEntity> findProductEntityByPid(Integer pid);

    @Query(value = "SELECT * from product WHERE product.name =:name ", nativeQuery = true)
    Optional<ProductEntity> findProductEntityByName(@RequestParam String name);

}
