package cn.com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.oa.dao.OrganDao;
import cn.com.oa.model.Organ;
import cn.com.oa.model.Organization;
import cn.com.oa.service.OrganService;
import cn.com.oa.util.UuidUtil;

@Service
public class OrganServiceImpl implements OrganService {

	@Resource
	private OrganDao organdao;

	@Override
	public void insert(Organ organ) {
		organ.setId(UuidUtil.get32UUID());
		organdao.insert(organ);

	}

	@Override
	public void delete(String id) {
		organdao.delete(id);

	}

	@Override
	public void update(Organ organ) {

		organdao.update(organ);
	}

	@Override
	public List<Organ> select(Organ organ) {
		return organdao.select(organ);
	}

	@Override
	public List<Organization> findalldeprt(String id) {
		// TODO Auto-generated method stub
		return organdao.findalldeprt(id);
	}

	@Override
	public Organ selectone(String id) {
		// TODO Auto-generated method stub
		return organdao.selectone(id);
	}

	

}
