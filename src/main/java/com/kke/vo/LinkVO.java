package com.kke.vo;

public class LinkVO {
	private String user_name;
	private String acc_num;
	private String user_phoneNum;
	private String user_phoneID;
	private int acc_code;
	private String sc_acc_num;
	
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}
	public String getAcc_num() {
		return acc_num;
	}
	public String getUser_phoneNum() {
	      return user_phoneNum;
   }
   public void setUser_phoneNum(String user_phoneNum) {
      this.user_phoneNum = user_phoneNum;
   }
   public String getuser_phoneID() {
	      return user_phoneID;
   }
   public void setuser_phoneID(String user_phoneID) {
      this.user_phoneID = user_phoneID;
   }
	public void setAcc_code(int acc_code) {
		this.acc_code = acc_code;
	}
	public int getAcc_code() {
		return acc_code;
	}
	public String getSc_acc_num() {
		return sc_acc_num;
	}
	public void setSc_acc_num(String sc_acc_num) {
		this.sc_acc_num = sc_acc_num;
	}
}
