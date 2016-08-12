package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.MEntry;



@Repository
public interface MEntryDao {

	public MEntry select(String id);
	
	public void deleteByTaskid(String id);
	
	public void update(MEntry mEntry);
	
	public List<MEntry> findByParameter(MEntry mEntry);
	
	public void insertList(List<MEntry> mEntryList);
	
	public void insert(MEntry mEntry);

	public void delete(String id);

	public List<MEntry> selectAll();
	
	public List<MEntry> findByMeetid(MEntry mEntry);
	
	
}
