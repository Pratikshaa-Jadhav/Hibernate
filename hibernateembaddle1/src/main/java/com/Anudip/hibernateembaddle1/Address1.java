package com.Anudip.hibernateembaddle1;

import javax.persistence.Embeddable;

@Embeddable
public class Address1 {
	private String city;
	private String address1;
	private String address2;
	private String pincode;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Address1() {
		
		// TODO Auto-generated constructor stub
	}
	

}