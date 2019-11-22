package com.fresh.freshdiary.service;

import java.util.List;

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
	public Customers findCustomerByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
