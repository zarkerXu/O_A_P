package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.DocDao;
import cn.com.oa.model.Doc;
import cn.com.oa.model.Page;


@Repository
public class DocDaoImpl implements DocDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	

	@Override
	public List<Doc> findByParameter(Doc doc) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.DocMapper.findByParameter", doc);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.DocMapper.delete", id);
	}

	@Override
	public Doc select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.DocMapper.select", id);
	}

	@Override
	public List<Doc> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.DocMapper.selectAll");
	}

	@Override
	public void insert(Doc doc) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.DocMapper.insert",doc);
	}

	@Override
	public void update(Doc doc) {
		sqlSessionTemplate.update("cn.com.oa.mapper.DocMapper.update",doc);
	}
	@Override
	public List<Doc> findByParameterToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.DocMapper.findByParameterToPage", page);
	}
	
	@Override
	public Integer findByParameterToPages(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.DocMapper.findByParameterToPages", page);
	}
	@Override
	public List<Doc> findByTaskToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.DocMapper.findByTaskToPage", page);
	}
	@Override
	public Integer findByTaskToPageCount(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.DocMapper.findByTaskToPageCount", page);
	}
	@Override
	public Boolean isSign(String tid) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.DocMapper.isSign", tid);
	}
	@Override
	public List<Doc> findByUserToPage(Page page) {
		return   sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.DocMapper.findByUserToPage", page);
	}
	@Override
	public Integer findByUserToPageCount(Page page) {
		return   sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.DocMapper.findByUserToPageCount", page);
	}
	@Override
	public void copyInsert(Doc doc) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.DocMapper.copyInsert",doc);
	}

}
