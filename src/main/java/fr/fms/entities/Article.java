package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private String brand;
	private double price;
	
	@ManyToOne
	private Category category;
	
	
	public Article(String description, String brand, double price, Category category) {
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = category;
	}
	public Article() {}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ "]";
	}

	
	
}
