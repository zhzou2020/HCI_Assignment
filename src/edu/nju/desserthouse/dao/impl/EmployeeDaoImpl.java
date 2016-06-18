package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.EmployeeDao;
import edu.nju.desserthouse.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(Employee employee) {
		baseDao.save(employee);
	}

	@Override
	public List<Employee> find(String column, String value) {
		String hqlString = "select e from Employee e where " + column + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Employee> list = query.list();
		return list;
	}

	@Override
	public Employee findById(String id) {
		String hqlString = "select e from Employee e where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Employee> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public void update(Employee employee) {
		baseDao.update(employee);
	}

	@Override
	public void delete(String id) {
		baseDao.delete(Employee.class, id);
	}

}
