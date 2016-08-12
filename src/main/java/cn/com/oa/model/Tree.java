package cn.com.oa.model;

import java.util.List;

/**
 * @author zlh
 *
 */
public class Tree {

	private String id;
	/**
	 * 单位名称
	 */
	private String name;
	/**
	 * 子节点
	 */
	private List<Tree> children;
	public Tree(){
		
	}
	public Tree(Organization organization) {
		id = organization.getId();
		name = organization.getName();
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

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

}
