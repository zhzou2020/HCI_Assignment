package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sale")
public class Sale implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4633855057812158702L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int bid;
	private int mid;
	private String date;
	private int salesman_id;
	private int pay_way;
	private int state;
	private double amount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
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
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(int salesman_id) {
		this.salesman_id = salesman_id;
	}
	public int getPay_way() {
		return pay_way;
	}
	public void setPay_way(int pay_way) {
		this.pay_way = pay_way;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"bid\":\"" + this.bid + "\", \"mid\":\"" + 
				mid + "\", \"date\":\"" + this.date + "\",\"salesman_id\":\"" + this.salesman_id + 
				"\",\"pay_way\":\"" + this.pay_way + "\",\"state\":\"" + this.state + "\",\"amount\":\"" + this.amount +"\"}";
		return result;
	}
}
