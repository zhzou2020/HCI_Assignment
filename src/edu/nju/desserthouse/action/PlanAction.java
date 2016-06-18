package edu.nju.desserthouse.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.model.Employee;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.PlanItem;
import edu.nju.desserthouse.service.BranchService;
import edu.nju.desserthouse.service.GoodsService;
import edu.nju.desserthouse.service.PlanService;
import edu.nju.desserthouse.tool.CalendarTool;

@Controller
public class PlanAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5590616187314387065L;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private PlanService planService;
	
	private String result;
	
	public String makePlan(){
		if(session.get("employee") == null){
			return "fail";
		}
		List<Goods> list = goodsService.getValidGoods();
		List<Branch> branchList = branchService.getValidBranch();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String start_date = sdf.format(CalendarTool.getNextWeekFirstDay());
		String end_date = sdf.format(CalendarTool.getNextWeekLastDay());
		session.put("goodslist", list);
		session.put("branchlist", branchList);
		session.put("plan_start_date", start_date);
		session.put("plan_end_date", end_date);
		return SUCCESS;
	}
	
	public String planList(){
		List<Plan> list = null;
		if(session.get("employee") == null){
			return "fail";
		} else if(((Employee)session.get("employee")).getAuthority() == 2){
			int eid = ((Employee)session.get("employee")).getId();
			list = planService.getEPlan(Integer.toString(eid));
		} else{
			list = planService.getValidPlan();
		}
		session.put("planlist", list);
		return SUCCESS;
	}
	
	public String addItem(){
		result = null;
		int gid = Integer.valueOf(request.getParameter("gid"));
		String price = request.getParameter("price");
		String number = request.getParameter("number");
		
		try{
			Double.parseDouble(price);
		} catch(NumberFormatException ex){
			this.result = "价格应为数字！";
			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
			return "result";
		}
		
		try{
			Integer.parseInt(number);
		} catch(NumberFormatException ex){
			this.result = "数量应为数字！";
			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
			return "result";
		}
		
		if(session.get("planItemList") == null){
			List<PlanItem> items = new ArrayList<PlanItem>();
			session.put("planItemList", items);
		}
		List<PlanItem> list = (List<PlanItem>) session.get("planItemList");
		for(int i = 0; i != list.size(); i ++){
			if(gid == list.get(i).getGid()){
				result = "此商品已经被添加，不能重复添加!";
				result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
				return "result";
			}
		}
		
		PlanItem item = new PlanItem();
		item.setGid(Integer.valueOf(gid));
		item.setPrice(Double.valueOf(price));
		item.setNumber(Integer.valueOf(number));
		list.add(item);
		result = "添加成功！";
		result = "{\"message\":\"" + result + "\", \"result\":\"true\"}"; 
		return "result";
	}
	
//	public String addTempItem(){
//		result = null;
//		int gid = Integer.valueOf(request.getParameter("gid"));
//		String price = request.getParameter("price");
//		String number = request.getParameter("number");
//		
//		try{
//			Double.parseDouble(price);
//		} catch(NumberFormatException ex){
//			this.result = "价格应为数字！";
//			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
//			return "result";
//		}
//		
//		try{
//			Integer.parseInt(number);
//		} catch(NumberFormatException ex){
//			this.result = "数量应为数字！";
//			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
//			return "result";
//		}
//		
//		if(session.get("planItemTempList") == null){
//			List<PlanItem> items = new ArrayList<PlanItem>();
//			session.put("planItemTempList", items);
//		}
//		List<PlanItem> list = (List<PlanItem>) session.get("planItemList");
//		List<PlanItem> temp = (List<PlanItem>) session.get("planItemTempList");
//		for(int i = 0; i != list.size(); i ++){
//			if(gid == list.get(i).getGid()){
//				result = "此商品已经被添加，不能重复添加!";
//				result = "{\"message\":\"" + result + "\", \"result\":\"false\"}"; 
//				return "result";
//			}
//		}
//		
//		PlanItem item = new PlanItem();
//		item.setGid(Integer.valueOf(gid));
//		item.setPrice(Double.valueOf(price));
//		item.setNumber(Integer.valueOf(number));
//		list.add(item);
//		temp.add(item);
//		result = "添加成功！";
//		result = "{\"message\":\"" + result + "\", \"result\":\"true\"}"; 
//		return "result";
//	}
	
	public String deleteItem(){
		result = null;
		String gid = request.getParameter("id");
		List<PlanItem> list = (List<PlanItem>) session.get("planItemList");
		for(int i = 0; i != list.size(); i ++){
			if(list.get(i).getGid() == Integer.valueOf(gid)){
				list.remove(i);
				result = "删除成功！";
				return "result";
			}
		}
		result = "删除失败！";
		return "result";
	}
	
