package com.fresh.freshdiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fresh.freshdiary.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>{}