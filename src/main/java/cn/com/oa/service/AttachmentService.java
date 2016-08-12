package cn.com.oa.service;
import java.util.List;

import cn.com.oa.model.Attachment;


public interface AttachmentService {

	public void delete(String id);

	public Attachment find(String id);
	
	public List<Attachment> findAll();

	public String insert(Attachment attachment);

	public void update(Attachment attachment);
	
	public List<Attachment> findByParameter(Attachment attachment);
	
	

}
