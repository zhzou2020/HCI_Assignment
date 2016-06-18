package edu.nju.desserthouse.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.PayRecord;
import edu.nju.desserthouse.model.User;

public interface UserManageService {
	public User validateUser(String phoneNo,String password);
	
	public List<User> findString(String colname, String value);
	
	public List<User> find(String colname, String value);

	public Integer validateNumber(String number);
	

	public void sentErrorMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
					throws ServletException,IOException;
	
	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
					throws ServletException,IOException;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String registerUser(User user);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String topupUser(String account, String password, double amount, User user);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void rankChange(User user);
	
	public int getIdByPhone(String phoneNo);
	
	public User getUserById(String id);
	
	public User getUserByPhone(String phoneNo);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String changeProfile(User user);

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String stopUser(User user);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void pauseTask();
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void stopTask();
	
	public List<PayRecord> getPayrecord(String id);
	
	public List<User> getAllUser();
}
