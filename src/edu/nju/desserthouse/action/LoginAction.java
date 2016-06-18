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
	
	public String execute(){
		String phoneNo = request.getParameter("phoneNo");
		String password = request.getParameter("password");
		user = userService.validateUser(phoneNo, password);
		if(user == null){
			return INPUT;
		} else{
			session.put("user", user);
			session.remove("employee");
			return SUCCESS;
		}
	}
	
	public String logout(){
		session.remove("user");
		return SUCCESS;
	}
}
