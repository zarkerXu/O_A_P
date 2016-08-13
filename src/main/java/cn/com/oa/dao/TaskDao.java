package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Task;



@Repository
public interface TaskDao {

	public Task select(String id);
	
	public List<Task> findByParameter(Task task);
	
	public void insert(Task task);

	public void update(Task task);

	public void delete(String id);
	
	public void deleteByPid(String id);

	public List<Task> selectAll();
	
	public List<Task> findCheckInfo(String did);
	
	public void updatePassStatusByPid(Task task);
	
	public Integer getIndexNum(Task task);

	public List<Task> findapproved(Task task);

	public Integer getDocRelayNum(String did);

	public Integer getMeetRelayNum(String did);

	public void deletemt(String id);

	public Integer getNotApproveNum(Task task);
	
	
}
