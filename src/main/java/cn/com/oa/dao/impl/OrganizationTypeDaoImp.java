package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.OrganizationTypeDao;
import cn.com.oa.model.OrganizationType;

@Repository
public class OrganizationTypeDaoImp implements OrganizationTypeDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void delete(String id) {
		sqlSessionTemplate.update("cn.com.oa.mapper.OrganizationTypeMapper.updatedel",id);

	}

	@Override
	public void insert(OrganizationType type) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.OrganizationTypeMapper.insert",type);

	}

	@Override
	public List<OrganizationType> select(OrganizationType type) {
		
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.OrganizationTypeMapper.select",type);
	}

	@Override
	public void update(OrganizationType type) {
         sqlSessionTemplate.update("cn.com.oa.mapper.OrganizationTypeMapper.update",type);
	}

	@Override
	public OrganizationType selectone(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.OrganizationTypeMapper.selectone",id);
	}

}
