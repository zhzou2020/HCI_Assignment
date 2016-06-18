package edu.nju.desserthouse.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Preorder;
import edu.nju.desserthouse.model.Sale;
import edu.nju.desserthouse.model.SaleItem;

public interface SaleService {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String addSale(List<SaleItem> items, Sale sale);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updateSale(Sale sale);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void autoComplete();
	
	public List<SaleItem> getItems(String saleid);
	
	public List<Sale> getSales(String colname, String value);
	
	public Sale getSale(String id);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updateCancelStock(String saleid);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String addPreorder(Preorder preorder);
	
	public List<Preorder> findPreorder(String colname, String value);
	
	public Preorder findPreorderById(String id);
	
	public List<Preorder> getAllPreorder();
	
	public List<Sale> getStringSale(String colname, String value);
}
