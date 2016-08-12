package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.OrganizationType;


@Repository
public interface OrganizationTypeDao {
	
	public void delete(String id);
	
	public void insert(OrganizationType type);
	
	public List<OrganizationType> select(OrganizationType type);
	
	public void update(OrganizationType type);

	public OrganizationType selectone(String id);

}
