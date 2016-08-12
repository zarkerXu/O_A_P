package cn.com.oa.model;

import java.util.Date;
import java.util.List;
/**
 * 用户表
 * @author zlh
 *
 */
public class User {

	private String id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 单位id
	 */
	private String oid;
	/**
	 * 特殊
	 */
	private String code;
	
	/**
	 * 电话1
	 */
	private String phone;
	/**
	 * 电话2
	 */
	private String phone2;
	/**
	 * 电话3
	 */
	private String phone3;
	/**
	 * 权限等级(0:普通人员   1:平台管理人员  2:单位管理员)
	 */
	private Integer level;
	/**
	 * 是否为默认联系人(0:是  1:否)
	 */
	private Integer ismain;
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
	private List<Object> objList;
	private String orgname;
	private String orgid;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIsmain() {
		return ismain;
	}
	public void setIsmain(Integer ismain) {
		this.ismain = ismain;
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
	public List<Object> getObjList() {
		return objList;
	}
	public void setObjList(List<Object> objList) {
		this.objList = objList;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}



}
