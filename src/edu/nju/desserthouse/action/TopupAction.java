package edu.nju.desserthouse.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Controller
public class TopupAction extends BaseAction {
	@Autowired
	private UserManageService userService;

	private User user;
	
	private String result;
	
	public String getResult(){
		return result;
	}

	public String execute() throws ServletException, IOException {
		result = null;
		if (userService == null) {
			System.out.println("user service null");
		}
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String amount = request.getParameter("topupamount");
		try{
			Double.parseDouble(amount);
		} catch(NumberFormatException ex){
			this.result = "充值金额应为数字！";
		}
		result = userService.topupUser(account, password, Double.valueOf(amount), (User)session.get("user"));
		return "result";
	}
}
