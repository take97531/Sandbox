package com.kke.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardVO {
	
	private String acc_num;
	private int bank_balance;
   private String bank_store;
   @JsonFormat(pattern="yyyy-MM-dd hh:mm", timezone = "Asia/Seoul")
   private Date bank_date;
   private String bank_deposit;
   private int bank_no;
   private int bank_amount;
   private String bank_name;
   private int acc_code;
   
   @JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
  	private Date start_date;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
  	private Date end_date;
   
   public void setBank_name(String bank_name) {
	   this.bank_name = bank_name;
   }
   public String getBank_name() {
	   return bank_name;
   }

   public String getAcc_num() {
		return acc_num;
	}
	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}
	public int getBank_balance() {
		return bank_balance;
	}
	public void setBank_balance(int i) {
		this.bank_balance = i;
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
	public String getBank_deposit() {
		return bank_deposit;
	}
	public void setBank_deposit(String bank_deposit) {
		this.bank_deposit = bank_deposit;
	}
	public int getBank_no() {
		return bank_no;
	}
	public void setBank_no(int bank_no) {
		this.bank_no = bank_no;
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
	
	@Override
	public String toString() {
		return "BoardVO [acc_num=" + acc_num + ", bank_balance=" + bank_balance + ", bank_store=" + bank_store
				+ ", bank_date=" + bank_date + ", bank_deposit=" + bank_deposit + ", bank_no=" + bank_no
				+ ", bank_amount=" + bank_amount + ", bank_name=" + bank_name + ", acc_code=" + acc_code
				+ ", start_date=" + start_date + ", end_date=" + end_date + "]";
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


}