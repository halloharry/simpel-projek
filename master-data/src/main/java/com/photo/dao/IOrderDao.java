package com.photo.dao;

import com.photo.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderDao extends JpaRepository<Order, Long> {
    @Query(value = "select * FROM tbl_order o where o.user_id = ?1", nativeQuery = true)
    List<Order> findAllByUserId(Long userId);


    @Query(value = "select * from tbl_order o where o.is_deleted = false ", nativeQuery = true)
    Slice<Order> findAllOrder(Pageable pageable);
}
