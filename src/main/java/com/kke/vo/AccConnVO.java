package com.kke.vo;

public class AccConnVO {
	private String user_RRN;
	private String user_phoneID;
	private int acc_code;
	private String acc_num;
	
	public void setUser_RRN(String user_RRN) {
		this.user_RRN = user_RRN; 
	}
	public String getUser_RRN() {
		return user_RRN;
	}
	
	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}
	public String getAcc_num() {
		return acc_num;
	}
	
	public String getUser_phoneID() {
	      return user_phoneID;
	}
	public void setUser_phoneID(String user_phoneID) {
      this.user_phoneID = user_phoneID;
	}
	
	public void setAcc_code(int acc_code) {
		this.acc_code = acc_code;
	}
	public int getAcc_code() {
		return acc_code;
	}
}
// vo 그만 만들고싶다