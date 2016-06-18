package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class Stock implements Serializable{
	@Id
	private int id;
	private int bid;
	private int gid;
	private double price;
	private int number;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"bid\":\"" + this.bid + "\", \"gid\":\"" + 
				gid + "\", \"number\":\"" + this.number + "\",\"date\":\"" + date  + "\",\"price\":\"" + price + "\"}";
		return result;
	}
}
