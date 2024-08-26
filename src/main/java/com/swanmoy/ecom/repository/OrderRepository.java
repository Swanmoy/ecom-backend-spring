package com.swanmoy.ecom.repository;

import com.swanmoy.ecom.entity.Order;
import com.swanmoy.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findAllByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatusList);
}
