package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Goods;

public interface GoodsDao {
	/**
	 * 
	 */
	public void save(Goods goods);
	
	/**
	 * 
	 */
	public void update(Goods goods);
	
	/**
	 * 
	 * @return
	 */
	public Goods findById(String id);
	
	/**
	 * 
	 * @param colname
	 * @param value
	 * @return
	 */
	public List<Goods> find(String colname, String value);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id);
}
