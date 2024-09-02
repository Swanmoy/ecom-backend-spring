package com.swanmoy.ecom.controller.Customer;

import com.swanmoy.ecom.dto.OrderedProductsResponseDto;
import com.swanmoy.ecom.dto.ReviewDto;
import com.swanmoy.ecom.services.Customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderedProductDetailsByOrderId(orderId));
    }


    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        Boolean success=reviewService.giveReview(reviewDto);
        if(!success){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!!!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
