package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.PayRecord;

public interface PayRecordDao {
	public void save(PayRecord record);
	
	public void update(PayRecord record);
	
	public PayRecord findById(String id);
	
	public List<PayRecord> find(String colname, String value);
	
	public List<PayRecord> findUserRecord(String mid);
}
