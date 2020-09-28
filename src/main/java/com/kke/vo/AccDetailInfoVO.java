package com.kke.vo;

public class AccDetailInfoVO {
	// amount�� date�뒗 Mapper�뿉�꽌 媛꾨떎.
	private String acc_num;
	private int bank_balance;
	private String bank_store;
	private String bank_deposit;
	private int bank_amount;
	
	public void setAcc_num(String acc_num) {

		this.acc_num = acc_num; 
	}
	public String getAcc_num() {
		return acc_num;
	}
	
	public void setBank_balance(int bank_balance) {
		this.bank_balance = bank_balance; 
	}
	public int getBank_balance() {
		return bank_balance;
	}
	
	public void setBank_store(String bank_store) {
		this.bank_store = bank_store; 
	}
	public String getBank_store() {
		return bank_store;
	}
	
	public void setBank_deposit(String bank_deposit) {
		this.bank_deposit = bank_deposit; 
	}
	public String getBank_deposit() {
		return bank_deposit;
	}
	
	public void setBank_amount(int bank_amount) {
		this.bank_amount = bank_amount; 
	}
	public int getBank_amount() {
		return bank_amount;
	}
	public String toString() {
		return "[acc_num=" + acc_num + ", bank_balance=" + bank_balance + ", bank_store=" + bank_store
				+ ", bank_deposit=" + bank_deposit
				+ ", bank_amount=" + bank_amount + "]"; 
	}
}
