package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.SaleItem;

public interface SaleItemDao {
	public void save(SaleItem item);
	
	public void update(SaleItem item);
	
	public SaleItem findById(String id);
	
	public List<SaleItem> find(String colname, String value);
}
