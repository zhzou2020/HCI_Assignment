package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="branch")
public class Branch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7593179966947238802L;
	@Id
	private int id;
	private String name;
	private String address;
	private String info;
	private int state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"name\":\"" + this.name + "\", \"address\":\"" + 
				address + "\", \"info\":\"" + this.info + "\",\"state\":\"" + state  + "\"}";
		return result;
	}
}
