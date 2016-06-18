package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.EmployeeDao;
import edu.nju.desserthouse.model.Employee;
import edu.nju.desserthouse.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	/**
	 * Default constructor
	 */
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee validate(String id, String password) {
		Employee employee = employeeDao.findById(id);
		if(employee == null){
			return null;
		} else{
			if(employee.getPassword().equals(password) && employee.getState() == 1){
				return employee;
			} else{
				return null;
			}
		}
	}

	@Override
	public String registerEmployee(Employee employee) {
		employeeDao.save(employee);
		return "职工创建成功～";
	}

	@Override
	public String assignBranch(String id, String bid) {
		Employee employee = employeeDao.findById(id);
		if(employee == null){
			return "不存在该职工！";
		} else{
			employee.setBid(Integer.valueOf(bid));
			employeeDao.update(employee);
			return "职工分配成功！";
		}
	}

	@Override
	public String assignPosition(String id, String position) {
		Employee employee = employeeDao.findById(id);
		if(employee == null){
			return "不存在该职工！";
		} else{
			employee.setBid(Integer.valueOf(position));
			employeeDao.update(employee);
			return "职工任命成功！";
		}
	}

	@Override
	public String deleteEmployee(String id) {
		Employee employee = employeeDao.findById(id);
		if(employee == null){
			return "不存在该职工!";
		} else{
			employee.setState(0);
			employeeDao.update(employee);
		}
		return "职工删除成功";
	}

	@Override
	public List<Employee> getValidEmployee() {
		return employeeDao.find("state", "1");
	}

	@Override
	public Employee findEmployeeById(String id) {
		return employeeDao.findById(id);
	}

	@Override
	public String assignPositionAndBranch(String id, String position, String bid) {
		Employee employee = employeeDao.findById(id);
		if(employee == null){
			return "不存在该员工!";
		} else{
			employee.setBid(Integer.valueOf(bid));
			employee.setAuthority(Integer.valueOf(position));
			employeeDao.update(employee);
			return "修改成功!";
		}
	}

}
