package com.swanmoy.ecom.repository;

import com.swanmoy.ecom.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findAllByUserId(Long userId);

    Optional<Wishlist> findByUserIdAndProductId(Long userId, Long productId);
}
