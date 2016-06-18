package edu.nju.desserthouse.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.service.UserManageService;

@Controller
public class AnalyseAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -494894041270754557L;
	private String result;
	
	@Autowired
	private UserManageService userService;

	public String LivePlace(){
		result = null;
		int nanjing = userService.findString("city", "南京").size();
		int jiaxing = userService.findString("city", "嘉兴").size();
		int shanghai = userService.findString("city", "上海").size();
		int guangzhou = userService.findString("city", "广州").size();
		int chengdu = userService.findString("city", "成都").size();
		int beijing = userService.findString("city", "北京").size();
		result = "{\"nanjing\":\"" + nanjing + "\",\"jiaxing\":\"" + jiaxing + "\",\"shanghai\":\"" + shanghai + 
				"\",\"guangzhou\":\"" + guangzhou + "\", \"chengdu\":\"" + chengdu + "\",\"beijing\":\"" + beijing + "\"}"; 
		return "result";
	}
	
	public String getResult(){
		return result;
	}
}
