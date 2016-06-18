package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="plan")
public class Plan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -490916879843030255L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int bid;
	private int state;
	private int eid;
	private Date start_date;
	private Date end_date;
	
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String json(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String result = "{\"id\":\"" + this.id + "\", \"bid\":\"" + this.bid + "\", \"eid\":\"" + 
				eid + "\", \"state\":\"" + this.state + "\",\"start_date\":\"" + sdf.format(start_date)  + "\",\"end_date\":\"" + sdf.format(end_date) + "\"}";
		return result;
	}
}
