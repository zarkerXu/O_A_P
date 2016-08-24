package cn.com.oa.model;

import java.util.Date;
import java.util.List;
/**
 * 会议表
 * @author zlh
 *
 */
public class Meet {


	private String id;
	/**
	 * 会议名称
	 */
	private String name;
	/**
	 * 会议时间
	 */
	private Date meetTime;
	
	
	/**
	 * 会议承办单位
	 */
	private String meetCompany;
	/**
	 * 接收单位
	 */
	private String personnel;
	private String department;
	/**
	 * 公文等级(1:特急 2:加急 3:平急 4:特提)
	 */
	private Integer level;
	/**
	 * 发文编号
	 */
	private String docNo;
	/**
	 * 发文标题
	 */
	private String docTitle;
	/**
	 * 发文摘要
	 */
	private String docSummary;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 会议地址
	 */
	private String addr;
	/**
	 * 签收人数
	 */
	private Integer signNum;
	/**
	 * 签收(0:是  1:否)
	 */
	private Integer signStatus;
	/**
	 * 审核状态(0:审批通过,1:审批未通过,2:待审批,3:未审批)
	 */
	private Integer passStatus;
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
	private List<Object> attachmentList;
	
	private String meetTimeString;
	/**
	 * 发送单位名称
	 */
	private String sendDepartmentInfo;
	/**
	 * 接收单位名称
	 */
	private String departmentInfo;
	/**
	 * 会议承办单位名称
	 */
	private String meetCompanyName;
	
	/**
	 * 流程id
	 */
	private String tid;
	
	 /** 
	 * 签收人的个数
	 */
	private String didtask;
	/**
	 * 最初文件的id
	 * @return
	 */
	private String oldid;
	/**
	 * 审批状态（0：已经审计；1:未审计）
	 * @return
	 */
	private String isPass;
	/**
	 * 审核的人员数目
	 * @return
	 */
	private String signUid;
	
	/**
	 * 转发的意见
	 */
	private String relayRemark;

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

	public Date getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(Date meetTime) {
		this.meetTime = meetTime;
	}

	public String getMeetCompany() {
		return meetCompany;
	}

	public void setMeetCompany(String meetCompany) {
		this.meetCompany = meetCompany;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocSummary() {
		return docSummary;
	}

	public void setDocSummary(String docSummary) {
		this.docSummary = docSummary;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getSignNum() {
		return signNum;
	}

	public void setSignNum(Integer signNum) {
		this.signNum = signNum;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public Integer getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
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

	public List<Object> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Object> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getMeetTimeString() {
		return meetTimeString;
	}

	public void setMeetTimeString(String meetTimeString) {
		this.meetTimeString = meetTimeString;
	}

	public String getSendDepartmentInfo() {
		return sendDepartmentInfo;
	}

	public void setSendDepartmentInfo(String sendDepartmentInfo) {
		this.sendDepartmentInfo = sendDepartmentInfo;
	}

	public String getDepartmentInfo() {
		return departmentInfo;
	}

	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}

	public String getMeetCompanyName() {
		return meetCompanyName;
	}

	public void setMeetCompanyName(String meetCompanyName) {
		this.meetCompanyName = meetCompanyName;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getDidtask() {
		return didtask;
	}

	public void setDidtask(String didtask) {
		this.didtask = didtask;
	}

	public String getOldid() {
		return oldid;
	}

	public void setOldid(String oldid) {
		this.oldid = oldid;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getSignUid() {
		return signUid;
	}

	public void setSignUid(String signUid) {
		this.signUid = signUid;
	}

	public String getRelayRemark() {
		return relayRemark;
	}

	public void setRelayRemark(String relayRemark) {
		this.relayRemark = relayRemark;
	}

	


}
