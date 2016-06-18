package edu.nju.desserthouse.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.PlanDao;
import edu.nju.desserthouse.dao.PlanItemDao;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.PlanItem;
import edu.nju.desserthouse.model.Stock;
import edu.nju.desserthouse.service.PlanService;
import edu.nju.desserthouse.service.StockService;

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	private PlanDao planDao;
	@Autowired
	private PlanItemDao planItemDao;
	@Autowired
	private StockService stockService;

	@Override
	public String updatePlanItem(PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteItem(PlanItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePlan(String planid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plan> getValidPlan() {
		List<Plan> list = planDao.findValidPlan();
		return list;
	}

	@Override
	public List<PlanItem> getItems(String planid) {
		List<PlanItem> list = planItemDao.find("pid", planid);
		return list;
	}

	@Override
	public Plan getPlan(String id) {
		return planDao.findById(id);
	}

	@Override
	public PlanItem getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addPlan(List<PlanItem> items, Plan plan) {
		planDao.save(plan);
		int pid = plan.getId();
		for(int i = 0 ; i != items.size(); i ++){
			PlanItem item = items.get(i);
			item.setPid(pid);
			planItemDao.save(item);
		}
		return "计划制定成功!";
	}

	@Override
	public String updatePlan(Plan plan) {
		planDao.update(plan);
		return "更新成功！";
	}

	@Override
	public String cancelPlan(String bid, Date date) {
		planDao.cancelPlan(bid, date);
		return null;
	}

	@Override
	public void updateStock(String pid) {
		Plan plan = planDao.findById(pid);
		Date start_date = plan.getStart_date();
		Date end_date = plan.getEnd_date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start_date);
		Calendar endcal = Calendar.getInstance();
		endcal.setTime(end_date);
		
		List<PlanItem> items = planItemDao.find("pid", pid);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		while(cal.get(Calendar.DAY_OF_WEEK) != endcal.get(Calendar.DAY_OF_WEEK)){
			
			for(int i = 0; i != items.size(); i ++){
				Stock stock = new Stock();
				stock.setBid(plan.getBid());
				stock.setGid(items.get(i).getGid());
				stock.setPrice(items.get(i).getPrice());
				stock.setDate(sdf.format(cal.getTime()));
				stock.setNumber(items.get(i).getNumber());
				stockService.addStock(stock);
			}
			cal.add(Calendar.DATE, 1);
		}
		for(int i = 0; i != items.size(); i ++){
			Stock stock = new Stock();
			stock.setBid(plan.getBid());
			stock.setGid(items.get(i).getGid());
			stock.setPrice(items.get(i).getPrice());
			stock.setDate(sdf.format(endcal.getTime()));
			stock.setNumber(items.get(i).getNumber());
			stockService.addStock(stock);
		}
	}

	@Override
	public boolean ifBranchPass(String bid) {
		return planDao.IfBranchPass(bid);
	}

	@Override
	public List<Plan> getEPlan(String eid) {
		return planDao.find("eid", eid);
	}

	@Override
	public void updatePlanItems(String pid, List<PlanItem> items) {
		Plan plan = planDao.findById(pid);
		plan.setState(0);
		planDao.update(plan);
		List<PlanItem> deleteItem = planItemDao.find("pid", pid);
		for(int i = 0; i != deleteItem.size(); i ++){
			System.out.println("deleting............." + deleteItem.get(i).getId());
			planItemDao.delete(Integer.toString(deleteItem.get(i).getId()));
		}
		for(int i = 0 ; i != items.size(); i ++){
			PlanItem item = items.get(i);
			item.setPid(Integer.valueOf(pid));
			planItemDao.save(item);
		}
	}

}
