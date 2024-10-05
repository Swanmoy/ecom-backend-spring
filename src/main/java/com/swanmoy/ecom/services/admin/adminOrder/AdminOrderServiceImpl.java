package com.swanmoy.ecom.services.admin.adminOrder;

import com.swanmoy.ecom.dto.AnalyticsResponseDto;
import com.swanmoy.ecom.dto.OrderDto;
import com.swanmoy.ecom.entity.Order;
import com.swanmoy.ecom.enums.OrderStatus;
import com.swanmoy.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public AnalyticsResponseDto calculateAnalytics(){
        Long placed= orderRepository.countByOrderStatus(OrderStatus.Placed);
        Long shipped=orderRepository.countByOrderStatus(OrderStatus.Shipped);
        Long delivered=orderRepository.countByOrderStatus(OrderStatus.Delivered);
        LocalDate currentDate=LocalDate.now();
        LocalDate previousMonthDate=currentDate.minusMonths(1);
        Long currentMonthOrders=getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthOrders=getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());
        Long currentMonthEarnings=getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthEarnings=getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());
        return new AnalyticsResponseDto(placed, shipped, delivered, currentMonthOrders, previousMonthOrders, currentMonthEarnings, previousMonthEarnings);
    }
    public Long getTotalOrdersForMonth(int month, int year){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startOfMonth=calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endOfMonth=calendar.getTime();
        List<Order> orders=orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);
        return (long) orders.size();
    }
    public Long getTotalEarningsForMonth(int month, int year){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startOfMonth=calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endOfMonth=calendar.getTime();
        List<Order> orders=orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);
        Long sum=0L;
        for (Order order : orders) {
            sum = sum + order.getAmount();
        }
        return sum;
    }
}
