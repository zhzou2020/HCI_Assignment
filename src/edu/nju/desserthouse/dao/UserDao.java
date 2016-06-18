package edu.nju.desserthouse.dao;

import java.util.Date;
import java.util.List;

import edu.nju.desserthouse.model.User;

public interface UserDao
{
	/**
	 * 
	 * @param user
	 */
	public void save(User user);

	
	/**
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public List<User> find(String column,String value);
	
	/**
	 * 
	 * @param userid
	 * @return
	 */
	public User findById(String userid);
	
	/**
	 * 
	 * @param user
	 */
	public void updateByUserid(User user);
	
	/**
	 * 
	 * @param endTime
	 * @return
	 */
	public List<User> findBeforeTime(Date endTime , String state);
	
	public List<User> findString(String column, String value);
	
	public List<User> findAllUser();
	
}
