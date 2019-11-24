package com.fresh.freshdiary.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.freshdiary.model.Product;
import com.fresh.freshdiary.service.ProductService;

@RestController
@CrossOrigin
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
	
	@RequestMapping(value="/view", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> getImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("qrcodes/cow-milk.jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    
    }
}
