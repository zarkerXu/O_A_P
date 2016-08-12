package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.oa.dao.MeetDao;
import cn.com.oa.model.DocMeet;
import cn.com.oa.model.Meet;
import cn.com.oa.model.Page;
import cn.com.oa.service.MeetService;
import cn.com.oa.util.UuidUtil;

@Service
public class MeetServiceImpl implements MeetService {

	@Resource
	private MeetDao meetDao;

	@Override
	public void delete(String id) {
		meetDao.delete(id);
	}

	@Override
	public Meet find(String id) {
		return meetDao.select(id);
	}

	@Override
	public List<Meet> findAll() {
		return meetDao.selectAll();
	}

	@Override
	public String insert(Meet meet) {
		String id = UuidUtil.get32UUID();
		meet.setId(id);
		meet.setCreateTime(new Date());
		meetDao.insert(meet);
		return id;
	}

	@Override
	public void update(Meet meet) {
		meet.setUpdataTime(new Date());
		meetDao.update(meet);
	}

	@Override
	public List<Meet> findByParameter(Meet meet) {
		return meetDao.findByParameter(meet);
	}

	@Override
	public Page findByParameterToPage(Meet meet, Page page) {
		if (meet.getDocNo() != null) {
			meet.setDocNo("%" + meet.getDocNo() + "%");
		}
		if (meet.getName() != null) {
			meet.setName(("%" + meet.getName() + "%"));
		}
		if (meet.getDocTitle() != null) {
			meet.setDocTitle(("%" + meet.getDocTitle() + "%"));
		}
		page.setPageObject(meet);
		page.setTotalPage(meetDao.findByParameterToPages(page));
		page.setBefore((page.getPage() - 1) * page.getSize());
		page.setPageObject(meetDao.findByParameterToPage(page));
		return page;
	}

	@Override
	public Page findByTaskToPage(Meet meet, Page page) {
		if (meet.getDocNo() != null) {
			meet.setDocNo("%" + meet.getDocNo() + "%");
		}
		if (meet.getName() != null) {
			meet.setName(("%" + meet.getName() + "%"));
		}
		if (meet.getDocTitle() != null) {
			meet.setDocTitle(("%" + meet.getDocTitle() + "%"));
		}
		page.setPageObject(meet);
		page.setTotalPage(meetDao.findByTaskToPageCount(page));
		page.setBefore((page.getPage() - 1) * page.getSize());
		page.setPageObject(meetDao.findByTaskToPage(page));
		return page;
	}

	@Override
	public Boolean isSign(String tid) {
		return meetDao.isSign(tid);
	}

	@Override
	public Page findByUserToPage(Meet meet, Page page) {
		if (meet.getDocNo() != null) {
			meet.setDocNo("%" + meet.getDocNo() + "%");
		}
		if (meet.getName() != null) {
			meet.setName(("%" + meet.getName() + "%"));
		}
		if (meet.getDocTitle() != null) {
			meet.setDocTitle(("%" + meet.getDocTitle() + "%"));
		}
		page.setPageObject(meet);
		page.setTotalPage(meetDao.findByUserToPageCount(page));
		page.setBefore((page.getPage() - 1) * page.getSize());
		page.setPageObject(meetDao.findByUserToPage(page));
		return page;
	}

	@Override
	public void copyInsert(Meet meet) {
		meet.setCreateTime(new Date());
		meetDao.copyInsert(meet);
	}

	@Override
	public Page mfindByRelayToPage(DocMeet docmeet, Page page) {
		if (docmeet.getDocNo() != null) {
			docmeet.setDocNo("%" + docmeet.getDocNo() + "%");
		}
		if (docmeet.getName() != null) {
			if (docmeet.getName().equals("")) {

			} else {
				docmeet.setName(("%" + docmeet.getName() + "%"));
				docmeet.setType(2);
			}
		}
		if (docmeet.getDocTitle() != null) {
			docmeet.setDocTitle(("%" + docmeet.getDocTitle() + "%"));
		}
		if (docmeet.getDocSummary() != null) {
			docmeet.setDocSummary(("%" + docmeet.getDocSummary() + "%"));
		}
		page.setPageObject(docmeet);
		page.setTotalPage(meetDao.mfindByRelayToPageCount(page));
		page.setBefore((page.getPage() - 1) * page.getSize());
		page.setPageObject(meetDao.mfindByRelayToPage(page));
		return page;
	}

	@Override
	public Page mfindByApproveToPage(DocMeet docmeet, Page page) {
		if (docmeet.getDocNo() != null) {
			docmeet.setDocNo("%" + docmeet.getDocNo() + "%");
		}
		if (docmeet.getName() != null) {
			if (docmeet.getName().equals("")) {

			} else {
				docmeet.setName(("%" + docmeet.getName() + "%"));
				docmeet.setType(2);
			}
		}
		if (docmeet.getDocTitle() != null) {
			docmeet.setDocTitle(("%" + docmeet.getDocTitle() + "%"));
		}
		if (docmeet.getDocSummary() != null) {
			docmeet.setDocSummary(("%" + docmeet.getDocSummary() + "%"));
		}
		page.setPageObject(docmeet);
		page.setTotalPage(meetDao.mfindByApproveToPageCount(page));
		page.setBefore((page.getPage() - 1) * page.getSize());
		page.setPageObject(meetDao.mfindByApproveToPage(page));
		return page;
	}

}
