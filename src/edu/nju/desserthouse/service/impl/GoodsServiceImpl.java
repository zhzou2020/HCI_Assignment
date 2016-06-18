package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	/**
	 * Default constructor.
	 */
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public String addGoods(Goods goods) {
		goodsDao.save(goods);
		return "添加成功！";
	}

	@Override
	public String deleteGoods(String id) {
		Goods goods = goodsDao.findById(id);
		goods.setState(0);
		goodsDao.update(goods);
		return "删除成功！";
	}

	@Override
	public String updateGoods(Goods goods) {
		goodsDao.update(goods);
		return "信息更新成功！";
	}

	@Override
	public Goods findGoodsById(String id) {
		return goodsDao.findById(id);
	}

	@Override
	public List<Goods> getValidGoods() {
		List<Goods> list = goodsDao.find("state", "1");
		return list;
	}

}
