package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.GoodsDao;
import edu.nju.desserthouse.model.Goods;

@Repository
public class GoodsDaoImpl implements GoodsDao{
	@Autowired
	private BaseDao baseDao;
	

	@Override
	public List<Goods> find(String colname, String value) {
		String hqlString = "select g from Goods g where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Goods> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseDao.delete(Goods.class, id);
	}

	@Override
	public void save(Goods goods) {
		baseDao.save(goods);
	}

	@Override
	public void update(Goods goods) {
		baseDao.update(goods);
	}

	@Override
	public Goods findById(String id) {
		String hqlString = "select g from Goods g where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Goods> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

}
