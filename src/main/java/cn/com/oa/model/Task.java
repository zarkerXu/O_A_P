package cn.com.oa.model;

/**
 * 任务表
 * @author zlh
 *
 */
public class Task {

	private String id;
	/**
	 * 用户id
	 */
	private String uid;
	/**
	 * 组织id
	 */
	private String oid;
	/**
	 * 公文id
	 */
	private String did;
	/**
	 * 类型(1:公文  2:会议 3:公告)
	 */
	private Integer type;
	/**
	 * 状态(0:签收 1:未签收)
	 */
	private Integer signStatus;
	/**
	 * 审批状态(0:审批通过,1:审批未通过,2:正在审批,3:未审批)
	 */
	private Integer passStatus;
	
	private String remark;
	
	private String passRemark;
	
	private String orderBy;
	
	/**
	 * 组织的名字
	 */
	private String organame;
	/**
	 * 签收者的名字
	 */
	private String uname;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Integer getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassRemark() {
		return passRemark;
	}

	public void setPassRemark(String passRemark) {
		this.passRemark = passRemark;
	}

	public String getOrganame() {
		return organame;
	}

	public void setOrganame(String organame) {
		this.organame = organame;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}
