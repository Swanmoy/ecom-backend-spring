package com.swanmoy.ecom.services.Customer.wishlist;


import com.swanmoy.ecom.dto.WishlistDto;
import com.swanmoy.ecom.entity.Product;
import com.swanmoy.ecom.entity.User;
import com.swanmoy.ecom.entity.Wishlist;
import com.swanmoy.ecom.repository.ProductRepository;
import com.swanmoy.ecom.repository.UserRepository;
import com.swanmoy.ecom.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto){
        Optional<Product> optionalProduct=productRepository.findById(wishlistDto.getProductId());
        Optional<User> optionalUser=userRepository.findById(wishlistDto.getUserId());
        if(optionalProduct.isPresent()&&optionalUser.isPresent()){
            Wishlist wishlist=new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());
            if(wishlistRepository.findByUserIdAndProductId(wishlistDto.getUserId(), wishlistDto.getProductId()).isPresent())
            {
                return null;
            }
            return wishlistRepository.save(wishlist).getWishlistDto();
        }
        return null;
    }

    public List<WishlistDto> getWishlistByUserId(Long userId){
        return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
    }
}
