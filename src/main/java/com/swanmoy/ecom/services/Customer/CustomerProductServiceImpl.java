package com.swanmoy.ecom.services.Customer;

import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.entity.Product;
import com.swanmoy.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

    @Autowired
    private ProductRepository productRepository;
    public List<ProductDto> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductByTitle(String name){
        List<Product> products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
