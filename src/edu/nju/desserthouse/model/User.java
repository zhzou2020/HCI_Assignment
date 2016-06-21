package edu.nju.desserthouse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4001267502784854386L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String phoneNo;
	private String name;
	private String password;
	private int gender;
	private String city;
	private Date birthday;
	
	private double money;
	private double balance;
	private double consume;
	private int state;
	private Date validDate;
	private int point;
	private int rank;
	
	
	public int getId() {
		return id;
	}
	public String getSerialid(){
		String serialid = Integer.toString(id);
		int length = serialid.length();
		serialid = "";
		for(int i = 0; i != 7-length; i ++){
			serialid += "0";
		}
		serialid += Integer.toString(id);
		return serialid;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwordOne) {
		this.password = passwordOne;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getConsume() {
		return consume;
	}
	public void setConsume(double consume) {
		this.consume = consume;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getBirthdayRegular(){
		String s = DateFormat.getDateInstance(DateFormat.MEDIUM).format(this.birthday);
		return s;
	}
	public String getBalanceRegular(){
		BigDecimal b = new BigDecimal(this.balance);  
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return Double.toString(f1);
	}
}
