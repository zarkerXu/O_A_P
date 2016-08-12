package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.User;



public interface UserService {

	public void delete(String id);

	public User find(String id);
	
	public List<User> findAll();

	public String insert(User doc);

	public void update(User doc);
	
	public List<User> findByParameter(User doc);
	
	public List<User> findAllAdmin();
	
	public List<String> selectuserid(User user);
	
	public List<User> findUserByAdminid(String id);
	
	public List<User> findOrganizationAllUser(String oid);
	
	public User findByAccount(String account);
	
	public List<User> findAllUsers();
	
	public User selectWithOrgnization(String id);
	
	public List<String> findUsernamebyOrganizations(String ids);

	public String finduidByOid(String oid);

}
