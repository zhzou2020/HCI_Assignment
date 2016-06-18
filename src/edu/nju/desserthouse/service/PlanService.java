package edu.nju.desserthouse.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.PlanItem;

public interface PlanService {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String addPlan(List<PlanItem> items, Plan plan);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updatePlanItem(PlanItem item);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updatePlan(Plan plan);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteItem(PlanItem item);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deletePlan(String planid);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String cancelPlan(String bid, Date date);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateStock(String pid);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updatePlanItems(String pid, List<PlanItem> items);
	
	public List<Plan> getValidPlan();
	
	public List<PlanItem> getItems(String planid);
	
	public Plan getPlan(String id);
	
	public List<Plan> getEPlan(String eid);
	
	public PlanItem getItem(String itemId);
	
	public boolean ifBranchPass(String bid);
}
