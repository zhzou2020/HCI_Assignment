package edu.nju.desserthouse.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.model.Employee;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Preorder;
import edu.nju.desserthouse.model.Sale;
import edu.nju.desserthouse.model.SaleItem;
import edu.nju.desserthouse.model.Stock;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.BranchService;
import edu.nju.desserthouse.service.GoodsService;
import edu.nju.desserthouse.service.SaleService;
import edu.nju.desserthouse.service.StockService;
import edu.nju.desserthouse.service.UserManageService;
import edu.nju.desserthouse.tool.CalendarTool;

@Controller
public class SaleAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2089763666695227703L;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private StockService stockService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private UserManageService userService;
	@Autowired
	private BranchService branchService;

	private String result;

	public String saleStart() {
		Employee employee = (Employee) session.get("employee");
		if (employee == null) {
			return INPUT;
		}
		String bid = Integer.toString(employee.getBid());
		session.put("preorderbid", bid);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<Stock> list = stockService.getBidDateStock(bid,
				sdf.format(cal.getTime()));
		session.put("preorderdate", sdf.format(cal.getTime()));
		session.put("stocklist", list);
		if (list.size() != 0) {
			session.put("stocknumber", list.get(0).getNumber());
			session.put(
					"stockname",
					goodsService.findGoodsById(
							Integer.toString(list.get(0).getGid())).getName());
		}

		return SUCCESS;
	}

	public String addSaleItem() {
		result = null;
		if (session.get("saleitemlist") == null) {
			List<SaleItem> list = new ArrayList<SaleItem>();
			double amount = 0.0;
			session.put("saleitemlist", list);
			session.put("amount", amount);
		}
		String bid = Integer.toString(((Employee) session.get("employee"))
				.getBid());
		List<SaleItem> salelist = (List<SaleItem>) session.get("saleitemlist");
		int gid = Integer.valueOf(request.getParameter("gid"));
		int number = Integer.valueOf(request.getParameter("number"));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(cal.getTime());

		for (int i = 0; i != salelist.size(); i++) {
			if (salelist.get(i).getGid() == gid) {
				result = "此商品已在销售列表中！";
				result = "{\"message\":\"" + result
						+ "\", \"result\":\"false\"}";
				return "result";
			}
		}

		Stock stock = stockService.getBidGidDateStock(bid, today,
				Integer.toString(gid));

		if (stock.getNumber() < number) {
			result = "库存不足，无法添加！";
			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}";
		} else {
			SaleItem item = new SaleItem();
			item.setGid(gid);
			item.setNumber(number);
			item.setItem_price(stock.getPrice());
			salelist.add(item);
			double amount = 0.0;
			if (session.get("amount") == null) {
				session.put("amount", amount);
			}
			amount = (double) session.get("amount");
			amount = amount + item.getItem_price() * item.getNumber();
			session.put("amount", amount);

			result = "添加成功！";
			result = "{\"message\":\"" + result
					+ "\", \"result\":\"true\", \"price\":\""
					+ item.getItem_price() + "\", \"amount\":\"" + amount
					+ "\"}";
		}

		return "result";
	}

	public String deleteSaleItem() {
		result = null;

		String gid = request.getParameter("id");
		int gidint = Integer.valueOf(gid);

		List<SaleItem> list = (List<SaleItem>) session.get("saleitemlist");

		for (int i = 0; i != list.size(); i++) {
			if (list.get(i).getGid() == gidint) {
				System.out.println(session.get("amount") + " "
						+ list.get(i).getItem_price() + " "
						+ list.get(i).getNumber());
				double amount = (double) session.get("amount")
						- list.get(i).getItem_price() * list.get(i).getNumber();
				session.put("amount", amount);
				list.remove(i);
				result = "删除成功！";
				result = "{\"message\":\"" + result + "\", \"amount\":\""
						+ amount + "\"}";
				break;
			}
		}
		return "result";
	}

	public String cashSale() {
		result = null;
		double amount = (double) session.get("amount");
		Employee employee = ((Employee) session.get("employee"));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(cal.getTime());

		Sale sale = new Sale();
		sale.setAmount(amount);
		sale.setPay_way(0);
		sale.setState(1);
		sale.setBid(employee.getBid());
		sale.setSalesman_id(employee.getId());
		sale.setDate(today);

		List<SaleItem> items = (List<SaleItem>) session.get("saleitemlist");

		result = saleService.addSale(items, sale);

		session.remove("saleitemlist");
		session.remove("amount");

		return "result";
	}

	public String cardSale() {
		result = null;
		double amount = Double.valueOf(request.getParameter("amount"));
		String mid = request.getParameter("mid");
		User user = userService.getUserById(mid);
		if (user == null) {
			result = "会员不存在！";
			result = "{\"message\":\"" + result + "\",\"success\":\"fail\"}";
		} else if (user.getBalance() < amount) {
			result = "会员余额不足！";
			result = "{\"message\":\"" + result + "\",\"success\":\"fail\"}";
		} else {
			Employee employee = ((Employee) session.get("employee"));
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(cal.getTime());

			Sale sale = new Sale();
			sale.setAmount(amount);
			sale.setPay_way(1);
			sale.setState(1);
			sale.setBid(employee.getBid());
			sale.setSalesman_id(employee.getId());
			sale.setDate(today);
			sale.setMid(Integer.valueOf(mid));

			List<SaleItem> items = (List<SaleItem>) session.get("saleitemlist");

			result = saleService.addSale(items, sale);
			result = "{\"message\":\"" + result + "\",\"success\":\"ok\"}";
			user.setBalance(user.getBalance() - amount);
			user.setPoint(user.getPoint() + (int)amount/10);
			userService.changeProfile(user);

			session.remove("saleitemlist");
			session.remove("amount");
		}

		return "result";
	}

	public String saleList() {
		if (session.get("user") != null) {
			User user = (User) session.get("user");
			int mid = user.getId();
			List<Sale> salelist = saleService.getSales("mid",
					Integer.toString(mid));
			session.put("salelist", salelist);
			return SUCCESS;
		} else if (session.get("employee") != null) {
			Employee employee = (Employee) session.get("employee");
			if (employee.getAuthority() == 3) {
				int eid = employee.getId();
				List<Sale> salelist = saleService.getSales("salesman_id",
						Integer.toString(eid));
				session.put("salelist", salelist);
			} else {

			}
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String saleInfo() {
		result = null;
		String sid = request.getParameter("id");
		Sale sale = saleService.getSale(sid);

		List<SaleItem> items = saleService.getItems(sid);

		if (items.size() == 0) {
			result = "{\"sale\":" + sale.json() + "}";
		} else {
			result = "{\"sale\":" + sale.json() + ", \"items\":[";
			for (int i = 0; i != items.size() - 1; i++) {
				result += items.get(i).json() + ",";
			}
			result += items.get(items.size() - 1).json() + "]}";
		}

		return "result";
	}

	public String cancelSale() {
		result = null;
		String sid = request.getParameter("id");

		Sale sale = saleService.getSale(sid);

		if (sale == null) {
			result = "不存在该订单！";
		} else if (sale.getState() != 0) {
			result = "只有未完成的订单才能退订哦";
		} else {
			sale.setState(-1);
			saleService.updateSale(sale);

			// 加上库存
			saleService.updateCancelStock(sid);

			// 退款
			User user = userService
					.getUserById(Integer.toString(sale.getMid()));
			user.setBalance(user.getBalance() + sale.getAmount());
			user.setPoint(user.getPoint() + (int)sale.getAmount()/10);
			userService.changeProfile(user);

			result = "订单取消成功，费用" + sale.getAmount() + "元已退回您的账户，您的账户余额为"
					+ user.getBalance() + "元！";
		}

		return "result";
	}

	public String branchDateChoose() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

		Calendar end_date = Calendar.getInstance();
		Date last_day = CalendarTool.getNextWeekLastDay();
		end_date.setTime(last_day);

		List<String> date_set = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while (cal.compareTo(end_date) <= 0) {
			date_set.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}

		session.put("dateset", date_set);

		List<Branch> branchlist = branchService.getValidBranch();

		session.put("branchlist", branchlist);

		return SUCCESS;
	}

	public String preorderStart() {
		String bid = request.getParameter("b_id");
		String date = request.getParameter("date");
		session.put("preorderbid", bid);
		session.put("preorderdate", date);

		List<Stock> list = stockService.getBidDateStock(bid, date);
		session.put("stocklist", list);
		if (list.size() != 0) {
			session.put("stocknumber", list.get(0).getNumber());
			session.put(
					"stockname",
					goodsService.findGoodsById(
							Integer.toString(list.get(0).getGid())).getName());
		}

		return SUCCESS;
	}

	public String addSaleItem_pre() {
		result = null;
		if (session.get("saleitemlist") == null) {
			List<SaleItem> list = new ArrayList<SaleItem>();
			double amount = 0.0;
			session.put("saleitemlist", list);
			session.put("amount", amount);
		}
		String bid = (String) session.get("preorderbid");
		List<SaleItem> salelist = (List<SaleItem>) session.get("saleitemlist");
		int gid = Integer.valueOf(request.getParameter("gid"));
		int number = Integer.valueOf(request.getParameter("number"));

		String today = (String) session.get("preorderdate");

		for (int i = 0; i != salelist.size(); i++) {
			if (salelist.get(i).getGid() == gid) {
				result = "此商品已在销售列表中！";
				result = "{\"message\":\"" + result
						+ "\", \"result\":\"false\"}";
				return "result";
			}
		}

		Stock stock = stockService.getBidGidDateStock(bid, today,
				Integer.toString(gid));

		if (stock.getNumber() < number) {
			result = "库存不足，无法添加！";
			result = "{\"message\":\"" + result + "\", \"result\":\"false\"}";
		} else {
			SaleItem item = new SaleItem();
			item.setGid(gid);
			item.setNumber(number);
			item.setItem_price(stock.getPrice());
			salelist.add(item);
			double amount = 0.0;
			if (session.get("amount") == null) {
				session.put("amount", amount);
			}
			amount = (double) session.get("amount");
			amount = amount + item.getItem_price() * item.getNumber();
			session.put("amount", amount);

			result = "添加成功！";
			result = "{\"message\":\"" + result
					+ "\", \"result\":\"true\", \"price\":\""
					+ item.getItem_price() + "\", \"amount\":\"" + amount
					+ "\"}";
		}

		return "result";
	}

	public String preorderSale() {
		result = null;
		double amount = Double.valueOf(request.getParameter("amount"));
		User user = (User) session.get("user");
		if (user == null) {
			result = "会员不存在！";
			result = "{\"message\":\"" + result + "\",\"success\":\"fail\"}";
		} else if (user.getBalance() < amount) {
			result = "会员余额不足！";
			result = "{\"message\":\"" + result + "\",\"success\":\"fail\"}";
		} else {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(cal.getTime());
			String order_day = (String) session.get("preorderdate");
			String bid = (String) session.get("preorderbid");
			System.out.println(bid);
			int mid = ((User) session.get("user")).getId();

			Sale sale = new Sale();
			sale.setAmount(amount);
			sale.setPay_way(1);
			if (today.equals(order_day)) {
				sale.setState(1);
			} else {
				sale.setState(0);
			}
			sale.setBid(Integer.valueOf(bid));
			sale.setMid(mid);
			sale.setDate(order_day);

			Preorder preorder = new Preorder();
			preorder.setDate(today);
			preorder.setMid(mid);

			List<SaleItem> items = (List<SaleItem>) session.get("saleitemlist");

			result = saleService.addSale(items, sale);
			result = "{\"message\":\"" + result + "\",\"success\":\"ok\"}";

			int sid = sale.getId();
			preorder.setSid(sid);
			saleService.addPreorder(preorder);

			user.setBalance(user.getBalance() - amount);
			user.setPoint(user.getPoint() + (int)amount/10);
			userService.changeProfile(user);

			session.remove("saleitemlist");
			session.remove("amount");
		}

		return "result";
	}
	
	public String SaleOrder(){
		result = null;
		List<Branch> branchlist = branchService.getValidBranch();
		HashMap<Integer, Double> preordermap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> salemap = new HashMap<Integer, Double>();
		for(int i = 0; i != branchlist.size(); i ++){
			preordermap.put(branchlist.get(i).getId(), 0.0);
			salemap.put(branchlist.get(i).getId(), 0.0);
		}
		List<Preorder> preorderlist = saleService.getAllPreorder();
		for(int i = 0; i != preorderlist.size(); i ++){
			Sale sale = saleService.getSale(Integer.toString(preorderlist.get(i).getSid()));
			int bid = sale.getBid();
			preordermap.put(bid, preordermap.get(bid) + sale.getAmount());
		}
		List<Sale> salelist = saleService.getSales("state", "1");
		for(int i = 0; i != salelist.size(); i ++){
			int bid = salelist.get(i).getBid();
			salemap.put(bid, salelist.get(i).getAmount() + salemap.get(bid));
		}
		result = "{\"branches\":[";
		for(int i = 0; i != branchlist.size() - 1; i ++){
			int bid = branchlist.get(i).getId();
			result += "{\"bid\":\"" + bid + "\",\"preorder\":\"" + preordermap.get(bid) + "\",\"sale\":\"" + salemap.get(bid) + "\"},";
		}
		int lastbid = branchlist.get(branchlist.size() - 1).getId();
		result += "{\"bid\":\"" + lastbid + "\",\"preorder\":\"" + preordermap.get(lastbid) + "\",\"sale\":\"" + salemap.get(lastbid) + "\"}]}";
		
		System.out.println(result);
		
		return "result";
	}
	
	public String saleRecord(){
		if (session.get("employee") != null) {
			String mid = request.getParameter("id");
			List<Sale> salelist = saleService.getSales("mid", mid);
			session.put("salelist", salelist);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String getResult() {
		return result;
	}
}
