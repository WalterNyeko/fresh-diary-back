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
	private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";
	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product saveProduct(Product product) {
		try {
			AWSConfigurations awsConfig = new AWSConfigurations();
			Slug slug = new Slug();
			 try {
				 QRCodeGenerator.generateQRCodeImage(product.getProductName(), 350, 350, QR_CODE_IMAGE_PATH);
		        } catch (WriterException e) {
		            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		        } catch (IOException e) {
		            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		        }
			String qrTitle = product.getProductName()+"-QR";
			String awsProductLink = "https://alationbucket.s3.us-east-2.amazonaws.com/"+slug.makeSlug(product.getProductName());
			String awsQRCodetLink = "https://alationbucket.s3.us-east-2.amazonaws.com/"+slug.makeSlug(qrTitle);
//			if(awsConfig.uploadToBucket("alationbucket", slug.makeSlug(product.getProductName()), product.getProductAWSLink()) &&
//					awsConfig.uploadToBucket("alationbucket", qrTitle, QR_CODE_IMAGE_PATH)) {
				product.setProductAWSLink(awsProductLink);
				product.setProductQRLink(awsQRCodetLink);
				productRepository.save(product);
				return product;
//			}else {
//				return null;
//			}
			
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