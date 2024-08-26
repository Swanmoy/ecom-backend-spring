package com.swanmoy.ecom.services.admin.coupon;

import com.swanmoy.ecom.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
