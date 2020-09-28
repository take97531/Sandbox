package com.kke.vo;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchCriteria extends Criteria{

	private String searchType = "";
	private String keyword = "";
	private Map scri;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
   	private Date start_date;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
   	private Date end_date;
	
	public Map getScri() {
		return scri;
	}
	
	public void setScri(Map scri) {
		this.scri = scri;
	}
	 
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", scri=" + scri + ", start_date="
				+ start_date + ", end_date=" + end_date + "]";
	}
}