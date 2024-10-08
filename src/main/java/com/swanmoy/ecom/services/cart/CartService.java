package com.swanmoy.ecom.services.cart;

import com.swanmoy.ecom.dto.AddProductInCartDto;
import com.swanmoy.ecom.dto.OrderDto;
import com.swanmoy.ecom.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId, String code);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto placeOrder(PlaceOrderDto placeOrderDto);
    List<OrderDto> getMyPlacedOrders(Long userId);
    public OrderDto searchOrderByTrackingId(UUID trackingId);
}
