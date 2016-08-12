package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Doc;
import cn.com.oa.model.Page;


@Repository
public interface DocDao {

	public Doc select(String id);
	
	public List<Doc> findByParameter(Doc doc);
	
	public void insert(Doc doc);

	public void update(Doc doc);

	public void delete(String id);

	public List<Doc> selectAll();
	
	public List<Doc> findByParameterToPage(Page page);

	public Integer findByParameterToPages(Page page);
	
	public List<Doc> findByTaskToPage(Page page);
	
	public Integer findByTaskToPageCount(Page page);
	
	public List<Doc> findByUserToPage(Page page);
	
	public Integer findByUserToPageCount(Page page);
	
	public Boolean isSign(String tid);
	
	public void copyInsert(Doc doc);

	
}
