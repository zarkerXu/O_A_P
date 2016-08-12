package cn.com.oa.service;

import java.util.List;

import cn.com.oa.model.OrganizationType;


public interface OrganizationTypeService {
	
	public void delete(String id);
	
	public void insert(OrganizationType type);
	
	public List<OrganizationType> select(OrganizationType type);
	
	public void update(OrganizationType type);
	
	public OrganizationType selectone(String id);
	

}
