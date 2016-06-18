package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Employee;

public interface EmployeeDao {
	/**
	 * 
	 * @param employee
	 */
	public void save(Employee employee);
	
	/**
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public List<Employee> find(String column, String value);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Employee findById(String id);
	
	/**
	 * 
	 * @param employee
	 */
	public void update(Employee employee);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id);
}
