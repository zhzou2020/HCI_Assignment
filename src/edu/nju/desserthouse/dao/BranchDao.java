package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Branch;

public interface BranchDao {
	/**
	 * 
	 * @param branch
	 */
	public void save(Branch branch);
	
	/**
	 * 
	 * @param branch
	 */
	public void update(Branch branch);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Branch findById(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getNameById(String id);
	
	/**
	 * 
	 * @param colname
	 * @param value
	 * @return
	 */
	public List<Branch> find(String colname, String value);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id);
}
