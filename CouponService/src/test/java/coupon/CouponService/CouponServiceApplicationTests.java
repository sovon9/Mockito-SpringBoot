package coupon.CouponService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import coupon.CouponService.controller.CouponRestController;
import coupon.CouponService.dao.CouponRepository;
import coupon.CouponService.model.Coupon;

@SpringBootTest
class CouponServiceApplicationTests {

	private static final String TRY = "TRY";

	@Mock
	private CouponRepository repository;
	
	@InjectMocks
	private CouponRestController controller;
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	void testcreateCoupon() {
		Coupon coupon = new Coupon();
		coupon.setCode(TRY);
		when(repository.save(coupon)).thenReturn(coupon);
		Coupon createCoupon = controller.createCoupon(coupon);
		verify(repository).save(coupon);
		assertNotNull(createCoupon);
		assertEquals(TRY, createCoupon.getCode());
	}
	
	@Test
	void testGetCoupon() {
		Coupon coupon = new Coupon();
		coupon.setCode(TRY);
		coupon.setDiscount(60);
		coupon.setExpDate("06-08-2024");;
		coupon.setId(1);
		when(repository.findByCode(TRY)).thenReturn(coupon);
		Coupon getCoupon = controller.getCoupon(TRY);
		verify(repository).findByCode(TRY);
		assertNotNull(getCoupon);
		assertEquals(60, getCoupon.getDiscount(),"discount value should match");
	}
	
	@Test
	void testCreateCoupon_COUPON_NULL_VALUE() {
		Coupon coupon = null;
		assertThrows(IllegalArgumentException.class, ()->{
			controller.createCoupon(coupon);
		});

	}
	
	@Test
	void testGetCoupon_COUPON_NULL_VALUE() {
		String code = null;
		assertThrows(IllegalArgumentException.class, ()->{
			controller.getCoupon(code);
		});

	}

}
