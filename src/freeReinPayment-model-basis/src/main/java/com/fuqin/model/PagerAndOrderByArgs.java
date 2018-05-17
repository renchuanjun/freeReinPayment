package com.fuqin.model;

import java.util.List;

public class PagerAndOrderByArgs implements java.io.Serializable {
	public PagerAndOrderByArgs(){
		currentPage = 0;
		pageSize = 10;
		splitPage = true;
		sortColumn = "createOn";
		
	}
	
	private Integer currentPage;
	
	private Integer pageSize;
	
	private int totalCount;
	
	private boolean splitPage;
	
	private String sortColumn;
	
	private boolean sortOrderBy;

    private String operateBy;

    private Integer pageIndex;
    
    private String sortOrderName;

	public String getSortOrderName() {
		if (sortOrderBy) {
			sortOrderName = changeSortColumn(this.sortColumn)+" ASC";
		}
		else {
			sortOrderName = changeSortColumn(this.sortColumn)+" DESC";
		}
		return sortOrderName;
	}

	//把userName格式转为user_name的数据库格式
	private String changeSortColumn(String val){
		if (null == val) return "";
		StringBuilder newStr = new StringBuilder();
		char[] cs = val.toCharArray();
		for (char c: cs) {
			if (Character.isUpperCase(c)){
				//大写字母
				newStr.append("_");
				String s = String.valueOf(c).toLowerCase();
				newStr.append(s);
			}
			else{
				//小写字母
				newStr.append(c);
			}
		}
		return newStr.toString();
	}

	public Integer getPageIndex() {
		if(null == pageIndex)
			pageIndex = currentPage * pageSize;
		return pageIndex;
	}
	
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public boolean getSortOrderBy() {
		return sortOrderBy;
	}

	public void setSortOrderBy(boolean sortOrderBy) {
		this.sortOrderBy = sortOrderBy;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		if (currentPage > 0) {
			this.currentPage = currentPage -1;
		}
		else
		{
			this.currentPage = currentPage;
		}
		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean getSplitPage() {
		return splitPage;
	}

	public void setSplitPage(boolean splitPage) {
		this.splitPage = splitPage;
	}

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String oprateBy) {
        this.operateBy = oprateBy;
    }

	public String getSortColumn() {
		return sortColumn;
	}

}
