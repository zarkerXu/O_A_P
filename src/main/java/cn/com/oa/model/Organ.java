package cn.com.oa.model;

public class Organ {
	/**
	 * 常用单位组id
	 */
	private String id;
	/**
	 * 用户的id
	 */
	private String uid;
	/**
	 * 常用单位组中的单位id的集合
	 */
	private String value;
	/**
	 * 常用单位组的名字
	 */
	private String name;
	/**
	 * 常用单位组的序号
	 */
	private Integer sort;
	/**
	 * 常用单位组中单位名字的集合
	 */
	private String departmentName;
	/**
	 * 常用单位属于的功能；0：属于公文和会议
	 * 				1：属于公告
	 * @return
	 */
	private Integer type;

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	

}
