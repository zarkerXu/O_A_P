package cn.com.oa.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.NoticeDao;
import cn.com.oa.model.Notice;
import cn.com.oa.model.Page;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Notice notice) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.NoticeMapper.insert", notice);

	}

	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete("cn.com.oa.mapper.NoticeMapper.delete", id);

	}

	@Override
	public void update(Notice notice) {
		sqlSessionTemplate.update("cn.com.oa.mapper.NoticeMapper.update", notice);

	}

	@Override
	public Notice select(String id) {
		return sqlSessionTemplate
				.selectOne("cn.com.oa.mapper.NoticeMapper.select",id);
	}

	@Override
	public List<Notice> getNoticeList(String uid) {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.NoticeMapper.getNoticeList",uid);
	}

	@Override
	public List<Notice> findByParameter(Notice notice) {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.NoticeMapper.findByParameter",notice);
	}

	@Override
	public void deleteNotice(String id) {
		sqlSessionTemplate.update("cn.com.oa.mapper.NoticeMapper.deleteNotice", id);
	}

	
	@Override
	public Integer findByParameterToPages(Page page) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.NoticeMapper.findByParameterToPages",page);
	}

	@Override
	public List<Notice> findByParameterToPage(Page page) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.NoticeMapper.findByParameterToPage",page);
	}

	@Override
	public Notice selectone(String id) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.NoticeMapper.selectone",id);
	}

	@Override
	public List<Notice> findByParameterinhome(String remark) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.NoticeMapper.findByParameterinhome",remark);
	}


}
