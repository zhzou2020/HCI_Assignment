package edu.nju.desserthouse.dao;

import java.util.Date;
import java.util.List;

import edu.nju.desserthouse.model.Plan;

public interface PlanDao {
	/**
	 * 
	 * @param plan
	 */
	public void save(Plan plan);
	
	/**
	 * 
	 * @param plan
	 */
	public void update(Plan plan);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Plan findById(String id);
	
	/**
	 * 
	 * @param colname
	 * @param value
	 * @return
	 */
	public List<Plan> find(String colname, String value);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 
	 * @return
	 */
	public List<Plan> findValidPlan();
	
	/**
	 * 
	 * @param bid
	 * @param date
	 */
	public void cancelPlan(String bid, Date date);
	
	/**
	 * 
	 * @param bid
	 */
	public boolean IfBranchPass(String bid);
	
}
