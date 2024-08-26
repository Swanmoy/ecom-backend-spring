package com.swanmoy.ecom.services.admin.coupon;

import com.swanmoy.ecom.entity.Coupon;
import com.swanmoy.ecom.exception.ValidationException;
import com.swanmoy.ecom.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{
    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon Code already Exists");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }
}
