package edu.nju.desserthouse.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Goods;

public interface GoodsService {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String addGoods(Goods goods);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteGoods(String id);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updateGoods(Goods goods);
	
	public Goods findGoodsById(String id);
	
	public List<Goods> getValidGoods();
}
