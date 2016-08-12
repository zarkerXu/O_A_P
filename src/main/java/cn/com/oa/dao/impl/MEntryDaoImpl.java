package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.MEntryDao;
import cn.com.oa.model.MEntry;




@Repository
public class MEntryDaoImpl implements MEntryDao {
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<MEntry> findByParameter(MEntry mEntry) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MEntryMapper.findByParameter", mEntry);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.MEntryMapper.delete", id);
	}

	@Override
	public MEntry select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.MEntryMapper.select", id);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	public void insertList(List<MEntry> mEntryList) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.MEntryMapper.insertList",mEntryList);
	}

	@Override
	public List<MEntry> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByTaskid(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.MEntryMapper.deleteByTaskid", id);
	}

	@Override
	public void insert(MEntry mEntry) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.MEntryMapper.insert",mEntry);
	}

	@Override
	public void update(MEntry mEntry) {
		sqlSessionTemplate.update(
				"cn.com.oa.mapper.MEntryMapper.update", mEntry);
		
	}

	@Override
	public List<MEntry> findByMeetid(MEntry mEntry) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MEntryMapper.findByMeetid", mEntry);
	}
	


}
