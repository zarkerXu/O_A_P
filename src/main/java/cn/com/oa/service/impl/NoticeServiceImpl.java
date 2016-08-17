package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.NoticeDao;
import cn.com.oa.model.Notice;
import cn.com.oa.model.Page;
import cn.com.oa.service.NoticeService;
import cn.com.oa.util.UuidUtil;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeDao noticeDao;

	@Override
	public String insert(Notice notice) {
		String id = UuidUtil.get32UUID();
		notice.setId(id);
		notice.setCreateTime(new Date());
		noticeDao.insert(notice);
		return id;
	}

	@Override
	public void delete(String id) {
		noticeDao.delete(id);
	}

	@Override
	public void update(Notice notice) {
		notice.setCreateTime(new Date());
		noticeDao.update(notice);
	}

	@Override
	public Notice select(String id) {
		return noticeDao.select(id);
	}

	@Override
	public List<Notice> getNoticeList(String uid) {
		return noticeDao.getNoticeList(uid);
	}

	@Override
	public List<Notice> findByParameter(Notice notice) {
		return noticeDao.findByParameter(notice);
	}

	@Override
	public void deleteNotice(String id) {
		noticeDao.deleteNotice(id);
	}


	@Override
	public Page findByParameterToPage(Notice notice, Page page) {
		if(notice.getTitle()!=null){
			notice.setTitle("%"+notice.getTitle()+"%");
		}
		page.setPageObject(notice);
		page.setTotalPage(noticeDao.findByParameterToPages(page));
		page.setBefore((page.getPage()-1)*page.getSize());
		page.setPageObject(noticeDao.findByParameterToPage(page));
		return page;
	}

	@Override
	public Notice selectone(String id) {
		return noticeDao.selectone(id);
	}

	@Override
	public List<Notice> findByParameterinhome(String remark) {
		return noticeDao.findByParameterinhome(remark);
	}

	@Override
	public List<Notice> getOwnNoticeList(String value) {
		return noticeDao.getOwnNoticeList(value);
	}

}
