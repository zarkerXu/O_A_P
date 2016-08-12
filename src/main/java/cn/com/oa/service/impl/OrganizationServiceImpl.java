package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.OrganizationDao;
import cn.com.oa.model.Organization;
import cn.com.oa.service.OrganizationService;
import cn.com.oa.util.UuidUtil;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	@Resource
	private OrganizationDao organizationDao;
	

	@Override
	public void delete(String id) {
		organizationDao.delete(id);
	}
	
	@Override
	public void del(String id) {
		organizationDao.del(id);
	}

	@Override
	public Organization find(String id) {
		return organizationDao.select(id);
	}

	@Override
	public List<Organization> findAll() {
		return organizationDao.selectAll();
	}

	@Override
	public String insert(Organization organization) {
		String id=UuidUtil.get32UUID();
		organization.setId(id);
		organization.setCreateTime(new Date());
		organizationDao.insert(organization);
		return id;
	}

	@Override
	public void update(Organization organization) {
		organization.setUpdataTime(new Date());
		organizationDao.update(organization);
	}

	@Override
	public List<Organization> findByParameter(Organization organization) {
		return organizationDao.findByParameter(organization);
	}

	@Override
	public List<Organization> findParentById(String id) {
		return organizationDao.findParentById(id);
	}
	
	@Override
	public List<Organization> findFirstOrganize() {
		// TODO Auto-generated method stub
		return organizationDao.findFirstOrganize();
	}

	@Override
	public List<Organization> findallChildOrganize(String pid) {
		// TODO Auto-generated method stub
		return organizationDao.findallChildOrganize(pid);
	}

}
