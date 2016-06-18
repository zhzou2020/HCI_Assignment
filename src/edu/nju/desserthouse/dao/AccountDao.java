package edu.nju.desserthouse.dao;

import edu.nju.desserthouse.model.Account;

public interface AccountDao {
	/**
	 * 
	 * @param account
	 */
	public Account find(String account);
	
	/**
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public boolean minusBalance(String account, double amount);
	
}
