package cn.com.oa.model;

public class Page implements Cloneable{
	/**
	 * 起始页数
	 */
	private Integer page=1;
	/**
	 * 每一页的条数
	 */
	private Integer size=5;
	/**
	 * 总共的数据量
	 */
	private Integer totalPage;
	/**
	 * 返回页中数据的对象
	 */
	private Object pageObject;
	/**
	 * 前一页的页数
	 */
	private Integer Before;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 
	 */
	private String ids;

	private String userids;
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getBefore() {
		return Before;
	}
	public void setBefore(Integer before) {
		Before = before;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Object getPageObject() {
		return pageObject;
	}
	public void setPageObject(Object pageObject) {
		this.pageObject = pageObject;
	}
	
	public Page clone(){ 
		Page o = null; 
			try{ 
				o = (Page)super.clone(); 
			}catch(CloneNotSupportedException e){ 
				e.printStackTrace(); 
			} 
		return o; 
	} 

}
