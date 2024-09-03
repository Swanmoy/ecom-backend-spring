package com.swanmoy.ecom.services.Customer;

import com.swanmoy.ecom.dto.ProductDetailDto;
import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.entity.FAQ;
import com.swanmoy.ecom.entity.Product;
import com.swanmoy.ecom.entity.Review;
import com.swanmoy.ecom.repository.FAQRepository;
import com.swanmoy.ecom.repository.ProductRepository;
import com.swanmoy.ecom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FAQRepository faqRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public List<ProductDto> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductByTitle(String name){
        List<Product> products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailDto getProductDetailById(Long productId){
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            List<FAQ> faqList=faqRepository.findAllByProductId(productId);
            List<Review> reviewList=reviewRepository.findAllByProductId(productId);
            ProductDetailDto productDetailDto=new ProductDetailDto();
            productDetailDto.setProductDto(product.getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).toList());
            productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).toList());

            return productDetailDto;
        }
        return null;
    }
}
