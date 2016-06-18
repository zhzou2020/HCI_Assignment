package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.PreorderDao;
import edu.nju.desserthouse.model.Preorder;

@Repository
public class PreorderDaoImpl implements PreorderDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(Preorder order) {
		baseDao.save(order);
	}

	@Override
	public void update(Preorder order) {
		baseDao.update(order);
	}

	@Override
	public Preorder findById(String id) {
		String hqlString = "select p from Preorder p where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Preorder> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<Preorder> find(String colname, String value) {
		String hqlString = "select p from Preorder p where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Preorder> list = query.list();
		return list;
	}

	@Override
	public List<Preorder> all() {
		String hqlString = "select p from Preorder p";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Preorder> list = query.list();
		return list;
	}

}
