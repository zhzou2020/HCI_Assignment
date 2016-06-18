package edu.nju.desserthouse.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Stock;
import edu.nju.desserthouse.service.GoodsService;
import edu.nju.desserthouse.service.StockService;

@Controller
public class Tool2Action extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5811624444024827420L;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private StockService stockService;
	
	private String result;
	
	public String GoodsStockInfo() {
		result = null;

		String gid = request.getParameter("id");
		Goods goods = goodsService.findGoodsById(gid);
		String name = goods.getName();
		String order_day = (String) session.get("preorderdate");
		String bid = (String) session.get("preorderbid");
		Stock stock = stockService.getBidGidDateStock(bid, order_day, gid);
		int number = stock.getNumber();
		result = "{\"name\":\"" + name + "\",\"number\":\""
				+ Integer.toString(number) + "\"}";

		return "result";
	}
	
	public String getResult(){
		return result;
	}

}
