package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.UserDao;
import cn.com.oa.model.User;
import cn.com.oa.service.UserService;
import cn.com.oa.util.UuidUtil;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	

	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Override
	public User find(String id) {
		return userDao.select(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.selectAll();
	}

	@Override
	public String insert(User user) {
		String id=UuidUtil.get32UUID();
		user.setId(id);
		user.setCreateTime(new Date());
		userDao.insert(user);
		return id;
	}

	@Override
	public void update(User user) {
		user.setUpdataTime(new Date());
		userDao.update(user);
	}

	@Override
	public List<User> findByParameter(User user) {
		return userDao.findByParameter(user);
	}

	@Override
	public List<User> findAllAdmin() {
		return userDao.findAllAdmin();
	}
	
	@Override
	public List<String> selectuserid(User user) {
		return userDao.selectuserid(user);
	}

	@Override
	public List<User> findUserByAdminid(String id) {
		return userDao.findUserByAdminid(id);
	}

	@Override
	public List<User> findOrganizationAllUser(String oid) {
		return userDao.findOrganizationAllUser(oid);
	}

	@Override
	public User findByAccount(String account) {
		return userDao.findByAccount(account);
	}

	@Override
	public User selectWithOrgnization(String id) {
		return userDao.selectWithOrgnization(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public List<String> findUsernamebyOrganizations(String ids) {
		return userDao.findUsernamebyOrganizations(ids);
	}

	@Override
	public String finduidByOid(String oid) {
		return userDao.finduidByOid(oid);
	}

}
