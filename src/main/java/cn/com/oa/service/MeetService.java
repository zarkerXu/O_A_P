package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.DocMeet;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Page;


public interface MeetService {

	public void delete(String id);

	public Meet find(String id);
	
	public List<Meet> findAll();

	public String insert(Meet meet);

	public void update(Meet meet);
	
	public List<Meet> findByParameter(Meet meet);
	/**
	 * 会议审计
	 * @param Meet
	 * @param page
	 * @return
	 */
	public Page findByParameterToPage(Meet meet,Page page);
	/**
	 * 会议接收
	 * @param Meet
	 * @param page
	 * @return
	 */
	public Page findByTaskToPage(Meet meet,Page page);
	/**
	 * 已发布会议
	 * @param Meet
	 * @param page
	 * @return
	 */
	public Page findByUserToPage(Meet meet,Page page);
	/**
	 * 判断会议是否已全部签收
	 * @param tid
	 * @return
	 */
	public Boolean isSign(String tid);
	/**
	 * 复制转发会议
	 * @param meet
	 * @return
	 */
	public void copyInsert(Meet meet);
	
	public Page mfindByRelayToPage(DocMeet docmeet, Page page);

	public Page mfindByApproveToPage(DocMeet docmeet, Page page);

	public List<DocMeet> getindexFirstGet(String oid);


}
