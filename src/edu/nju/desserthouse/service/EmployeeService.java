package edu.nju.desserthouse.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Employee;

public interface EmployeeService {
	/**
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public Employee validate(String id, String password);
	
	/**
	 * 
	 * @param employee
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String registerEmployee(Employee employee);
	
	/**
	 * 
	 * @param id
	 * @param bid
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String assignBranch(String id, String bid);

	/**
	 * 
	 * @param id
	 * @param position
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String assignPosition(String id, String position);
	
	/**
	 * 
	 * @param id
	 * @param position
	 * @param bid
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String assignPositionAndBranch(String id, String position, String bid);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteEmployee(String id);
	
	/**
	 * 
	 * @return
	 */
	public List<Employee> getValidEmployee();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Employee findEmployeeById(String id);
}
