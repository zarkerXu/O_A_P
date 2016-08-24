package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.TaskDao;
import cn.com.oa.model.Task;



@Repository
public class TaskDaoImpl implements TaskDao {
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<Task> findByParameter(Task task) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.TaskMapper.findByParameter", task);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.update(
				"cn.com.oa.mapper.TaskMapper.deleteupdate", id);
	}
	
	@Override
	public void deletemt(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.TaskMapper.deletemt", id);
		
	}

	@Override
	public void deleteByPid(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.TaskMapper.deleteByPid", id);
	}
	
	@Override
	public Task select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.TaskMapper.select", id);
	}

	@Override
	public List<Task> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.TaskMapper.selectAll");
	}

	@Override
	public void insert(Task task) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.TaskMapper.insert",task);
	}

	@Override
	public void update(Task task) {
		sqlSessionTemplate.update("cn.com.oa.mapper.TaskMapper.update",task);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	@Override
	public List<Task> findCheckInfo(String did) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.TaskMapper.findcheckinfo",did);
	}

	@Override
	public void updatePassStatusByPid(Task task) {
		sqlSessionTemplate.update("cn.com.oa.mapper.TaskMapper.updatePassStatusByPid",task);
	}

	@Override
	public Integer getIndexNum(Task task) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.TaskMapper.getIndexNum", task);
	}

	@Override
	public List<Task> findapproved(Task task) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.TaskMapper.findapproved",task);
	}

	@Override
	public Integer getDocRelayNum(String did) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.TaskMapper.getDocRelayNum",did);
	}

	@Override
	public Integer getMeetRelayNum(String did) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.TaskMapper.getMeetRelayNum",did);
	}

	@Override
	public Integer getNotApproveNum(Task task) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.TaskMapper.getNotApproveNum",task);
	}

	@Override
	public List<String> getindexNoPassMeet(String oid) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.TaskMapper.getindexNoPassMeet",oid);
	}

	



}
