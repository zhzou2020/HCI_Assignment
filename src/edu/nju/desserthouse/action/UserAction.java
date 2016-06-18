package edu.nju.desserthouse.action;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.PayRecord;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

@Controller
public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3480325286570523762L;

	@Autowired
	private UserManageService userService; 
	
	private String result;
	
	public String changeProfile(){
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		User user = (User)session.get("user");
		user.setName(name);
		user.setGender(Integer.valueOf(gender));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			this.result = "日期必须符合格式！";
		}
		this.result = userService.changeProfile(user);
		return "result";
	}
	
	public String stopUser(){
		result = null;
		String phoneNo = request.getParameter("phoneNo");
		User user = userService.getUserByPhone(phoneNo);
		this.result = userService.stopUser(user);
		if(((User)session.get("user")).getPhoneNo().equals(phoneNo)){
			((User)session.get("user")).setState(-1);
		}
		return "stopresult";
	}
	
	public String pointChange(){
		result = null;
		String point = request.getParameter("change");
		try{
			Integer.parseInt(point);
			int changePoint = Integer.valueOf(point);
			User user = (User) session.get("user");
			if(user == null){
				result = "{\"result\":\"用户未登录\"}";
			} else if(user.getPoint() < changePoint){
				result = "{\"result\":\"积分不足，无法兑换！\"}";
			} else{
				double money = (double)changePoint/10;
				user.setPoint(user.getPoint() - changePoint);
				user.setBalance(user.getBalance() + money);
				userService.changeProfile(user);
				result = "{\"result\":\"您本次兑换积分" + changePoint + ", 获得" + money + "元卡余额，您现在的卡余额为" + user.getBalance() + "元!\", \"point\":\"" + user.getPoint() +"\"}";
			}
		} catch(NumberFormatException ex){
			this.result = "{\"result\":\"积分应为数字！\"}";
		}
		return "result";
	}
	
	public String payRecord(){
		User user = (User)session.get("user");
		if(user == null){
			return INPUT;
		} else{
			String mid = Integer.toString(user.getId());
			List<PayRecord> list = userService.getPayrecord(mid);
			session.put("payrecord", list);
		}
		return SUCCESS;
	}
	
	public String memberInfo(){
		result = null;
		String mid = request.getParameter("id");
		User user = userService.getUserById(mid);
		if(user == null){
			
		} else{
			result = "{\"name\":\"" + user.getName() + "\", \"rank\":\"" + user.getRank() + "\",\"state\":\"" + user.getState() + "\"}";
		}
		
		return "result";
	}
	
	public String Gender(){
		result = null;
		int male = userService.find("gender", "0").size();
		int female = userService.find("gender", "1").size();
		result = "{\"male\":\"" + male + "\",\"female\":\"" + female + "\"}"; 
		return "result";
	}
	
	public String CardState(){
		result = null;
		int pause = userService.find("state", "2").size();
		int valid = userService.find("state", "1").size();
		int notvalid = userService.find("state", "0").size();
		int stop = userService.find("state", "-1").size();
		result = "{\"pause\":\"" + pause + "\",\"valid\":\"" + valid + "\",\"notvalid\":\"" + notvalid + "\",\"stop\":\"" + stop + "\"}"; 
		return "result";
	}
	
	public String profile(){
		User thisuser = (User) session.get("user");
		if(thisuser == null){
			return INPUT;
		} else{
			int mid = thisuser.getId();
			User user = userService.getUserById(Integer.toString(mid));
			session.put("user", user);
			session.put("thisuser", thisuser);
			return SUCCESS;
		}
	}
	
	public String userInfo(){
		if(session.get("employee") == null){
			return INPUT;
		}
		String mid = request.getParameter("id");
		User thisuser = userService.getUserById(mid);
		session.put("thisuser", thisuser);
		return SUCCESS;
	}
	
	public String Age(){
		result = null;
		List<User> list = userService.find("state", "1");
		int age0_20 = 0;
		int age20_30 = 0;
		int age30_40 = 0;
		int age40_50 = 0;
		int age50_60 = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i = 0; i != list.size(); i ++){
			String date = sdf.format(list.get(i).getBirthday());
			if(date.compareTo("1995-01-01") > 0){
				age0_20 ++;
			} else if(date.compareTo("1985-01-01") > 0){
				age20_30 ++;
			} else if(date.compareTo("1975-01-01") > 0){
				age30_40 ++;
			} else if(date.compareTo("1965-01-01") > 0){
				age40_50 ++;
			} else{
				age50_60 ++;
			}
		}
		
		result = "{\"age0_20\":\"" + age0_20 + "\",\"age20_30\":\"" + age20_30 + "\",\"age30_40\":\"" + age30_40 +
				"\",\"age40_50\":\"" + age40_50 + "\",\"age50_60\":\"" + age50_60 + "\"}";
		
		return "result";
	}
	
	public String userList(){
		List<User> list = userService.getAllUser();
		session.put("userlist", list);
		return SUCCESS;
	}
	
	public String getResult(){
		return result;
	}
}
