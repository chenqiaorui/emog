/**
 * 
 */
package com.emog.model;

/**
 * 商品
 * @author ricky chen
 *
 */
public class Order {
	private long id;
	private String name;
	private String description;
	private String pic;
	private double price;
	private int stock;
	private int sales;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", pic=" + pic + ", price="
				+ price + ", stock=" + stock + ", sales=" + sales + "]";
	}
	
	
	
	
}
