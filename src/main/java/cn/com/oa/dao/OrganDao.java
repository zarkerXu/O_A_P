package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Organ;
import cn.com.oa.model.Organization;


@Repository
public interface OrganDao {
	
	public void insert(Organ organ);
	
	public void delete(String id);
	
	public void update(Organ organ);
	
	public List<Organ> select(Organ organ);
	
	public List<Organization> findalldeprt (String id);
	
	public Organ selectone(String ids);

}
