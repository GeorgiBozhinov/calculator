package com.example.calculator.data.repository;

import com.example.calculator.data.base_entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<ProductEntity, Long> {

    List<ProductEntity> findByCandleNameAndCandleJar(String productName, String candleJar);
    List<ProductEntity> findByCandleName(String productName);
    //List<ProductEntity> findAllByPrice(double price, Pageable pageable);
    //Page<ProductEntity> findAllProducts(Pageable pageable);

}
