package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.PayRecordDao;
import edu.nju.desserthouse.model.PayRecord;

@Repository
public class PayRecordDaoImpl implements PayRecordDao{
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(PayRecord record) {
		baseDao.save(record);
	}

	@Override
	public void update(PayRecord record) {
		baseDao.update(record);
	}

	@Override
	public PayRecord findById(String id) {
		String hqlString = "select p from PayRecord p where id = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<PayRecord> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<PayRecord> find(String colname, String value) {
		String hqlString = "select p from PayRecord p where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<PayRecord> list = query.list();
		return list;
	}

	@Override
	public List<PayRecord> findUserRecord(String mid) {
		String hqlString = "select p from PayRecord p where mid" + " = " + mid + "order by p.date desc";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<PayRecord> list = query.list();
		return list;
	}

}
