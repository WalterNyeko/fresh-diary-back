package com.fresh.freshdiary.service;

import org.springframework.stereotype.Service;

import com.fresh.freshdiary.model.Customers;

@Service
public interface CustomerService {
	
	Customers saveCustomer(Customers customer);
	
	Iterable<Customers> listAllCustomers();
	
	Customers findCustomerByFirstName(String firstName);
	

}
