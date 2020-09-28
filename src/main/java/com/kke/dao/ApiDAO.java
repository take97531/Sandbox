package com.kke.dao;

import java.util.List;

import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;

public interface ApiDAO {
	public int apiChk(ApiVO vo) throws Exception;
	
	public int phoneChk(ApiVO vo) throws Exception;
	
	public void phoneId(ApiVO vo) throws Exception;
	
	public void IdRegister(ApiVO vo) throws Exception;
	
	public List<ApiVO> getAccNum(ApiVO vo) throws Exception;
	
	
	public int getWithdraw(ApiVO accNum) throws Exception;
	
	public List<ApiVO> showApiList(ApiVO accNum) throws Exception;
}
