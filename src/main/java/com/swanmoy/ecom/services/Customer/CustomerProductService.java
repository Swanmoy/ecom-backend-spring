package com.swanmoy.ecom.services.Customer;

import com.swanmoy.ecom.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProductByTitle(String name);

}
