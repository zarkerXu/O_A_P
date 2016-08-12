package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.AttachmentDao;
import cn.com.oa.model.Attachment;



@Repository
public class AttachmentDaoImpl implements AttachmentDao {
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public List<Attachment> findByParameter(Attachment attachment) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.AttachmentMapper.findByParameter", attachment);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.AttachmentMapper.delete", id);
	}

	@Override
	public Attachment select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.AttachmentMapper.select", id);
	}

	@Override
	public List<Attachment> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.AttachmentMapper.selectAll");
	}

	@Override
	public void insert(Attachment attachment) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.AttachmentMapper.insert",attachment);
	}

	@Override
	public void update(Attachment attachment) {
		sqlSessionTemplate.update("cn.com.oa.mapper.AttachmentMapper.update",attachment);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}


}
