package com.fresh.freshdiary.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.freshdiary.aws.AWSConfigurations;
import com.fresh.freshdiary.aws.QRCodeGenerator;
import com.fresh.freshdiary.aws.Slug;
import com.fresh.freshdiary.model.Product;
import com.fresh.freshdiary.repository.ProductRepository;
import com.google.zxing.WriterException;

@Service
public class ProductServiceImpl implements ProductService {
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/qrcodes/";
	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product saveProduct(Product product) {
		try {
			Slug slug = new Slug();
			AWSConfigurations aws = new AWSConfigurations();
			productRepository.save(product);
			Product theProduct = productRepository.findByProductName(product.getProductName());
			
			 try {
				 QRCodeGenerator.generateQRCodeImage("https://fresh-diary-app.herokuapp.com/#/draw/"+theProduct.getId(), 
						 350, 350, QR_CODE_IMAGE_PATH+slug.makeSlug(theProduct.getProductName())+".jpg");
				 theProduct.setProductQRLink(aws.uploadToBucket("alationbucket", slug.makeSlug(theProduct.getProductName()), 
						 QR_CODE_IMAGE_PATH+slug.makeSlug(theProduct.getProductName())+".jpg"));
				 productRepository.save(theProduct);
		     } catch (WriterException e) {
		            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		     } catch (IOException e) {
		            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		     }
			return product;
			
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Iterable<Product> listAllProducts() {
		try {
			Iterable<Product> products = productRepository.findAll();
			return products;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public Product findById(Long productId) {
		try {
			Product product = productRepository.findProductById(productId);
			return product;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Product editProduct(Product product) {
		try {
			productRepository.save(product);
			return product;	
		}catch(Exception e) {
			return null;
		}
	}

}
