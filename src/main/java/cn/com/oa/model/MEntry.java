package cn.com.oa.model;

/**
 * 报名表
 * @author zlh
 *
 */
public class MEntry {

	private String id;
	/**
	 * 任务id
	 */
	private String pid;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 职位
	 */
	private String post;
	/**
	 * 性别(0:男  1:女)
	 */
	private Integer sex;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 状态(0:参会 1:听会 2:请假)
	 */
	private Integer type;
	/**
	 * 审批状态(0:审批通过,1:审批未通过,2:正在审批,3:未审批)
	 */
	private Integer passStatus;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 审批备注
	 */
	private String passRemark;
	//额外属性
	/**
	 * 组织的名字
	 */
	private String organame;
	
	private String orderBy;

	public String getOrganame() {
		return organame;
	}

	public void setOrganame(String organame) {
		this.organame = organame;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
