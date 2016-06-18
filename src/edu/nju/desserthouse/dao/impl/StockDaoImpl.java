package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.StockDao;
import edu.nju.desserthouse.model.Stock;

@Repository
public class StockDaoImpl implements StockDao {
	@Autowired
	private BaseDao baseDao;

	@Override
	public void save(Stock stock) {
		baseDao.save(stock);
	}

	@Override
	public void update(Stock stock) {
		baseDao.update(stock);
	}

	@Override
	public Stock findById(String id) {
		String hqlString = "select s from Stock s where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Stock> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}


	@Override
	public List<Stock> find(String colname, String value) {
		String hqlString = "select s from Stock s where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Stock> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		baseDao.delete(Stock.class, id);
	}

	@Override
	public List<Stock> getStock(String bid, String date) {
		String hqlString = "select s from Stock s where bid = " + bid + " and date = ?";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setString(0, date);
		List<Stock> list = query.list();
		return list;
	}

	@Override
	public Stock getGidStock(String bid, String gid, String date) {
		String hqlString = "select s from Stock s where bid = " + bid + " and date = ? and gid = " + gid;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setString(0, date);
		List<Stock> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0); 
		}
	}

	@Override
	public List<Stock> getDayandAfter(String bid, String date) {
		String hqlString = "select s from Stock s where bid = " + bid + " and s.date >= " + date ;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Stock> list = query.list();
		return list;
	}

	@Override
	public List<Stock> getDayandAfterG(String bid, String gid, String date) {
		String hqlString = "select s from Stock s where bid = " + bid + " and s.date >= " + date + " and gid = " + gid;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Stock> list = query.list();
		return list;
	}
}
