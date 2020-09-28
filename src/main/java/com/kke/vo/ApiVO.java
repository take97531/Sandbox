package com.kke.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiVO {
	private String acc_num;
	private String sc_acc_num;
	private int bank_balance;
	private String bank_store;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm", timezone = "Asia/Seoul")
   	private Date bank_date;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
   	private Date start_date;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
   	private Date end_date;
   	private String bank_deposit;
   	private int bank_amount;
   	private int acc_code;
   	private int bank_no;
   	
   	private String user_name;
   	private String user_RRN;
    private String user_phoneNum;
   	private String user_phoneID;
   	
	public String getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}
	public int getBank_balance() {
		return bank_balance;
	}
	public void setBank_balance(int bank_balance) {
		this.bank_balance = bank_balance;
	}
	public String getBank_store() {
		return bank_store;
	}
	public void setBank_store(String bank_store) {
		this.bank_store = bank_store;
	}
	public Date getBank_date() {
		return bank_date;
	}
	public void setBank_date(Date bank_date) {
		this.bank_date = bank_date;
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
	public String getBank_deposit() {
		return bank_deposit;
	}
	public void setBank_deposit(String bank_deposit) {
		this.bank_deposit = bank_deposit;
	}
	public int getBank_amount() {
		return bank_amount;
	}
	public void setBank_amount(int bank_amount) {
		this.bank_amount = bank_amount;
	}
	public int getAcc_code() {
		return acc_code;
	}
	public void setAcc_code(int acc_code) {
		this.acc_code = acc_code;
	}
	public int getBank_no() {
		return bank_no;
	}
	public void setBank_no(int bank_no) {
		this.bank_no = bank_no;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_RRN() {
		return user_RRN;
	}
	public void setUser_RRN(String user_RRN) {
		this.user_RRN = user_RRN;
	}
	public String getUser_phoneNum() {
		return user_phoneNum;
	}
	public void setUser_phoneNum(String user_phoneNum) {
		this.user_phoneNum = user_phoneNum;
	}
	public String getUser_phoneID() {
		return user_phoneID;
	}
	public void setUser_phoneID(String user_phoneID) {
		this.user_phoneID = user_phoneID;
	}
	
	public String getSc_acc_num() {
		return sc_acc_num;
	}
	public void setSc_acc_num(String sc_acc_num) {
		this.sc_acc_num = sc_acc_num;
	}
	
	@Override
	public String toString() {
		return "ApiVO [acc_num=" + acc_num + ", sc_acc_num=" + sc_acc_num + ", bank_balance=" + bank_balance
				+ ", bank_store=" + bank_store + ", bank_date=" + bank_date + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", bank_deposit=" + bank_deposit + ", bank_amount=" + bank_amount
				+ ", acc_code=" + acc_code + ", bank_no=" + bank_no + ", user_name=" + user_name + ", user_RRN="
				+ user_RRN + ", user_phoneNum=" + user_phoneNum + ", user_phoneID=" + user_phoneID + "]";
	}

}
