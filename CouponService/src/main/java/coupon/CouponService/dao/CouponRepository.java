package coupon.CouponService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coupon.CouponService.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);

}
