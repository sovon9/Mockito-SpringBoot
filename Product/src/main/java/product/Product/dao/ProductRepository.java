package product.Product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.Product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
