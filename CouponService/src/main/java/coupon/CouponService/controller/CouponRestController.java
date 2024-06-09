package coupon.CouponService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coupon.CouponService.dao.CouponRepository;
import coupon.CouponService.model.Coupon;

@RestController
@RequestMapping("/api")
public class CouponRestController {

	private CouponRepository repository;
	
	@Autowired
	public CouponRestController(CouponRepository repository)
	{
		this.repository = repository;
	}
	
	@PostMapping("/save")
	public Coupon createCoupon(@RequestBody Coupon coupon)
	{
		if(null == coupon)
		{
			throw new IllegalArgumentException("Coupon can't be null");
		}
		return repository.save(coupon);
	}
	
	/**
	 * Finds Coupon data from a coupon code
	 * @param code
	 * @return Coupon obj
	 */
	@GetMapping("/couponCode/{code}")
	public Coupon getCoupon(@PathVariable String code)
	{
		if(null == code)
		{
			throw new IllegalArgumentException("code can't be null");
		}
		return repository.findByCode(code);
	}
	
}
