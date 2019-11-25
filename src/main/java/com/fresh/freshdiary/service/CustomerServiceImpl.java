package com.fresh.freshdiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.freshdiary.model.Customers;
import com.fresh.freshdiary.repository.CustomersRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomersRepository customersRepository;
	
	@Override
	public Customers saveCustomer(Customers customer) {
		try {
			customersRepository.save(customer);
			return customer;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Iterable<Customers> listAllCustomers() {
		try {
			Iterable<Customers> allCustomers = customersRepository.findAll();
			return allCustomers;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Customers findByReceiptNumber(String receiptNumber) {
		Customers customer = customersRepository.findByReceiptNumber(receiptNumber);
		return customer;
	}

}
