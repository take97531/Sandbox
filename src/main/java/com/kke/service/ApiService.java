package com.kke.service;

import java.util.List;

import com.kke.vo.ApiVO;

public interface ApiService {
	public int scApiChk(ApiVO vo) throws Exception;
	
	public int scPhoneChk(ApiVO vo) throws Exception;
	
	public void scPhoneId(ApiVO vo) throws Exception;
	
	public List<ApiVO> scGetAccNum(ApiVO vo) throws Exception;
	
	public int scGetWithdraw(ApiVO accNum) throws Exception;
	
	public List<ApiVO> scShowApiList(ApiVO accNum) throws Exception;
	
	public ApiVO getScVO(String accNum) throws Exception;
	
	public List<ApiVO> listAll() throws Exception;
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public int apiChk(ApiVO vo) throws Exception;
	
	public int phoneChk(ApiVO vo) throws Exception;
	
	public void phoneId(ApiVO vo) throws Exception;
	
	public void IdRegister(ApiVO vo) throws Exception;
	
	public List<ApiVO> getAccNum(ApiVO vo) throws Exception;
	
	public int getWithdraw(ApiVO accNum) throws Exception;
	
	public List<ApiVO> showApiList(ApiVO accNum) throws Exception;
}
