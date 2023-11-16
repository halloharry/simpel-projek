package com.photo.dao;

import com.photo.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdAndIsDeleted(Long id, Boolean isDeleted);

    @Query(value = "select * from product p where lower(p.product_nama) ilike %?1% ", nativeQuery = true)
    Slice<Product> findAllProduct(String keywords, Pageable pageable);
}
