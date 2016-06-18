package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.PlanItem;

public interface PlanItemDao {
	/**
	 * 
	 * @param item
	 */
	public void save(PlanItem item);
	
	/**
	 * 
	 * @param item
	 */
	public void update(PlanItem item);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public PlanItem findById(String id);
	
	/**
	 * 
	 * @param colname
	 * @param value
	 * @return
	 */
	public List<PlanItem> find(String colname, String value);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id);
}
