package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plan_item")
public class PlanItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5710799349244793747L;
	@Id
	private int id;
	private int gid;
	private int pid;
	private int number;
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"gid\":\"" + this.gid + "\", \"pid\":\"" + 
				pid + "\", \"price\":\"" + this.price + "\",\"number\":\"" + number + "\"}";
		return result;
	}
}
