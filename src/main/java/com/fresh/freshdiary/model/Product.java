package com.fresh.freshdiary.model;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_aws_link")
	private String productAWSLink;
	
	@Column(name = "product_aws_qr_link")
	private String productQRLink;

	@Column(name = "product_description")
	private String productDescription;
	
	public Product() {}


	public Product(Long id, String productName, String productAWSLink, 
			String productQRLink, String productDescription) {
		super();
		this.id = id;
		this.productName = productName;
		this.productAWSLink = productAWSLink;
		this.productQRLink = productQRLink;
		this.productDescription = productDescription;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductAWSLink() {
		return productAWSLink;
	}


	public void setProductAWSLink(String productAWSLink) {
		this.productAWSLink = productAWSLink;
	}


	public String getProductQRLink() {
		return productQRLink;
	}


	public void setProductQRLink(String productQRLink) {
		this.productQRLink = productQRLink;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productAWSLink=" + productAWSLink + "]";
	}
	
	

}
