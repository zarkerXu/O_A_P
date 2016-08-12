package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.Doc;
import cn.com.oa.model.Page;


public interface DocService {

	public void delete(String id);

	public Doc find(String id);
	
	public List<Doc> findAll();

	public String insert(Doc doc);

	public void update(Doc doc);
	
	public List<Doc> findByParameter(Doc doc);
	/**
	 * 公文审计
	 * @param doc
	 * @param page
	 * @return
	 */
	public Page findByParameterToPage(Doc doc,Page page);
	/**
	 * 公文接收
	 * @param doc
	 * @param page
	 * @return
	 */
	public Page findByTaskToPage(Doc doc,Page page);
	/**
	 * 已发布公文
	 * @param doc
	 * @param page
	 * @return
	 */
	public Page findByUserToPage(Doc doc,Page page);
	/**
	 * 判断公文是否已全部签收
	 * @param tid
	 * @return
	 */
	public Boolean isSign(String tid);
	/**
	 * 复制转发公文
	 * @param doc
	 */
	public void copyInsert(Doc doc);

}
