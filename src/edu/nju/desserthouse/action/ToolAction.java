package edu.nju.desserthouse.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.service.BranchService;

@Controller
public class ToolAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7755816516637965015L;

	@Autowired
	private BranchService branchService;

	private String result;

	public String getBranchName() {
		result = null;
		String bid = request.getParameter("id");
		String name = branchService.getBranchName(bid);
		result = "{\"name\":\"" + name + "\"}";
		return "result";
	}
	

	public String getResult(){
		return result;
	}
}
