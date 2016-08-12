package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.User;


@Repository
public interface UserDao {

	public User select(String id);
	
	public List<User> findByParameter(User doc);
	
	public void insert(User doc);

	public void update(User doc);

	public void delete(String id);

	public List<User> selectAll();
	
	public List<String> selectuserid(User user);
	
	public List<User> findAllAdmin();
	
	public List<User> findUserByAdminid(String uid);
	
	public List<User> findOrganizationAllUser(String oid);
	
	public User findByAccount(String account);
	
	public List<User> findAllUsers();
	
	public User selectWithOrgnization(String id);
	
	public List<String> findUsernamebyOrganizations(String ids);

	public String finduidByOid(String oid);
	
}
