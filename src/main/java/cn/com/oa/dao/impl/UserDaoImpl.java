package cn.com.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.com.oa.dao.UserDao;
import cn.com.oa.model.User;


@Repository
public class UserDaoImpl implements UserDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	

	@Override
	public List<User> findByParameter(User user) {
		return sqlSessionTemplate.selectList(
				"cn.com.oa.mapper.UserMapper.findByParameter", user);
	}
	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(
				"cn.com.oa.mapper.UserMapper.delete", id);
	}

	@Override
	public User select(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.UserMapper.select", id);
	}

	@Override
	public List<User> selectAll() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.UserMapper.selectAll");
	}

	@Override
	public void insert(User user) {
		sqlSessionTemplate.insert("cn.com.oa.mapper.UserMapper.insert",user);
	}

	@Override
	public void update(User user) {
		sqlSessionTemplate.update("cn.com.oa.mapper.UserMapper.update",user);
	}
	@Override
	public List<User> findAllAdmin() {
		return sqlSessionTemplate
				.selectList("cn.com.oa.mapper.UserMapper.findAllAdmin");
	}

	@Override
	public List<String> selectuserid(User user) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.UserMapper.selectuserid",user);
	}
	@Override
	public List<User> findUserByAdminid(String id) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.UserMapper.findUserByAdminid",id);
	}
	@Override
	public List<User> findOrganizationAllUser(String oid) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.UserMapper.findOrganizationAllUser",oid);
	}
	@Override
	public User findByAccount(String account) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.UserMapper.findByAccount", account);
	}
	@Override
	public User selectWithOrgnization(String id) {
		return sqlSessionTemplate.selectOne(
				"cn.com.oa.mapper.UserMapper.selectWithOrgnization", id);
	}
	@Override
	public List<User> findAllUsers() {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.UserMapper.findAllUsers");
	}
	@Override
	public List<String> findUsernamebyOrganizations(String ids) {
		return sqlSessionTemplate.selectList("cn.com.oa.mapper.UserMapper.findUsernamebyOrganizations",ids);
	}
	@Override
	public String finduidByOid(String oid) {
		return sqlSessionTemplate.selectOne("cn.com.oa.mapper.UserMapper.finduidByOid",oid);
	}

}
