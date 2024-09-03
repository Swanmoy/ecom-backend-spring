package com.swanmoy.ecom.controller.Customer;

import com.swanmoy.ecom.dto.ProductDetailDto;
import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.services.Customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    @Autowired
    private CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products=customerProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> products=customerProductService.searchProductByTitle(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductDetailById(@PathVariable Long productId){
        ProductDetailDto productDetailDto=customerProductService.getProductDetailById(productId);
        if(productDetailDto==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(productDetailDto);
        }
    }
}
