package com.swanmoy.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swanmoy.ecom.entity.CartItems;
import com.swanmoy.ecom.entity.User;
import com.swanmoy.ecom.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;
    private String address;
    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;
    private UUID trackingId;


    private String userName;

    private String couponName;

    private List<CartItemsDto> cartItems;
}
