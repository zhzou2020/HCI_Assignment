package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sale_item")
public class SaleItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4930567095325257207L;
	@Id
	private int id;
	private int sid;
	private int gid;
	private int number;
	private double item_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"gid\":\"" + this.gid + "\", \"price\":\"" + this.item_price + "\",\"number\":\"" + number + "\"}";
		return result;
	}
}
