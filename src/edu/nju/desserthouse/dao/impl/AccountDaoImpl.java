package edu.nju.desserthouse.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.AccountDao;
import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao{
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Account find(String account) {
		String hql = "select a from Account a where id = " + account;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hql);
		List<Account> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public boolean minusBalance(String account, double amount) {
		String hql = "update Account a set a.balance = a.balance - " + amount + " where a.id = " + account;
		System.out.println(hql);
		Session session = baseDao.getSession();
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		if(result == 0){
			return false;
		} else{
			return true;
		}
	}
}