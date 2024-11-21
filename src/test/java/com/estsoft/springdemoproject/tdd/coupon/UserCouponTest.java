package com.estsoft.springdemoproject.tdd.coupon;

import com.estsoft.springdemoproject.user.coupon.DummyCoupon;
import com.estsoft.springdemoproject.user.coupon.ICoupon;
import com.estsoft.springdemoproject.user.coupon.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCouponTest {

    @Test
    public void testAddCoupon() {
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = new DummyCoupon();    // 더미 객체

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }

    @Test
    public void 쿠폰이_유효할_경우에만_유저에게_발급() {
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class);
        Mockito.when(coupon.isValid()).thenReturn(true);

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }

    @Test
    public void 쿠폰이_유효하지_않을경우_발급안됨() {
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class);
        Mockito.when(coupon.isValid()).thenReturn(false);

        user.addCoupon(coupon);
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }
}
