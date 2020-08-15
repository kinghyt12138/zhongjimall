package com.example.zhongjimall.repository;


import com.example.zhongjimall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByid(long id);
}