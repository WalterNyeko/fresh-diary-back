package com.fresh.freshdiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.freshdiary.model.Product;
import com.fresh.freshdiary.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/fresh/v1/products", method=RequestMethod.GET)
	public Iterable<Product> listAllProducts(){
		 Iterable<Product> products = productService.listAllProducts();
		 return products;
	}
	
	@RequestMapping(value="/fresh/v1/add/products", method=RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product){
		 productService.saveProduct(product);
		 return product;
	}
	
	@RequestMapping(value="/fresh/v1/products/{productId}", method=RequestMethod.GET)
	public Product getProduct(@PathVariable Long productId) {
		Product product = productService.findById(productId);
		return product;
	}
	
	@RequestMapping(value="/fresh/v1/edit/products/{productId}", method=RequestMethod.POST)
	public Product editProduct(@PathVariable Long productId, @RequestBody Product product) {
		Product theProduct = productService.findById(productId);
		theProduct.setProductName(product.getProductName());
		theProduct.setProductAWSLink(product.getProductAWSLink());
		productService.saveProduct(theProduct);
		return product;
	}
	
	@RequestMapping(value="/fresh/v1/delete/products/{productId}", method=RequestMethod.DELETE)
	public void deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
	}
}
