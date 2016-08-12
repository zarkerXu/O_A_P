package cn.com.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.MEntryDao;
import cn.com.oa.model.MEntry;
import cn.com.oa.service.MEntryService;
import cn.com.oa.util.UuidUtil;


@Service
@Transactional
public class MEntryServiceImpl implements MEntryService {
	
	@Resource
	private MEntryDao mEntryDao;
	
	public MEntryDao getMonitorSettingsDao() {
		return mEntryDao;
	}

	@Resource
	public void setMonitorSettingsDao(MEntryDao mEntryDao) {
		this.mEntryDao = mEntryDao;
	}

	@Override
	public void delete(String id) {
		mEntryDao.delete(id);
	}

	@Override
	public MEntry find(String id) {
		return mEntryDao.select(id);
	}

	@Override
	public List<MEntry> findAll() {
		return mEntryDao.selectAll();
	}

	@Override
	public void insertList(List<MEntry> mEntryList) {
		for(MEntry mEntry:mEntryList){
			String id=UuidUtil.get32UUID();
			mEntry.setId(id);
		}
		mEntryDao.insertList(mEntryList);
	}

	@Override
	public List<MEntry> findByParameter(MEntry mEntry) {
		return mEntryDao.findByParameter(mEntry);
	}

	@Override
	public void deleteByTaskid(String id) {
		mEntryDao.deleteByTaskid(id);
	}

	@Override
	public String insert(MEntry mEntry) {
		String id=UuidUtil.get32UUID();
		mEntry.setId(id);
		mEntryDao.insert(mEntry);
		return id;
	}

	@Override
	public void update(MEntry mEntry) {
		mEntryDao.update(mEntry);
	}

	@Override
	public List<MEntry> findByMeetid(MEntry mEntry) {
		return mEntryDao.findByMeetid(mEntry);
	}


}
