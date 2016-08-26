package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.MeetDao;
import cn.com.oa.model.DocMeet;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Page;


@Repository
public class MeetDaoImpl implements MeetDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	

	@Override
	public List<Meet> findByParameter(Meet meet) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MeetMapper.findByParameter", meet);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.MeetMapper.delete", id);
	}

	@Override
	public Meet select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.MeetMapper.select", id);
	}

	@Override
	public List<Meet> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.MeetMapper.selectAll");
	}

	@Override
	public void insert(Meet meet) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.MeetMapper.insert",meet);
	}

	@Override
	public void update(Meet meet) {
		sqlSessionTemplate.update("cn.com.oa.mapper.MeetMapper.update",meet);
	}
	@Override
	public List<Meet> findByParameterToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MeetMapper.findByParameterToPage", page);
	}
	
	@Override
	public Integer findByParameterToPages(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.MeetMapper.findByParameterToPages", page);
	}
	@Override
	public List<Meet> findByTaskToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MeetMapper.findByTaskToPage", page);
	}
	@Override
	public Integer findByTaskToPageCount(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.MeetMapper.findByTaskToPageCount", page);
	}
	@Override
	public Boolean isSign(String tid) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.MeetMapper.isSign", tid);
	}
	@Override
	public List<Meet> findByUserToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.MeetMapper.findByUserToPage", page);
	}
	@Override
	public Integer findByUserToPageCount(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.MeetMapper.findByUserToPageCount", page);
	}
	@Override
	public void copyInsert(Meet meet) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.MeetMapper.copyInsert",meet);
	}
	@Override
	public Integer mfindByApproveToPageCount(Page page) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.MeetMapper.mfindByApproveToPageCount",page);
	}
	@Override
	public List<DocMeet> mfindByApproveToPage(Page page) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.MeetMapper.mfindByApproveToPage",page);
	}
	@Override
	public Integer mfindByRelayToPageCount(Page page) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.MeetMapper.mfindByRelayToPageCount",page);
	}
	@Override
	public List<DocMeet> mfindByRelayToPage(Page page) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.MeetMapper.mfindByRelayToPage",page);
	}
	@Override
	public List<DocMeet> getindexFirstGet(String oid) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.MeetMapper.getindexFirstGet",oid);
	}

}
