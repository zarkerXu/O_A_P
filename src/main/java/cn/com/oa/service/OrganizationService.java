package cn.com.oa.service;

import java.util.List;

import cn.com.oa.model.Organization;

public interface OrganizationService {

	public void delete(String id);

	public void del(String id);

	public Organization find(String id);

	public List<Organization> findAll();

	public String insert(Organization organization);

	public void update(Organization organization);

	public List<Organization> findByParameter(Organization organization);

	public List<Organization> findParentById(String id);

	public List<Organization> findFirstOrganize();

	public List<Organization> findallChildOrganize(String pid);

}
