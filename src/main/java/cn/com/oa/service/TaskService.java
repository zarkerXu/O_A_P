package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.Task;



public interface TaskService {

	public void delete(String id);
	
	public void deleteByPid(String id);

	public Task find(String id);
	
	public List<Task> findAll();

	public String insert(Task task);

	public void update(Task task);
	
	public List<Task> findByParameter(Task task);
	/**
	 * 获取签收信息
	 * @param did
	 * @return
	 */
	public List<Task> findCheckInfo(String did);
	/**
	 * 统一会议下的任务审批状态
	 * @param mid
	 * @param passStatus
	 */
	public void updatePassStatusByPid(String mid,Integer passStatus);
	/**
	 * 获取人员未签收公文或会议
	 * @param uid
	 * @param type(1:公文  2:会议)
	 * @return
	 */
	public Integer getIndexNum(String uid,Integer type);

	public List<Task> findapproved(Task task);

	public Integer getDocRelayNum(String did);

	public Integer getMeetRelayNum(String did);

	public void deletemt(String id);

	public Integer getNotApproveNum(String uid, Integer type);
	

}
