package edu.nju.desserthouse.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.service.BranchService;

@Controller
public class BranchAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6453698934192311597L;

	@Autowired
	private BranchService branchService;
	
	private Branch branch;
	
	private String result;
	
	public String branchList(){
		List<Branch> list = branchService.getValidBranch();
		session.put("branchlist", list);
		return SUCCESS;
	}
	
	public String branchInformation(){
		result = null;
		String bid = request.getParameter("bid");
		Branch thisBranch = branchService.findBranchById(bid);
		String name = thisBranch.getName();
		String address = thisBranch.getAddress();
		String info = thisBranch.getInfo();
		result = "{\"id\":\"" + bid + "\", \"name\":\"" + name + "\", \"address\":\"" + address + "\", \"info\":\"" + info + "\"}";
		return "result";
	}
	
	public String updateBranch(){
		result = null;
		String bid = request.getParameter("bid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		Branch branch = branchService.findBranchById(bid);
		if(branch == null){
			result =  "没有此店面";
		} else{
			branch.setName(name);
			branch.setAddress(address);
			branch.setInfo(info);
			result = branchService.updateBranch(branch);
		}
		return "result";
	}
	
	public String deleteBranch(){
		result = null;
		String bid = request.getParameter("bid");
		result = branchService.deleteBranch(bid);
		return "result";
	}
	
	public String addBranch(){
		result = null;
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		branch = new Branch();
		branch.setName(name);
		branch.setAddress(address);
		branch.setInfo(info);
		branch.setState(1);
		result = branchService.addBranch(branch);
		return "result";
	}
	
	public String BranchAllInfo(){
		result = null;
		String bid = request.getParameter("id");
		Branch branch = branchService.findBranchById(bid);
		if(branch == null){
			
		} else{
			result = branch.json();
		}
		return "result";
	}
	
	public String getResult(){
		return result;
	}

}
