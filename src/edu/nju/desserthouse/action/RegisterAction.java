package edu.nju.desserthouse.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Controller
public class RegisterAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserManageService userService;  

	private User user; 
	
	public String execute() 
					throws ServletException,IOException
	{
		if (userService==null ){
			System.out.println("user service null");
		}
		
		String message="";
		
		user = new User();
		
		user.setPhoneNo(request.getParameter("phoneNo"));
		user.setCity(request.getParameter("city"));
		user.setName(request.getParameter("name"));
		user.setGender(Integer.valueOf(request.getParameter("gender")));
		
		if(request.getParameter("passwordOne").equals(request.getParameter("passwordTwo"))){
			user.setPassword(request.getParameter("passwordOne"));
		}
		else{
			message += "Password not match!<br>";
			userService.sentMessage(message,request);
			return INPUT;
		}
		
		String dateStr=request.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = null;
		try {
			date = (Date) sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dateStr);
		user.setBirthday(date);
		
		message = userService.registerUser(user);
		if(message != null){
			session.put("errormessage", message);
			return INPUT;
		}
		else{
			session.put("user", user);
			session.remove("employee");
			return SUCCESS;
		}
		
    }
	
	public void setUser(User user) {
		this.user = user;
		System.out.println(user.getId()+" user");
	}

	public User getUser() {
		if (user==null){
			System.out.println("user null");
		}
		return user;
	}
}
