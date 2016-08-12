package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.oa.dao.DocDao;
import cn.com.oa.model.Doc;
import cn.com.oa.model.Page;
import cn.com.oa.service.DocService;
import cn.com.oa.util.UuidUtil;


@Service
public class DocServiceImpl implements DocService {
	
	@Resource
	private DocDao docDao;
	

	@Override
	public void delete(String id) {
		docDao.delete(id);
	}

	@Override
	public Doc find(String id) {
		return docDao.select(id);
	}

	@Override
	public List<Doc> findAll() {
		return docDao.selectAll();
	}

	@Override
	public String insert(Doc doc) {
		String id=UuidUtil.get32UUID();
		doc.setId(id);
		doc.setCreateTime(new Date());
		docDao.insert(doc);
		return id;
	}

	@Override
	public void update(Doc doc) {
		doc.setUpdataTime(new Date());
		docDao.update(doc);
	}

	@Override
	public List<Doc> findByParameter(Doc doc) {
		return docDao.findByParameter(doc);
	} 

	@Override
	public Page findByParameterToPage(Doc doc, Page page) {
		if(doc.getDocNo()!=null){
			doc.setDocNo("%"+doc.getDocNo()+"%");
		}
		if(doc.getDocTitle()!=null){
			doc.setDocTitle("%"+doc.getDocTitle()+"%");
		}
		page.setPageObject(doc);
		page.setTotalPage(docDao.findByParameterToPages(page));
		page.setBefore((page.getPage()-1)*page.getSize());
		page.setPageObject(docDao.findByParameterToPage(page));
		return page;
	}

	@Override
	public Page findByTaskToPage(Doc doc, Page page) {
		if(doc.getDocNo()!=null){
			doc.setDocNo("%"+doc.getDocNo()+"%");
		}
		if(doc.getDocNo()!=null){
			doc.setDocTitle("%"+doc.getDocTitle()+"%");
		}
		page.setPageObject(doc);
		page.setTotalPage(docDao.findByTaskToPageCount(page));
		page.setBefore((page.getPage()-1)*page.getSize());
		page.setPageObject(docDao.findByTaskToPage(page));
		return page;
	}

	@Override
	public Boolean isSign(String tid) {
		return docDao.isSign(tid);
	}

	@Override
	public Page findByUserToPage(Doc doc, Page page) {
		if(doc.getDocNo()!=null){
			doc.setDocNo("%"+doc.getDocNo()+"%");
		}
		if(doc.getDocTitle()!=null){
			doc.setDocTitle("%"+doc.getDocTitle()+"%");
		}
		page.setPageObject(doc);
		page.setTotalPage(docDao.findByUserToPageCount(page));
		page.setBefore((page.getPage()-1)*page.getSize());
		page.setPageObject(docDao.findByUserToPage(page));
		return page;
	}

	@Override
	public void copyInsert(Doc doc) {
		doc.setCreateTime(new Date());
		docDao.copyInsert(doc);
		
	}



}
