package com.fresh.freshdiary.service;

import org.springframework.stereotype.Service;

import com.fresh.freshdiary.model.Product;

@Service
public interface ProductService {
	
	Product saveProduct(Product product);
	
	Iterable<Product> listAllProducts();
	
	Product editProduct(Product product);
	
	Product findById(Long productId);
	
	void deleteProduct(Long productId);

}
