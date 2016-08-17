package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Notice;
import cn.com.oa.model.Page;



@Repository
public interface NoticeDao {
	
	public void insert(Notice notice);
	
	public void delete(String id);
	
	public void update(Notice notice);
	
	public Notice select(String id);
	
	public List<Notice> getNoticeList(String uid);
	
	public List<Notice> findByParameter(Notice notice);
	
	public void deleteNotice(String id);

	public Integer findByParameterToPages(Page page);

	public List<Notice> findByParameterToPage(Page page);

	public Notice selectone(String id);

	public List<Notice> findByParameterinhome(String remark);

	public List<Notice> getOwnNoticeList(String value);
	

}
