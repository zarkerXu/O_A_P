package cn.com.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.oa.dao.AttachmentDao;
import cn.com.oa.model.Attachment;
import cn.com.oa.service.AttachmentService;
import cn.com.oa.util.UuidUtil;


@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {
	private AttachmentDao attachmentDao;
	
	public AttachmentDao getMonitorSettingsDao() {
		return attachmentDao;
	}

	@Resource
	public void setMonitorSettingsDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	@Override
	public void delete(String id) {
		attachmentDao.delete(id);
	}

	@Override
	public Attachment find(String id) {
		return attachmentDao.select(id);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentDao.selectAll();
	}

	@Override
	public String insert(Attachment attachment) {
		String id=UuidUtil.get32UUID();
		attachment.setId(id);
		attachment.setCreateTime(new Date());
		attachmentDao.insert(attachment);
		return id;
	}

	@Override
	public void update(Attachment attachment) {
		attachmentDao.update(attachment);
	}

	@Override
	public List<Attachment> findByParameter(Attachment attachment) {
		return attachmentDao.findByParameter(attachment);
	}

}
