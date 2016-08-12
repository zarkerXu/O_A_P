package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.MEntry;



public interface MEntryService {

	public void delete(String id);
	
	public void deleteByTaskid(String id);
	
	public void update(MEntry mEntry);

	public MEntry find(String id);
	
	public List<MEntry> findAll();

	/**
	 * 批量添加
	 * @param mEntryList
	 */
	public void insertList(List<MEntry> mEntryList);

	public List<MEntry> findByParameter(MEntry mEntry);
	
	public String insert(MEntry mEntry);
	/**
	 * id为 meetid
	 * @param mEntry
	 * @return
	 */
	public List<MEntry> findByMeetid(MEntry mEntry);
	
	

}
