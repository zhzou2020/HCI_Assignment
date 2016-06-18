package edu.nju.desserthouse.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.nju.desserthouse.model.Branch;

public interface BranchService {
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String addBranch(Branch branch);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteBranch(String id);
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String updateBranch(Branch branch);
	
	public Branch findBranchById(String id);
	
	public String getBranchName(String id);
	
	public List<Branch> getValidBranch();
}
