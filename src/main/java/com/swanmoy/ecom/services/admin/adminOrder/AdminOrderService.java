package com.swanmoy.ecom.services.admin.adminOrder;

import com.swanmoy.ecom.dto.AnalyticsResponseDto;
import com.swanmoy.ecom.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();
    OrderDto changeOrderStatus(Long orderId, String status);
    public AnalyticsResponseDto calculateAnalytics();
}
