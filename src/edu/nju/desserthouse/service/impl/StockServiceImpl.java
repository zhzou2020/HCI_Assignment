package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.StockDao;
import edu.nju.desserthouse.model.Stock;
import edu.nju.desserthouse.service.StockService;

@Service
public class StockServiceImpl implements StockService{
	@Autowired
	private StockDao stockDao;
	
	@Override
	public boolean addStock(Stock stock) {
		stockDao.save(stock);
		return true;
	}

	@Override
	public boolean updateStock(Stock stock) {
		stockDao.update(stock);
		return true;
	}

	@Override
	public Stock getStock(String id) {
		return stockDao.findById(id);
	}

	@Override
	public Stock getBidGidDateStock(String bid, String date, String gid) {
		return stockDao.getGidStock(bid, gid, date);
	}

	@Override
	public List<Stock> getBidDateStock(String bid, String date) {
		return stockDao.getStock(bid, date);
	}

	@Override
	public List<Stock> getTodayAndAfterStock(String bid, String date) {
		return stockDao.getDayandAfter(bid, date);
	}
	
	public List<Stock> getTodayAndAfterStockG(String bid, String gid, String date){
		return stockDao.getDayandAfterG(bid, gid, date);
	}
}
