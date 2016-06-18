package edu.nju.desserthouse.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.desserthouse.dao.BaseDao;
import edu.nju.desserthouse.dao.PlanDao;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.tool.CalendarTool;

@Repository
public class PlanDaoImpl implements PlanDao{
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public void save(Plan plan) {
		baseDao.save(plan);
	}

	@Override
	public void update(Plan plan) {
		baseDao.update(plan);
	}

	@Override
	public Plan findById(String id) {
		String hqlString = "select p from Plan p where id" + " = " + id;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Plan> list = query.list();
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public List<Plan> find(String colname, String value) {
		String hqlString = "select p from Plan p where " + colname + " = " + value;
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		List<Plan> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		baseDao.delete(Plan.class, id);
	}

	@Override
	public List<Plan> findValidPlan() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date date = cal.getTime();
		String hqlString = "select p from Plan p where p.start_date > ? order by p.start_date desc";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setDate(0, date);
		List<Plan> list = query.list();
		return list;
	}

	@Override
	public void cancelPlan(String bid, Date date) {
		String hqlString = "select p from Plan p where p.start_date < ? and p.bid = " + bid + " and p.state = 0";
		System.out.println(hqlString);
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		date = cal.getTime();
		query.setDate(0, date);
		List<Plan> list = query.list();
		for(int i = 0; i != list.size(); i ++){
			list.get(i).setState(-2);
			this.update(list.get(i));
		}
	}

	@Override
	public boolean IfBranchPass(String bid) {
		Date start_date = CalendarTool.getNextWeekFirstDay();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start_date);
		cal.add(Calendar.DATE, -1);
		String hqlString = "select p from Plan p where p.start_date > ? and p.bid = " + bid + " and p.state = 1";
		Session session = baseDao.getSession();
		Query query = session.createQuery(hqlString);
		query.setDate(0, cal.getTime());
		List<Plan> list = query.list();
		if(list.size() == 0){
			return false;
		}
		return true;
	}

}
