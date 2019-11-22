package com.fresh.freshdiary.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresh.freshdiary.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findProductById(Long productId);
}
