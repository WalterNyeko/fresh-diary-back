package com.fresh.freshdiary.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.workspaces.model.ResourceNotFoundException;
import com.fresh.freshdiary.model.Customers;
import com.fresh.freshdiary.model.Product;
import com.fresh.freshdiary.service.CustomerService;
import com.fresh.freshdiary.service.ProductService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/fresh/v1/customers", method=RequestMethod.GET)
	public Iterable<Customers> listCustomers() {
		return service.listAllCustomers();
	}
	
	@RequestMapping(value = "/fresh/v1/product/{productId}/add/customers", method=RequestMethod.POST)
	public Customers addCustomer(@PathVariable(value="productId") Long productId, @RequestBody Customers customers) {
		Product product = productService.findById(productId);
		if(product != null) {
			customers.setProduct(product);
			service.saveCustomer(customers);
		}else {
			throw new ResourceNotFoundException("Product " + productId + " not found");
		}
		return customers;
		
	}
	
	@RequestMapping(value="/fresh/v1/product/{productId}/winner")
	public Customers getWinningCustomerPerProduct(@PathVariable(value="productId") Long productId) {
		Customers theWinner = service.findByProduct(productId);
		return theWinner;
	}

}
