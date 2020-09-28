package com.kke.vo;

public class AccInfoVO {
	private String user_name;
	private String user_RRN;
	private String acc_num;
	private String bank;
	private String acc_code;
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return user_name;
	}
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
	public String getAcc_code() {
		return acc_code;
	}
	public void setAcc_code(String acc_code) {
		this.acc_code = acc_code;
	}
}