package edu.nju.desserthouse.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.model.Employee;
import edu.nju.desserthouse.service.BranchService;
import edu.nju.desserthouse.service.EmployeeService;

@Controller
public class EmployeeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6364884296051912599L;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BranchService branchService;
	
	private Employee employee;
	private String result;
	
	public String Login(){
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		employee = employeeService.validate(id, password);
		if(employee == null){
			return INPUT;
		} else{
			session.put("employee", employee);
			session.remove("user");
			return SUCCESS;
		}
	}
	
	public String Logout(){
		session.remove("employee");
		return SUCCESS;
	}
	
	public String addEmployee(){
		result = null;
		String name = request.getParameter("name");
		String passwordOne = request.getParameter("passwordOne");
		String passwordTwo = request.getParameter("passwordTwo");
		if(!passwordOne.equals(passwordTwo)){
			result = "密码不匹配！";
		} else{
			String bid = request.getParameter("bid");
			String authority = request.getParameter("authority");
			employee = new Employee();
			employee.setName(name);
			employee.setPassword(passwordOne);
			employee.setBid(Integer.valueOf(bid));
			employee.setAuthority(Integer.valueOf(authority));
			employee.setState(1);
			result = employeeService.registerEmployee(employee);
		}
		return "result";
	}
	
	public String employeeList(){
		List<Employee> list = employeeService.getValidEmployee();
		List<Branch> branchlist = branchService.getValidBranch();
		session.put("branchlist", branchlist);
		session.put("employeeList", list);
		return SUCCESS;
	}
	
	public String employeeInfo(){
		String id = request.getParameter("id");
		Employee employee = employeeService.findEmployeeById(id);
		session.put("thisEmployee", employee);
		return SUCCESS;
	}
	
	public String updateEmployee(){
		result = null;
		String id = request.getParameter("id");
		String bid = request.getParameter("bid");
		String authority = request.getParameter("authority");
		result = employeeService.assignPositionAndBranch(id, authority, bid);
		return "result";
	}
	
	public String deleteEmployee(){
		result = null;
		String id = request.getParameter("id");
		result = employeeService.deleteEmployee(id);
		return "result";
	}
	
	public String getResult(){
		return this.result;
	}
}
