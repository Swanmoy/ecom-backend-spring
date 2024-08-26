package com.swanmoy.ecom.services.admin.product;

import com.swanmoy.ecom.dto.ProductDto;
import com.swanmoy.ecom.entity.Category;
import com.swanmoy.ecom.entity.Product;
import com.swanmoy.ecom.repository.CategoryRepository;
import com.swanmoy.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminProductServiceImpl implements AdminProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductsByName(String name){
        List<Product> products=productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public Boolean deleteProduct(Long id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
       return false;
    }
}
