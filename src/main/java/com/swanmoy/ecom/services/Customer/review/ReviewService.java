package com.swanmoy.ecom.services.Customer.review;

import com.swanmoy.ecom.dto.OrderedProductsResponseDto;
import com.swanmoy.ecom.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getOrderedProductDetailsByOrderId(Long orderId);
    Boolean giveReview(ReviewDto reviewDto) throws IOException;
}
