package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Stock;

public interface StockDao {
	public void save(Stock stock);
	
	public void update(Stock stock);
	
	public Stock findById(String id);
	
	public List<Stock> getStock(String bid, String date);
	
	public Stock getGidStock(String bid, String gid, String date);
	
	public List<Stock> getDayandAfter(String bid, String date);
	
	public List<Stock> getDayandAfterG(String bid, String gid, String date);
	
	public List<Stock> find(String colname, String value);
	
	public void delete(String id);
}
