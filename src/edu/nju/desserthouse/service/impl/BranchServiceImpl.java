package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.BranchDao;
import edu.nju.desserthouse.model.Branch;
import edu.nju.desserthouse.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService{
	
	/**
	 * Default constructor
	 */
	@Autowired
	private BranchDao branchDao;
	
	@Override
	public String addBranch(Branch branch) {
		branchDao.save(branch);
		return "添加成功！";
	}

	@Override
	public String deleteBranch(String id) {
		Branch branch = branchDao.findById(id);
		branch.setState(0);
		branchDao.update(branch);
		return "删除成功！";
	}

	@Override
	public String updateBranch(Branch branch) {
		branchDao.update(branch);
		return "信息更新成功！";
	}

	@Override
	public Branch findBranchById(String id) {
		return branchDao.findById(id);
	}

	@Override
	public String getBranchName(String id) {
		return branchDao.getNameById(id);
	}

	@Override
	public List<Branch> getValidBranch() {
		List<Branch> list = branchDao.find("state", "1");
		return list;
	}

}
