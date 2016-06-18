package edu.nju.desserthouse.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.AccountDao;
import edu.nju.desserthouse.dao.PayRecordDao;
import edu.nju.desserthouse.dao.UserDao;
import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.PayRecord;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Session Bean implementation class UserManageServiceBean
 */
@Service
public class UserManageServiceImpl implements UserManageService {

	/**
	 * Default constructor.
	 */
	@Autowired
	private UserDao userDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private PayRecordDao payrecordDao;

	public User validateUser(String phoneNo, String password) {
		List<User> list = userDao.find("phoneNo", phoneNo);
		if (list.size() == 0) {
			return null;
		} else {
			User user = list.get(0);
			if (user == null) {
				return null;
			} else if (!user.getPassword().equals(password) || user.getState() == -1) {
				return null;
			}
			return user;
		}

	}

	public Integer validateNumber(String number) {
		Integer num = null;
		try {
			num = Integer.valueOf(number);
		} catch (Exception e) {
			return null;
		}

		if (num.intValue() <= 0) {
			return null;
		}

		return num;
	}

	public void sentErrorMessage(String message, HttpServletRequest req)
			throws ServletException, IOException {
		req.setAttribute("message", message);
		// RequestDispatcher
		// dispater=req.getRequestDispatcher(resp.encodeURL("/error/error.jsp"));
		// dispater.forward(req,resp);
	}

	public void sentMessage(String message, HttpServletRequest req)
			throws ServletException, IOException {
		req.setAttribute("message", message);
		// RequestDispatcher
		// dispater=req.getRequestDispatcher(resp.encodeURL("/message/message.jsp"));
		// dispater.forward(req,resp);
	}

	public void forwardPage(String page, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispater = req.getRequestDispatcher(resp
				.encodeURL(page));
		dispater.forward(req, resp);
	}

	public String registerUser(User user) {
		String message = null;
		if (ifexist(user.getPhoneNo())) {
			message = "phone number exist";
			return message;
		} else {
			System.out.println(" Ready to save user");
			if (userDao == null) {
				System.out.println(" userDao is null");
			}
			userDao.save(user);
			System.out.println(user.getId());

			return message;
		}
	}

	private boolean ifexist(String phoneNo) {
		List<User> user = userDao.find("phoneNo", phoneNo);
		if (user.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String topupUser(String accountid, String password, double amount,
			User user) {
		String message = null;
		if (accountDao == null) {
			return "accountDao null";
		}
		if(amount < 0){
			return "账户余额不足！";
		}
		Account account = accountDao.find(accountid);
		if (account == null) {
			message = "账户不存在！";
		} else if (!account.getPassword().equals(password)) {
			message = "密码错误！";
		} else if (account.getBalance() < amount) {
			message = "余额不足，无法充值！";
		} else {
			boolean result = accountDao.minusBalance(accountid, amount);
			if (!result) {
				message = "数据库错误！";
			} else {
				// 充值成功，修改账户余额
				user.setBalance(user.getBalance() + amount);
				user.setMoney(user.getMoney() + amount);
				if(user.getState() == 0){
					if(amount >= 200){
						user.setState(1);
						Calendar cal=Calendar.getInstance();    
						Date today = cal.getTime();
						user.setValidDate(today);
						message = "充值成功，您的账户已激活！您的账户余额为" + Double.toString(user.getBalance()) + "元";
					} else{
						message = "充值成功，本次充值金额不足200元，无法激活账户! 您的账户余额为" + Double.toString(user.getBalance()) + "元";
					}
				} else if(user.getState() == 2){
					user.setState(1);
					Calendar cal=Calendar.getInstance();    
					Date today = cal.getTime();
					user.setValidDate(today);
					message = "充值成功，您的会员卡恢复正常状态！您的账户余额为" + Double.toString(user.getBalance()) + "元";
				} else{
					message = "充值成功！您的账户余额为" + Double.toString(user.getBalance()) + "元";
				}
				if(user.getState() == 1){
					this.rankChange(user);
				}
				userDao.updateByUserid(user);
				//生成充值记录
				PayRecord record = new PayRecord();
				Calendar cal = Calendar.getInstance();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				String time = sdf.format(cal.getTime());
				record.setDate(time);
				record.setMid(user.getId());
				record.setAmount(amount);
				payrecordDao.save(record);
			}
		}
		return message;
	}

	@Override
	public int getIdByPhone(String phoneNo) {
		List<User> list = userDao.find("phoneNo", phoneNo);
		User thisUser = list.get(0);
		return thisUser.getId();
	}

	@Override
	public void rankChange(User user) {
		double amount = user.getMoney();
		int rank = user.getRank();
		int[] rankRule = {200, 400, 800, 1200, 1600};
		if(rankRule[rank] < amount){
			for(int i = rankRule.length - 1; i != 0; i --){
				if(amount < rankRule[i] && amount >= rankRule[i - 1]){
					user.setRank(i);
				}
			}
		}
	}

	@Override
	public String changeProfile(User user) {
		userDao.updateByUserid(user);
		return "更新成功！";
	}

	@Override
	public String stopUser(User user) {
		user.setState(-1);
		userDao.updateByUserid(user);
		return "停止成功,取消会员资格！";
	}

	@Override
	public User getUserByPhone(String phoneNo) {
		List<User> list = userDao.find("phoneNo", phoneNo);
		if(list.size() == 0){
			return null;
		} else{
			return list.get(0);
		}
	}

	@Override
	public void pauseTask() {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date date = cal.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		List<User> list = userDao.findBeforeTime(date, "1");
		for(int i = 0; i != list.size(); i ++){
			User user = list.get(i);
			if(user.getBalance() < 200){
				user.setValidDate(today);
				user.setState(2);
				userDao.updateByUserid(user);
			}
		}
	}

	@Override
	public void stopTask() {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date date = cal.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		
		List<User> list = userDao.findBeforeTime(date, "2");
		for(int i = 0; i != list.size(); i ++){
			User user = list.get(i);
			user.setValidDate(today);
			user.setState(3);
			userDao.updateByUserid(user);
		}
	}

	@Override
	public List<PayRecord> getPayrecord(String id) {
		return payrecordDao.findUserRecord(id);
	}

	@Override
	public User getUserById(String id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findString(String colname, String value) {
		return userDao.findString(colname, value);
	}

	@Override
	public List<User> find(String colname, String value) {
		return userDao.find(colname, value);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.findAllUser();
	}
}
