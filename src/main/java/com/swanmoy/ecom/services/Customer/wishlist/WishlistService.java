package com.swanmoy.ecom.services.Customer.wishlist;

import com.swanmoy.ecom.dto.WishlistDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WishlistService {
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto);
    public List<WishlistDto> getWishlistByUserId(Long userId);
}
