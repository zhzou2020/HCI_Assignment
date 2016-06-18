package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Preorder;

public interface PreorderDao {
	public void save(Preorder order);
	
	public void update(Preorder order);
	
	public Preorder findById(String id);
	
	public List<Preorder> find(String colname, String value);
	
	public List<Preorder> all();
}
