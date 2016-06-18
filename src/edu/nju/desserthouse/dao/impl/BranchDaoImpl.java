package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.BranchDao;
import edu.nju.desserthouse.model.Branch;

@Repository
public class BranchDaoImpl implements BranchDao{
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(Branch branch) {
		baseDao.save(branch);
	}

	@Override
	public void update(Branch branch) {
		baseDao.update(branch);
	}

	@Override
	public Branch findById(String id) {
		String hqlString = "select b from Branch b where id = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Branch> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public String getNameById(String id) {
		Branch branch = this.findById(id);
		if(branch == null){
			return null;
		} else{
			return branch.getName();
		}
	}

	@Override
	public List<Branch> find(String colname, String value) {
		String hqlString = "select b from Branch b where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Branch> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		baseDao.delete(Branch.class, id);
	}

}
