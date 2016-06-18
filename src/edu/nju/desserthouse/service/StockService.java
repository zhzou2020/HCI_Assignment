package edu.nju.desserthouse.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Stock;


public interface StockService {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addStock(Stock stock);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateStock(Stock stock);
	
	public Stock getStock(String id);
	
	public Stock getBidGidDateStock(String bid, String date, String gid);
	
	public List<Stock> getBidDateStock(String bid, String date);
	
	public List<Stock> getTodayAndAfterStock(String bid, String date);
	
	public List<Stock> getTodayAndAfterStockG(String bid, String gid, String date);
}
