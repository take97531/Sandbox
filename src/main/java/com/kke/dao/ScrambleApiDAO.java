package com.kke.dao;

import java.util.List;

import com.kke.vo.ApiVO;

public interface ScrambleApiDAO {
	public int scApiChk(ApiVO vo) throws Exception;
	
	public int scPhoneChk(ApiVO vo) throws Exception;
	
	public void scPhoneId(ApiVO vo) throws Exception;
	
	public List<ApiVO> scGetAccNum(ApiVO vo) throws Exception;
	
	public int scGetWithdraw(ApiVO accNum) throws Exception;
	
	public List<ApiVO> scShowApiList(ApiVO accNum) throws Exception;
	
	public ApiVO getScVO(String accNum) throws Exception;
	
	public List<ApiVO> listAll() throws Exception;
	
}
