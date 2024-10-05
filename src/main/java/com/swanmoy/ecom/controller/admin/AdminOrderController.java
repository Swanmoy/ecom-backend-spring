package com.swanmoy.ecom.controller.admin;

import com.swanmoy.ecom.dto.AnalyticsResponseDto;
import com.swanmoy.ecom.dto.OrderDto;
import com.swanmoy.ecom.services.admin.adminOrder.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrders(){
        return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){
        OrderDto orderDto=adminOrderService.changeOrderStatus(orderId, status);
        if(orderDto==null){
            return new ResponseEntity<>("Something went Wrong!", HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(orderDto);
        }
    }

    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponseDto> getAnalytics(){
        return ResponseEntity.ok(adminOrderService.calculateAnalytics());
    }
}
