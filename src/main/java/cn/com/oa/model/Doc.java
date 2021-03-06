package cn.com.oa.model;

import java.util.Date;
import java.util.List;
/**
 * 公文表
 * @author zlh
 *
 */
public class Doc {

	private String id;
	/**
	 * 公文接收单位
	 */
	private String department;
	/**
	 * 公文接收人员
	 */
	private String personnel;
	
	/**
	 * 公文等级(1:特急 2:加急 3:平急 4:特提)
	 */
	private Integer level;
	/**
	 * 公文编号
	 */
	private String docNo;
	/**
	 * 公文标题
	 */
	private String docTitle;
	/**
	 * 公文摘要
	 */
	private String docSummary;
	
	/**
	 * 签收人数
	 */
	private Integer signNum;
	/**
	 * 签收状态(0:是 1:否)
	 */
	private Integer signStatus;
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
	
	/**
	 * 已签收人员
	 */
	private String signUid;
	private String orderBy;
	private List<Object> attachmentList;
	/**
	 * 流程id
	 */
	private String tid;
	/**
	 * 
	 * 接收单位读出
	 */
	private String departmentInfo;
	/**
	 * 
	 * 发送单位读出
	 */
	private String sendDepartmentInfo;
	/**
	 * 
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
	 * 转发的意见
	 */
	private String relayRemark;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPersonnel() {
		return personnel;
	}
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
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
	public String getSignUid() {
		return signUid;
	}
	public void setSignUid(String signUid) {
		this.signUid = signUid;
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
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getDepartmentInfo() {
		return departmentInfo;
	}
	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}
	public String getSendDepartmentInfo() {
		return sendDepartmentInfo;
	}
	public void setSendDepartmentInfo(String sendDepartmentInfo) {
		this.sendDepartmentInfo = sendDepartmentInfo;
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
	public String getRelayRemark() {
		return relayRemark;
	}
	public void setRelayRemark(String relayRemark) {
		this.relayRemark = relayRemark;
	}
	
	
	
}