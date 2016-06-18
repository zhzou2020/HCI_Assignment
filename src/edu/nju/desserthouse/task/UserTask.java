package edu.nju.desserthouse.task;


import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.service.UserManageService;

public class UserTask {
	@Autowired
	private UserManageService userService;
    
	
	public void pauseTask(){
		userService.pauseTask();
		userService.stopTask();
	}
}