//	public String deleteTempItem(){
//		result = null;
//		boolean isRemove = false;
//		String gid = request.getParameter("id");
//		List<PlanItem> list = (List<PlanItem>) session.get("planItemList");
//		List<PlanItem> temp = (List<PlanItem>) session.get("planItemTempList");
//		for(int i = 0; i != list.size(); i ++){
//			if(list.get(i).getGid() == Integer.valueOf(gid)){
//				list.remove(i);
//				isRemove = true;
//				result = "删除成功！";
//			}
//		}
//		for(int j = 0; j != temp.size(); j ++){
//			if(list.get(j).getGid() == Integer.valueOf(gid)){
//				temp.remove(j);
//			}
//		}
//		if(!isRemove){
//			result = "删除失败！";
//		}
//		return "result";
//	}
//	
//	public String deleteItemFromDB(){
//		result = null;
//		String id = request.getParameter("id");
//		PlanItem item = planService.getItem(id);
//		planService.deleteItem(item);
//		List<PlanItem> list = (List<PlanItem>) session.get("planItemList");
//		for(int i = 0; i != list.size(); i ++){
//			if(list.get(i).getId() == Integer.valueOf(id)){
//				list.remove(i);
//				result = "删除成功！";
//				return "result";
//			}
//		}
//		result = "删除成功！";
//		return "reuslt";
//	}
//	
	public String createPlan(){
		result = null;
		
		List<PlanItem> items = (List<PlanItem>) session.get("planItemList");
		if(items == null || items.size() == 0){
			result = "计划不能为空！";
			return "result";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date start_date = CalendarTool.getNextWeekFirstDay();
		Date end_date = CalendarTool.getNextWeekLastDay();
		
		
		Plan thisPlan = new Plan();
		thisPlan.setBid(Integer.valueOf(request.getParameter("bid")));
		thisPlan.setEid(((Employee)session.get("employee")).getId());
		thisPlan.setStart_date(start_date);
		thisPlan.setEnd_date(end_date);
		thisPlan.setState(0);
		if(planService.ifBranchPass(Integer.toString(thisPlan.getBid()))){
			result = "此分店下周已有审批通过的计划，您无需制定计划!";
			session.remove("planItemList");
			return "result";
		}
		
		result = planService.addPlan(items, thisPlan);
		session.remove("planItemList");
		return "result";
	}
	
	public String planInfo(){
		result = null;
		String pid = request.getParameter("id");
		Plan plan = planService.getPlan(pid);
		result = "{\"plan\":" + plan.json() + ", \"items\":[";
		List<PlanItem> items = planService.getItems(pid);
		for(int i = 0; i != items.size() - 1; i ++){
			result += items.get(i).json() + ", ";
		}
		result += items.get(items.size() - 1).json() + "]}";
		return "result";
	}
	
	public String passPlan(){
		result = null;
		String pid = request.getParameter("id");
		Plan plan = planService.getPlan(pid);
		if(plan == null){
			result = "不存在此计划！";
		} else if(plan.getState() != 0){
			result = "只有未审批的计划可以审批，此计划无法审批！";
		} else{
			plan.setState(1);
			planService.updatePlan(plan);
			planService.cancelPlan(Integer.toString(plan.getBid()), plan.getStart_date());
			planService.updateStock(pid);
			result = "审批通过!"; 
		}
		return "result";
	}
	
	public String rejectPlan(){
		result = null;
		String pid = request.getParameter("id");
		Plan plan = planService.getPlan(pid);
		if(plan == null){
			result = "不存在此计划！";
		} else if(plan.getState() != 0){
			result = "只有未审批的计划可以审批，此计划无法审批！";
		} else{
			plan.setState(-1);
			planService.updatePlan(plan);
			result = "拒绝计划成功！";
		}
		return "result";
	}
	
	public String modifyPlan(){
		String pid = request.getParameter("id");
		Plan plan = planService.getPlan(pid);
		session.put("thisplan", plan);
		List<PlanItem> items = planService.getItems(pid);
		session.put("planItemList", items);
		session.put("branchName", branchService.getBranchName(Integer.toString(plan.getBid())));
		return SUCCESS;
	}
	
	public String updatePlan(){
		result = null;
		List<PlanItem> items = (List<PlanItem>) session.get("planItemList");
		if(items == null || items.size() == 0){
			result = "计划不能为空！";
			return "result";
		}
		
		String id = request.getParameter("id");
		
		Plan thisPlan = planService.getPlan(id);
		
		if(planService.ifBranchPass(Integer.toString(thisPlan.getBid()))){
			result = "此分店下周已有审批通过的计划，您无需制定计划!";
			session.remove("planItemList");
			return "result";
		}
		
//		result = planService.addPlan(items, thisPlan);
		planService.updatePlanItems(id, items);
		result = "修改成功！";
		session.remove("planItemList");
		return "result";
	}
	
	public String getResult(){
		return result;
	}
}
