package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.PlanItemDao;
import edu.nju.desserthouse.model.PlanItem;

@Repository
public class PlanItemDaoImpl implements PlanItemDao{
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(PlanItem item) {
		baseDao.save(item);
	}

	@Override
	public void update(PlanItem item) {
		baseDao.save(item);
	}

	@Override
	public PlanItem findById(String id) {
		String hqlString = "select p from PlanItem p where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<PlanItem> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<PlanItem> find(String colname, String value) {
		String hqlString = "select p from PlanItem p where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<PlanItem> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		String hqlString = "delete PlanItem p where p.id = ?";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setString(0, id);
		query.executeUpdate();
	}
	
}
