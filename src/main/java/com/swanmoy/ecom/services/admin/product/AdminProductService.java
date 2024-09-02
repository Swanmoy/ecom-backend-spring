package com.swanmoy.ecom.services.admin.product;

import com.swanmoy.ecom.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {
    ProductDto addProduct(ProductDto productDto) throws IOException;
    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductsByName(String name);

    Boolean deleteProduct(Long id);

    ProductDto getProductById(Long productId);
    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}
