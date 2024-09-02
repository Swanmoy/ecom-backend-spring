package com.swanmoy.ecom.controller.admin;

import com.swanmoy.ecom.dto.FAQDto;
import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.services.admin.faq.FAQService;
import com.swanmoy.ecom.services.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @Autowired
    private FAQService faqService;


    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1=adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products=adminProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> products=adminProductService.getAllProductsByName(name);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId){
        boolean deleted= adminProductService.deleteProduct(productId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/faq/{productId}")
    public  ResponseEntity<FAQDto> postFaq(@PathVariable Long productId, @RequestBody FAQDto faqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto=adminProductService.getProductById(productId);
        if(productDto!=null){
            return ResponseEntity.ok(productDto);
        }else{
            ResponseEntity.notFound().build();
        }
        return null;
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updatedProduct=adminProductService.updateProduct(productId, productDto);
        if(updatedProduct!=null){
            return ResponseEntity.ok(updatedProduct);
        }else{
            ResponseEntity.notFound().build();
        }
        return null;
    }
}
