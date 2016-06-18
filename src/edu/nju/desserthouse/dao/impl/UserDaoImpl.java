package edu.nju.desserthouse.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	// protected Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BaseDao baseDao;

	/**
	 * 存储一个user对象
	 */
	public void save(User user) {
		try {
			baseDao.save(user);
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public List<User> find(String column, String value) {
		String hqlString = "select u from User u where " + column + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<User> list = query.list();
		return list;
	}
	
	public List<User> findString(String column, String value){
		String hqlString = "select u from User u where " + column + " = ?";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setString(0, value);
		List<User> list = query.list();
		return list;
	}

	/**
	 * 
	 */
	public void updateByUserid(User user) {
		baseDao.update(user);
	}

	@Override
	public User findById(String userid) {
		String hqlstmt = "select u from User u where id = " + userid;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlstmt);
		List<User> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<User> findBeforeTime(Date endTime, String state) {
		String hqlstmt = "select u from User u where u.validDate <= ? and u.state = " + state;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlstmt);
		query.setDate(0, endTime);
		List<User> list = query.list();
		return list;
	}

	@Override
	public List<User> findAllUser() {
		String hqlString = "select u from User u";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<User> list = query.list();
		return list;
	}
}
