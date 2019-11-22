package com.fresh.freshdiary.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fresh.freshdiary.model.Customers;
import com.fresh.freshdiary.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value = "/fresh/v1/customers", method=RequestMethod.GET)
	public Iterable<Customers> listCustomers() {
		return service.listAllCustomers();
	}
	
	@RequestMapping(value = "/fresh/v1/add/customers", method=RequestMethod.POST)
	public Customers addCustomer(@RequestBody Customers customers) {
		service.saveCustomer(customers);
		return customers;
	}

}
