package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.SaleItemDao;
import edu.nju.desserthouse.model.SaleItem;

@Repository
public class SaleItemDaoImpl implements SaleItemDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(SaleItem item) {
		baseDao.save(item);
	}

	@Override
	public void update(SaleItem item) {
		baseDao.update(item);
	}

	@Override
	public SaleItem findById(String id) {
		String hqlString = "select s from SaleItem s where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<SaleItem> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<SaleItem> find(String colname, String value) {
		String hqlString = "select s from SaleItem s where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<SaleItem> list = query.list();
		return list;
	}

}
