package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.OrganDao;
import cn.com.oa.model.Organ;
import cn.com.oa.model.Organization;

@Repository
public class OrganDaoImpl implements OrganDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Organ organ) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.OrganMapper.insert", organ);

	}

	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete("cn.com.oa.mapper.OrganMapper.delete", id);

	}

	@Override
	public void update(Organ organ) {
		sqlSessionTemplate.update("cn.com.oa.mapper.OrganMapper.update", organ);

	}

	@Override
	public List<Organ> select(Organ organ) {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.OrganMapper.select",organ);
	}

	@Override
	public List<Organization> findalldeprt(String id) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.OrganMapper.findalldepartmentnames", id);
	}

	@Override
	public Organ selectone(String ids) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.OrganMapper.selectone", ids);
	}

}
