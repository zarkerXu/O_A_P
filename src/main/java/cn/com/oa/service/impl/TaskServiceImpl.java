package cn.com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.TaskDao;
import cn.com.oa.model.Task;
import cn.com.oa.service.TaskService;
import cn.com.oa.util.UuidUtil;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	private TaskDao taskDao;
	
	public TaskDao getMonitorSettingsDao() {
		return taskDao;
	}

	@Resource
	public void setMonitorSettingsDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public void delete(String id) {
		taskDao.delete(id);
	}

	@Override
	public Task find(String id) {
		return taskDao.select(id);
	}

	@Override
	public List<Task> findAll() {
		return taskDao.selectAll();
	}

	@Override
	public String insert(Task task) {
		String id=UuidUtil.get32UUID();
		task.setId(id);
		taskDao.insert(task);
		return id;
	}

	@Override
	public void update(Task task) {
		taskDao.update(task);
	}

	@Override
	public List<Task> findByParameter(Task task) {
		return taskDao.findByParameter(task);
	}

	@Override
	public List<Task> findCheckInfo(String did) {
		return taskDao.findCheckInfo(did);
	}

	@Override
	public void updatePassStatusByPid(String mid, Integer passStatus) {
		Task task=new Task();
		task.setDid(mid);
		task.setPassStatus(passStatus);
		taskDao.updatePassStatusByPid(task);
	}

	@Override
	public Integer getIndexNum(String uid, Integer type) {
		Task task=new Task();
		task.setUid(uid);
		task.setType(type);
		return taskDao.getIndexNum(task);
	}

	@Override
	public void deleteByPid(String id) {
		taskDao.deleteByPid(id);
	}

	@Override
	public List<Task> findapproved(Task task) {
		return taskDao.findapproved(task);
	}

	@Override
	public Integer getDocRelayNum(String did) {
		return taskDao.getDocRelayNum(did);
	}

	@Override
	public Integer getMeetRelayNum(String did) {
		return taskDao.getMeetRelayNum(did);
	}

}
