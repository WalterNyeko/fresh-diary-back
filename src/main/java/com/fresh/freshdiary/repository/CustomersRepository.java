package com.fresh.freshdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fresh.freshdiary.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>{
	Customers findByReceiptNumber(String receiptNumber);
	
	@Query(value="SELECT * FROM Customers WHERE product_id = ?1 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
	Customers findByProduct(Long productId);
}
