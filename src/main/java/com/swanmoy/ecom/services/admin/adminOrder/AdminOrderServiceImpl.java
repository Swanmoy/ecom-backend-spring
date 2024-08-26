package com.swanmoy.ecom.services.admin.adminOrder;

import com.swanmoy.ecom.dto.OrderDto;
import com.swanmoy.ecom.entity.Order;
import com.swanmoy.ecom.enums.OrderStatus;
import com.swanmoy.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList=orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));
        return orderList.stream().map(Order::getOrderDto).toList();
    }

    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            if(Objects.equals(status, "Shipped")) {
                order.setOrderStatus(OrderStatus.Shipped);
            }else{
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepository.save(order).getOrderDto();
        }
        return null;
    }
}
