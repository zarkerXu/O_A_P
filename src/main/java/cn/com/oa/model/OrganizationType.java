package cn.com.oa.model;

import java.util.Date;



public class OrganizationType {
	private String id;
	/**
	 * 类别的名字
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否启用（0：是 ；1：否）
	 */
	private Integer isuse;
	/**
	 * 创作者id
	 */
    private String cuid;
    /**
     * 创建时间
     */
	private Date createTime;
	/**
	 * 修改者id
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private Date updataTime;
	/**
	 * 排序
	 */
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getUpdataTime() {
		return updataTime;
	}
	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}
	

}
