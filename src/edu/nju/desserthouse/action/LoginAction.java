package edu.nju.desserthouse.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Controller
public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8492734641389955715L;

	@Autowired
	private UserManageService userService;  

	private User user; 
	private String result;
	
	public String execute(){
		result = null;
		String phoneNo = request.getParameter("phoneNo");
		String password = request.getParameter("password");
		user = userService.validateUser(phoneNo, password);
		if(user == null){
			result = "fail";
		} else{
			session.put("user", user);
			session.remove("employee");
			result = "success";
		}
		return "result";
	}
	
	public String logout(){
		session.remove("user");
		return SUCCESS;
	}
	
	public String getResult(){
		return this.result;
	}
}
