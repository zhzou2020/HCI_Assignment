package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.PreorderDao;
import edu.nju.desserthouse.dao.SaleDao;
import edu.nju.desserthouse.dao.SaleItemDao;
import edu.nju.desserthouse.model.Preorder;
import edu.nju.desserthouse.model.Sale;
import edu.nju.desserthouse.model.SaleItem;
import edu.nju.desserthouse.model.Stock;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.service.StockService;

@Service
public class SaleServiceImpl implements SaleService{
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private SaleItemDao saleitemDao;
	@Autowired
	private PreorderDao preorderDao;
	@Autowired
	private StockService stockService;

	@Override
	public String addSale(List<SaleItem> items, Sale sale) {
		saleDao.save(sale);
		int sid = sale.getId();
		String date = sale.getDate();
		int bid = sale.getBid();
		
		for(int i = 0; i != items.size(); i ++){
			items.get(i).setSid(sid);
			saleitemDao.save(items.get(i));
			//更新库存
			Stock stock = stockService.getBidGidDateStock(Integer.toString(bid), date, Integer.toString(items.get(i).getGid()));
			stock.setNumber(stock.getNumber() - items.get(i).getNumber());
			stockService.updateStock(stock);
		}
		
		return "销售成功！";
	}

	@Override
	public String updateSale(Sale sale) {
		saleDao.update(sale);
		return "更新成功！";
	}

	@Override
	public void autoComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SaleItem> getItems(String saleid) {
		return saleitemDao.find("sid", saleid);
	}

	@Override
	public List<Sale> getSales(String colname, String value) {
		return saleDao.find(colname, value);
	}

	@Override
	public Sale getSale(String id) {
		return saleDao.findById(id);
	}

	@Override
	public String updateCancelStock(String saleid) {
		List<SaleItem> items = saleitemDao.find("sid", saleid);
		Sale sale = saleDao.findById(saleid);
		String date = sale.getDate();
		int gid = 0;
		int bid = sale.getBid();
		
		for(int i = 0; i != items.size(); i ++){
			gid = items.get(i).getGid();
			System.out.println(gid);
			Stock stock = stockService.getBidGidDateStock(Integer.toString(bid), date, Integer.toString(gid));
			stock.setNumber(stock.getNumber() + items.get(i).getNumber());
			stockService.updateStock(stock);
		}
		
		return "库存更新成功！";
	}

	@Override
	public String addPreorder(Preorder preorder) {
		preorderDao.save(preorder);
		return "添加成功！";
	}

	@Override
	public List<Preorder> findPreorder(String colname, String value) {
		return preorderDao.find(colname, value);
	}

	@Override
	public Preorder findPreorderById(String id) {
		return preorderDao.findById(id);
	}

	@Override
	public List<Preorder> getAllPreorder() {
		return preorderDao.all();
	}

	@Override
	public List<Sale> getStringSale(String colname, String value) {
		return saleDao.findString(colname, value);
	}

}
