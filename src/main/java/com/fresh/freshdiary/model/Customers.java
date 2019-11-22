package com.fresh.freshdiary.model;
import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customers {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "outlet")
	private String outlet;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "receipt_number")
	private String receiptNumber;
	
	public Customers() {}

	public Customers(Long id, String firstName, String lastName, String outlet, String phoneNumber, String location,
			String receiptNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.outlet = outlet;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.receiptNumber = receiptNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOutlet() {
		return outlet;
	}

	public void setOutlet(String outlet) {
		this.outlet = outlet;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	@Override
	public String toString() {
		return "Customers [firstName=" + firstName + ", lastName=" + lastName + ", outlet=" + outlet + ", phoneNumber="
				+ phoneNumber + ", location=" + location + ", receiptNumber=" + receiptNumber + "]";
	}
	
}
