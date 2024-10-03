package com.Anudip.hibernateone;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Laptop1 {
	@Id
	private int lid;
	private String brand;
	private int price;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Laptop1() {
		
		// TODO Auto-generated constructor stub
	}

}