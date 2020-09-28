package com.kke.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kke.vo.ApiVO;

@Repository
public class ScrambleApiDAOImpl implements ScrambleApiDAO {
	@Inject 
	@Resource(name="sqlSession_CONNECTION")
	@Qualifier(value="dataSource_CONNECTION")
	private SqlSession sqlSession_CONNECTION;
	
	@Inject 
	@Resource(name="sqlSession_SCRAMBLER")
	@Qualifier(value="dataSource_SCRAMBLER")
	private SqlSession sqlSession_SCRAMBLER;
	
	
	@Override
	public int scApiChk(ApiVO vo) throws Exception {
		int result = sqlSession_CONNECTION.selectOne("scrambleApiMapper.scApiChk", vo);
		return result;
	}
	
	@Override
	public int scPhoneChk(ApiVO vo) throws Exception {
		int result = sqlSession_CONNECTION.selectOne("scrambleApiMapper.scPhoneChk", vo);
		return result;
	}
	
	// phoneID
	@Override
	public void scPhoneId(ApiVO vo) throws Exception {
		sqlSession_CONNECTION.update("scrambleApiMapper.scPhoneId", vo); 
	}
	
	
	@Override
	public List<ApiVO> scGetAccNum(ApiVO vo) throws Exception {
		List<ApiVO> accList = new ArrayList<>();
		
		accList = sqlSession_CONNECTION.selectList("scrambleApiMapper.scGetAccNum", vo);
		
		 return accList;
	}
	
	@Override
	public int scGetWithdraw(ApiVO accNum) throws Exception {
		int sum=0;
		
		List<Integer> selectList = new ArrayList<>();
		
		selectList = sqlSession_SCRAMBLER.selectList("scrambleApiMapper.scGetWithdraw", accNum);
		
		for(int i=0; i<selectList.size(); i++)
			sum += selectList.get(i);
	
		return sum;
	}

	@Override
	public List<ApiVO> scShowApiList(ApiVO accNum) throws Exception {
		List<ApiVO> listAll = new ArrayList<>();
		
		listAll = sqlSession_SCRAMBLER.selectList("scrambleApiMapper.scShowApiList", accNum);
	
		return listAll;
	}
	
	@Override
	public ApiVO getScVO(String accNum) throws Exception {

		return sqlSession_CONNECTION.selectOne("scrambleApiMapper.getScVO", accNum);
	}
	
	@Override
	public List<ApiVO> listAll() throws Exception {
		List<ApiVO> boardList = new ArrayList<>();
	
		boardList = sqlSession_SCRAMBLER.selectList("scrambleApiMapper.listAll");
		
		Collections.sort(boardList, new Comparator<ApiVO>(){ 
            @Override
            public int compare(ApiVO o1, ApiVO o2) {
                return o2.getBank_date().compareTo(o1.getBank_date());
            }
        });
		
		return boardList;
	}

}
