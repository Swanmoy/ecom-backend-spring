package com.swanmoy.ecom.services.Customer.review;

import com.swanmoy.ecom.dto.OrderedProductsResponseDto;
import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.dto.ReviewDto;
import com.swanmoy.ecom.entity.*;
import com.swanmoy.ecom.repository.OrderRepository;
import com.swanmoy.ecom.repository.ProductRepository;
import com.swanmoy.ecom.repository.ReviewRepository;
import com.swanmoy.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements  ReviewService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public OrderedProductsResponseDto getOrderedProductDetailsByOrderId(Long orderId){
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto=new OrderedProductsResponseDto();

        if(optionalOrder.isPresent()){
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDto> productDtoList=new ArrayList<>();

            for(CartItems cartItems:optionalOrder.get().getCartItems()){
                ProductDto productDto=new ProductDto();
                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setByteImg(cartItems.getProduct().getImg());
                productDto.setQuantity(cartItems.getQuantity());
                productDtoList.add(productDto);
            }
            orderedProductsResponseDto.setProductDtoList(productDtoList);

        }
        return orderedProductsResponseDto;

    }

    public Boolean giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct=productRepository.findById(reviewDto.getProductId());

        Optional<User> optionalUser=userRepository.findById(reviewDto.getUserId());

        if(optionalProduct.isPresent()&&optionalUser.isPresent()){
            Review review=new Review();
            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

}
