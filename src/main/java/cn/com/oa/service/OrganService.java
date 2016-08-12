package cn.com.oa.service;

import java.util.List;

import cn.com.oa.model.Organ;
import cn.com.oa.model.Organization;

public interface OrganService {
	
	public void insert(Organ organ);
	
	public void delete(String id);
	
	public void update(Organ organ);
	
	public List<Organ> select(Organ organ);
	
	public List<Organization> findalldeprt (String id);
	
	public Organ selectone(String ids);
	
	

}
