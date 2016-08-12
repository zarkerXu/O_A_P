package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.OrganizationDao;
import cn.com.oa.model.Organization;


@Repository
public class OrganizationDaoImpl implements OrganizationDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	

	@Override
	public List<Organization> findByParameter(Organization organization) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.OrganizationMapper.findByParameter", organization);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.OrganizationMapper.delete", id);
	}
	
	@Override
	public void del(String id) {
		sqlSessionTemplate.update(
				"cn.com.oa.mapper.OrganizationMapper.del", id);
	}
	
	@Override
	public void deluser(String id){
		sqlSessionTemplate.update(
				"cn.com.oa.mapper.OrganizationMapper.deluser", id);
	}

	@Override
	public Organization select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.OrganizationMapper.select", id);
	}

	@Override
	public List<Organization> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.OrganizationMapper.selectAll");
	}

	@Override
	public void insert(Organization organization) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.OrganizationMapper.insert",organization);
	}

	@Override
	public void update(Organization organization) {
		sqlSessionTemplate.update("cn.com.oa.mapper.OrganizationMapper.update",organization);
	}
	@Override
	public List<Organization> findParentById(String id) {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.OrganizationMapper.findParentById",id);
	}
	
	@Override
	public List<Organization> findFirstOrganize() {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.OrganizationMapper.findFirstOrganize");
	}
	@Override
	public List<Organization> findallChildOrganize(String pid) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.OrganizationMapper.findallChildOrganize",pid);
	}


}
