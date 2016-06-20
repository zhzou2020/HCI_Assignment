package edu.nju.desserthouse.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Employee;
import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.GoodsService;

@Controller
public class GoodsAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8897740017993873280L;
	
	@Autowired
	private GoodsService goodsService;
	
	private Goods goods;
	private String result;

	public String addGoods(){
		goods = new Goods();
		result = null;
		try{
			Double.parseDouble(request.getParameter("price"));
			String name = request.getParameter("name");
			double price = Double.valueOf(request.getParameter("price"));
			String info = request.getParameter("info");
			goods.setName(name);
			goods.setPrice(price);
			goods.setInfo(info);
			goods.setState(1);
			this.result = goodsService.addGoods(goods);
		} catch(NumberFormatException ex){
			this.result = "价格应为数字！";
		}
		return "result";
	}
	
	public String updateGoods(){
		result = null;
		String id = request.getParameter("id");
		Goods thisgoods = goodsService.findGoodsById(id); 
		String name = request.getParameter("name");
		String info = request.getParameter("info");
		try{
			Double.parseDouble(request.getParameter("price"));
			double price = Double.valueOf(request.getParameter("price"));
			thisgoods.setName(name);
			thisgoods.setPrice(price);
			thisgoods.setInfo(info);
			goodsService.updateGoods(thisgoods);
			this.result = "修改成功！";
		} catch(NumberFormatException ex){
			this.result = "价格应为数字！";
		}
		return "result";
	}
	
	public String goodsList(){
		List<Goods> list = goodsService.getValidGoods();
		session.put("goodslist", list);
		return SUCCESS;
	}
	
	public String goodsInfo(){
		goods = null;
		String id = request.getParameter("id");
		goods = goodsService.findGoodsById(id);
		session.put("thisGoods", goods);
		return SUCCESS;
	}
	
	public String GoodsInfo(){
		result = null;
		String id = request.getParameter("id");
		goods = goodsService.findGoodsById(id);
		String name = goods.getName();
		String price = Double.toString(goods.getPrice());
		result = "{\"name\":\"" + name + "\", \"price\":\"" + price + "\"}";
		return "result";
	}
	
	public String goodsInformation(){
		result = null;
		String gid = request.getParameter("gid");
		goods = goodsService.findGoodsById(gid);
		String name = goods.getName();
		String price = Double.toString(goods.getPrice());
		String description = goods.getInfo();
		Employee employee = (Employee) session.get("employee");
		String ifuser = "";
		if(employee == null){
			ifuser = "1";
		} else{
			ifuser = "0";
		}
		result = "{\"name\":\"" + name + "\", \"price\":\"" + price + "\", \"info\":\"" + description + "\", \"ifuser\":\"" + ifuser + "\"}";
		return "result";
	}
	
	public String execute(){
		return SUCCESS;
	}
	
	public String getResult(){
		return result;
	}
}
