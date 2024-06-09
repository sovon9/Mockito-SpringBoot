package product.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import product.Product.controller.ProductController;
import product.Product.dao.ProductRepository;
import product.Product.dto.Coupon;
import product.Product.model.Product;

@SpringBootTest
class ProductApplicationTests {

	private static final String TRY = "TRY";
	@Mock
	private ProductRepository repository;
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private ProductController controller=new ProductController(repository);
	
	@Test
	void testCreateProduct() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String couponUrl =  "http://localhost:8080/api/couponCode/";
		Product product = new Product();
		product.setCouponCode(TRY);
		product.setPrice(100);
		Field field = controller.getClass().getDeclaredField("couponUrl");
		field.setAccessible(true);
		field.set(controller, "http://localhost:8080/api/couponCode/");
		Coupon coupon = new Coupon();
		coupon.setCode(TRY);
		coupon.setDiscount(60);
		when(restTemplate.getForEntity(couponUrl+TRY, Coupon.class)).thenReturn(new ResponseEntity<Coupon>(coupon,HttpStatus.OK));
		when(repository.save(product)).thenReturn(product);
		Product createProduct = controller.createProduct(product);
		verify(restTemplate).getForEntity(couponUrl+TRY, Coupon.class);
		verify(repository).save(product);
		System.out.println(createProduct.getPrice());
		assertNotNull(createProduct);
		assertEquals(40, createProduct.getPrice());
	}

}
