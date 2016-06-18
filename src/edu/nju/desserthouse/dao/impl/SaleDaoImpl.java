package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.SaleDao;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.Sale;

@Repository
public class SaleDaoImpl implements SaleDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(Sale sale) {
		baseDao.save(sale);
	}

	@Override
	public void update(Sale sale) {
		baseDao.update(sale);
	}

	@Override
	public Sale findById(String id) {
		String hqlString = "select s from Sale s where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Sale> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<Sale> find(String colname, String value) {
		String hqlString = "select s from Sale s where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Sale> list = query.list();
		return list;
	}

	@Override
	public List<Sale> findBeforeTime(String date, String state) {
		String hqlstmt = "select s from Sale s where s.date <= ? and s.state = " + state;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlstmt);
		query.setString(0, date);
		List<Sale> list = query.list();
		return list;
	}

	@Override
	public List<Sale> findString(String colname, String value) {
		String hqlString = "select s from Sale s where " + colname + " = ?";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setString(0, value);
		List<Sale> list = query.list();
		return list;
	}


}
