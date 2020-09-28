package com.kke.vo;

import java.util.Date;

public class MemberVO {

   private String user_id;
   private String user_pw;
   private String user_name;
   private Date user_date;
   
   private String user_phoneNum;
   private String user_RRN;
   private String user_job;
   private String user_phoneID;
   
   public String getuser_id() {
      return user_id;
   }
   public void setuser_id(String user_id) {
      this.user_id = user_id;
   }
   public String getuser_pw() {
      return user_pw;
   }
   public void setuser_pw(String user_pw) {
      this.user_pw = user_pw;
   }
   public String getuser_name() {
      return user_name;
   }
   public void setuser_name(String user_name) {
      this.user_name = user_name;
   }
   public Date getuser_date() {
      return user_date;
   }
   public void setuser_date(Date user_date) {
      this.user_date = user_date;
   }
   
   public String getuser_phoneNum() {
      return user_phoneNum;
   }
   public void setuser_phoneNum(String user_phoneNum) {
      this.user_phoneNum = user_phoneNum;
   }
   
   public String getuser_RRN() {
      return user_RRN;
   }
   public void setuser_RRN(String user_RRN) {
      this.user_RRN = user_RRN;
   }
   
   public String getuser_job() {
      return user_job;
   }
   public void setuser_job(String user_job) {
      this.user_job = user_job;
   }
   
   public String getuser_phoneID() {
      return user_phoneID;
   }
   public void setuser_phoneID(String user_phoneID) {
      this.user_phoneID = user_phoneID;
   }
   
   @Override
   public String toString() {
      return "MemberVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_date="
            + user_date + ", user_phoneNum=" + user_phoneNum + ", user_RRN=" + user_RRN + ", user_job=" + user_job + "]";
   }
   
}