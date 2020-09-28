package com.kke.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kke.dao.ApiDAO;
import com.kke.dao.ScrambleApiDAO;
import com.kke.vo.ApiVO;

@Service
public class ApiServiceImpl implements ApiService {
	@Inject
	private ApiDAO dao;
	@Inject
	private ScrambleApiDAO scDao;
	
	
	@Override
	public int scApiChk(ApiVO vo) throws Exception {
		int result = scDao.scApiChk(vo);
		return result;
	}
	
	@Override
	public int scPhoneChk(ApiVO vo) throws Exception {
		int result = scDao.scPhoneChk(vo);
		return result;
	}
	
	@Override
	public void scPhoneId(ApiVO vo) throws Exception {
		
		//받은 vo를 DAO로 보내줌
		scDao.scPhoneId(vo);
		
	}
	
	@Override
	public List<ApiVO> scGetAccNum(ApiVO vo) throws Exception {

		return scDao.scGetAccNum(vo);
	}
	
	@Override
	public int scGetWithdraw(ApiVO accNum) throws Exception {

		return scDao.scGetWithdraw(accNum);
	}
	
	@Override
	public List<ApiVO> scShowApiList(ApiVO accNum) throws Exception {
		return scDao.scShowApiList(accNum);
	}
	
	@Override
	public ApiVO getScVO(String accNum) throws Exception {

		return scDao.getScVO(accNum);
	}
	
	@Override
	public List<ApiVO> listAll() throws Exception {
		return scDao.listAll();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int apiChk(ApiVO vo) throws Exception {
		int result = dao.apiChk(vo);
		return result;
	}
	
	@Override
	public int phoneChk(ApiVO vo) throws Exception {
		int result = dao.phoneChk(vo);
		return result;
	}
	
	@Override
	public void phoneId(ApiVO vo) throws Exception {
		
		//받은 vo를 DAO로 보내줌
		dao.phoneId(vo);
		
	}
	
	@Override
	public void IdRegister(ApiVO vo) throws Exception {
		
		//받은 vo를 DAO로 보내줌
		dao.IdRegister(vo);
	}
	
	@Override
	public List<ApiVO> getAccNum(ApiVO vo) throws Exception {

		return dao.getAccNum(vo);
	}
	
	@Override
	public int getWithdraw(ApiVO accNum) throws Exception {

		return dao.getWithdraw(accNum);
	}
	
	@Override
	public List<ApiVO> showApiList(ApiVO accNum) throws Exception {
		return dao.showApiList(accNum);
	}
}
