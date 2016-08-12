package cn.com.oa.service;


import java.util.List;

import cn.com.oa.model.Notice;
import cn.com.oa.model.Page;

public interface NoticeService {

	public String insert(Notice notice);

	public void delete(String id);
	/**
	 * 假删除
	 * @param id
	 */
	public void deleteNotice(String id);

	public void update(Notice notice);

	public Notice select(String id);
	
	public List<Notice> getNoticeList(String uid);
	
	public List<Notice> findByParameter(Notice notice);

	public Page findByParameterToPage(Notice notice, Page page);

	public Notice selectone(String id);

	public List<Notice> findByParameterinhome(String remark);
	

}
