package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Sale;

public interface SaleDao {
	public void save(Sale sale);
	
	public void update(Sale sale);
	
	public Sale findById(String id);
	
	public List<Sale> find(String colname, String value);
	
	public List<Sale> findString(String colname, String value);
	
	public List<Sale> findBeforeTime(String date, String state);
}
