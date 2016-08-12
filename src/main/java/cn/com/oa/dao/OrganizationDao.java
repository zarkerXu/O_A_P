package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Organization;



@Repository
public interface OrganizationDao {

	public Organization select(String id);
	
	public List<Organization> findByParameter(Organization organization);
	
	public void insert(Organization organization);

	public void update(Organization organization);

	public void delete(String id);
	
	public void del(String id);
	
	public void deluser(String id);

	public List<Organization> selectAll();
	
	public List<Organization> findParentById(String id);
	
	public List<Organization> findFirstOrganize();

	public List<Organization> findallChildOrganize(String pid);
	
	
}
