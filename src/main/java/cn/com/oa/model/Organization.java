package cn.com.oa.model;

import java.util.Date;
import java.util.List;
/**
 * 组织单位表
 * @author zlh
 *
 */
public class Organization {

	private String id;
	/**
	 * 单位名称
	 */
	private String name;
	/**
	 * 类型id
	 */
	private String typeid;
	
	/**
	 * 父id(顶层默认为0)
	 */
	private String pid;
	/**
	 * 序列号
	 */
	private Integer sort;
	/**
	 * 单位层级
	 */
	private Integer level;
	/**
	 * 联系人1
	 */
	private String contact1;
	/**
	 * 联系人2
	 */
	private String contact2;
	/**
	 * 联系人3
	 */
	private String contact3;
	/**
	 * 是否默认(0:默认   1:非默认)
	 */
	private Integer isdef;
	/**
	 * 是否启用(0:是  1:否)
	 */
	private Integer isuse;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人员
	 */
	private String cuid;
	/**
	 * 修改人员
	 */
	private String uuid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updataTime;

	// 额外属性
	
	private String orderBy;
	private List<Organization> organizationList;
	//类型名
	private String typeName;
	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdataTime() {
		return updataTime;
	}

	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getContact3() {
		return contact3;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public Integer getIsdef() {
		return isdef;
	}

	public void setIsdef(Integer isdef) {
		this.isdef = isdef;
	}

}
