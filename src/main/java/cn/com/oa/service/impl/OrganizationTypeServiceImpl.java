package cn.com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.OrganizationTypeDao;
import cn.com.oa.model.OrganizationType;
import cn.com.oa.service.OrganizationTypeService;

@Service
@Transactional
public class OrganizationTypeServiceImpl implements OrganizationTypeService {
	
	@Resource
	private OrganizationTypeDao organizationTypeDao;
	
	@Override
	public void delete(String id) {
		organizationTypeDao.delete(id);
	}

	@Override
	public void insert(OrganizationType type) {
		organizationTypeDao.insert(type);

	}

	@Override
	public List<OrganizationType> select(OrganizationType type) {
		return organizationTypeDao.select(type);
	}

	@Override
	public void update(OrganizationType type) {
		organizationTypeDao.update(type);
	}

	@Override
	public OrganizationType selectone(String id) {
		// TODO Auto-generated method stub
		return organizationTypeDao.selectone(id);
	}

}
