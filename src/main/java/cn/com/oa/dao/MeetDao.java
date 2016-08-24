package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Doc;
import cn.com.oa.model.DocMeet;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Page;


@Repository
public interface MeetDao {

	public Meet select(String id);
	
	public List<Meet> findByParameter(Meet meet);
	
	public void insert(Meet meet);

	public void update(Meet meet);

	public void delete(String id);

	public List<Meet> selectAll();
	
	public List<Meet> findByParameterToPage(Page page);

	public Integer findByParameterToPages(Page page);
	
	public List<Meet> findByTaskToPage(Page page);
	
	public Integer findByTaskToPageCount(Page page);
	
	public List<Meet> findByUserToPage(Page page);
	
	public Integer findByUserToPageCount(Page page);
	
	public Boolean isSign(String tid);
	
	public void copyInsert(Meet meet);

	public Integer mfindByApproveToPageCount(Page page);

	public List<DocMeet> mfindByApproveToPage(Page page);

	public Integer mfindByRelayToPageCount(Page page);

	public List<DocMeet> mfindByRelayToPage(Page page);

	public List<DocMeet> getindexFirstGet(String oid);
	
}
