package edu.nju.desserthouse.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preorder")
public class Preorder {
	@Id
	private int id;
	private int sid;
	private String date;
	private int mid;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String json(){
		String result = "{\"id\":\"" + this.id + "\", \"sid\":\"" + this.sid + "\", \"date\":\"" + 
				date + "\", \"mid\":\"" + this.mid +"\"}";
		return result;
	}
}
