package cn.com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oa.model.Attachment;



@Repository
public interface AttachmentDao {

	public Attachment select(String id);
	
	public List<Attachment> findByParameter(Attachment attachment);
	
	public void insert(Attachment monitorSettings);

	public void update(Attachment monitorSettings);

	public void delete(String id);

	public List<Attachment> selectAll();
	
}
