package com.kke.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;

@Repository
public class ApiDAOImpl implements ApiDAO {
	@Inject 
	@Resource(name="sqlSession_Member")
	@Qualifier(value="dataSource_Member")
	private SqlSession sql;
	
	@Inject 
	@Resource(name="sqlSession_BANK_HANA")
	@Qualifier(value="dataSource_BANK_HANA")
	private SqlSession sqlSession_HANA;
	
	@Inject 
	@Resource(name="sqlSession_BANK_KB")
	@Qualifier(value="dataSource_BANK_KB")
	private SqlSession sqlSession_KB;
	
	@Inject 
	@Resource(name="sqlSession_BANK_NH")
	@Qualifier(value="dataSource_BANK_NH")
	private SqlSession sqlSession_NH;
	
	@Override
	public int apiChk(ApiVO vo) throws Exception {
		int result = sql.selectOne("apiMapper.apiChk", vo);
		return result;
	}
	
	@Override
	public int phoneChk(ApiVO vo) throws Exception {
		int result = sql.selectOne("apiMapper.phoneChk", vo);
		return result;
	}
	
	// phoneID
	@Override
	public void phoneId(ApiVO vo) throws Exception {
		sql.update("apiMapper.phoneId", vo); 
	}
	
	@Override
	public void IdRegister(ApiVO vo) throws Exception {
		sql.update("apiMapper.IdRegister", vo); 
	}
	
	@Override
	public List<ApiVO> getAccNum(ApiVO vo) throws Exception {
		List<ApiVO> accList = new ArrayList<>();
		
		accList = sql.selectList("apiMapper.getAccNum", vo);
		
		 return accList;
	}

	@Override
	public int getWithdraw(ApiVO accNum) throws Exception {
		int sum=0;
		
		List<Integer> selectList_HANA = new ArrayList<>();
		List<Integer> selectList_KB = new ArrayList<>();
		List<Integer> selectList_NH = new ArrayList<>();
		
		selectList_HANA = sqlSession_HANA.selectList("apiMapper.getWithdraw", accNum);
		selectList_KB = sqlSession_KB.selectList("apiMapper.getWithdraw", accNum);
		selectList_NH = sqlSession_NH.selectList("apiMapper.getWithdraw", accNum);
		
		for(int i=0; i < selectList_HANA.size(); i++)
			sum += selectList_HANA.get(i);
		for(int i=0; i < selectList_KB.size(); i++)
			sum += selectList_KB.get(i);
		for(int i=0; i < selectList_NH.size(); i++)
			sum += selectList_NH.get(i);
		
		System.out.println("DAO sum!!!!!!!!!!!!!!!! : " + sum);
		
		return sum;
	}
	
	@Override
	public List<ApiVO> showApiList(ApiVO accNum) throws Exception {
		List<ApiVO> selectList_HANA = new ArrayList<>();
		List<ApiVO> selectList_KB = new ArrayList<>();
		List<ApiVO> selectList_NH = new ArrayList<>();
		List<ApiVO> listAll = new ArrayList<>();
		
		selectList_HANA = sqlSession_HANA.selectList("apiMapper.showApiList", accNum);
		for(int i=0; i<selectList_HANA.size(); i++)
			selectList_HANA.get(i).setAcc_code(81);
		
		
		selectList_KB = sqlSession_KB.selectList("apiMapper.showApiList", accNum);
		for(int i=0; i<selectList_KB.size(); i++)
			selectList_KB.get(i).setAcc_code(4);
		
		selectList_NH = sqlSession_NH.selectList("apiMapper.showApiList", accNum);
		for(int i=0; i<selectList_NH.size(); i++)
			selectList_NH.get(i).setAcc_code(11);
	
		listAll = selectList_HANA;
		listAll.addAll(selectList_KB);
		listAll.addAll(selectList_NH);
		
		return listAll;
	}

}
