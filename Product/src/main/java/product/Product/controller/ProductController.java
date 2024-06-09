package product.Product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import product.Product.dao.ProductRepository;
import product.Product.dto.Coupon;
import product.Product.model.Product;

@RestController
@RequestMapping("/product-api")
public class ProductController {

	private ProductRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	public ProductController(ProductRepository repository)
	{
		this.repository = repository;
	}
	
	@Value("${couponService.url:'http://localhost'}")
	private String couponUrl="";
	
	@PostMapping("/save")
	public Product createProduct(@RequestBody Product product)
	{
		ResponseEntity<Coupon> forEntity = restTemplate.getForEntity(couponUrl+product.getCouponCode(), Coupon.class);
		if(forEntity.getStatusCode().value()==200 && forEntity.hasBody())
		{
			Coupon coupon = forEntity.getBody();
			product.setPrice(product.getPrice()-coupon.getDiscount());
		}
		return repository.save(product);
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable Long id)
	{
		 Optional<Product> findById = repository.findById(id);
		 if(findById.isEmpty())
		 {
			 throw new RuntimeException("no data found for id: "+id);
		 }
		 return findById.get();
	}
	
	
}
