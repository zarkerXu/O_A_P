package cn.com.oa.model;

import java.util.Date;
/**
 * 附件表
 * @author zlh
 *
 */
public class Attachment {

	private String id;
	/**
	 * 父id
	 */
	private String pid;
	/**
	 * 创建者
	 */
	private String cuid;
	/**
	 * 1为公文;2为会议;3为头像
	 */
	private Integer type;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件大小(kb)
	 */
	private String fileSize;
	/**
	 * 文件后缀名
	 */
	private String suffix;
	/**
	 * 文件路径
	 */
	private String url;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否启用(0:是  1:否)
	 */
	private Integer isuse;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	private String orderBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsuse() {
		return isuse;
	}
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
