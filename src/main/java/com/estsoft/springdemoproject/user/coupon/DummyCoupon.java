package com.estsoft.springdemoproject.user.coupon;

// 테스트 코드에서 사용할 더미 객체의 클래스
public class DummyCoupon implements ICoupon{
    @Override
    public String getName() {
        throw new UnsupportedOperationException("호출되지 않을 예정");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int getDiscountPercent() {
        return 0;
    }

    @Override
    public boolean isAppliable(Item item) {
        return false;
    }

    @Override
    public void doExpire() {

    }
}
