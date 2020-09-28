package com.kke.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.LinkVO;
import com.kke.vo.MemberVO;
import com.kke.vo.ScrambleVO;

@Repository
public class ScrambleDAOImpl implements ScrambleDAO {
	
	@Inject
	@Resource(name="sqlSession_testraw")
	@Qualifier(value = "sqlSession_testraw")
	private SqlSession sql;
	
	@Inject
	@Resource(name="sqlSession_testScramble")
	@Qualifier(value = "sqlSession_testScramble")
	private SqlSession sqlScramble;
	
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
	
	@Inject 
	@Resource(name="sqlSession_Member")
	@Qualifier(value="dataSource_Member")
	private SqlSession sqlSession_Member;
	
	@Inject 
	@Resource(name="sqlSession_CONNECTION")
	private SqlSession sqlSession_Connection;
	
	@Inject 
	@Resource(name="sqlSession_SCRAMBLER")
	private SqlSession sqlSession_SCRAMBLER;
	
	private Map<Integer,SqlSession> sqlSession_Bank = new HashMap<>();
	// 나중에 합치자, 4 11 81 써서 하게
	
	public void clearSDb() {
		sqlSession_SCRAMBLER.delete("scrambleMapper.clearSDb");
	}
	
	public void updateDetailForDate(ApiVO vo) {
		sqlSession_SCRAMBLER.insert("scrambleMapper.insertScrambleDetailForDate",vo);
	}
	
	

	public List<String> matchAccNum(List<BoardVO> voList) {
		List<String> scAccNumList = new ArrayList<>();
		for(BoardVO vo : voList) 
			scAccNumList.add( sqlSession_Connection.selectOne("scrambleMapper.matchAccNum", vo.getAcc_num()) );
		
		return scAccNumList;	
	}
	
	
	public List<BoardVO> matchAccDeatil(List<String> scNumList, List<BoardVO> voList) {
		List<BoardVO> scBoardList = new ArrayList<>();
		List<BoardVO> scramblevoList = new ArrayList<>();
		scramblevoList.addAll(voList);
		
		for(int i = 0 ; i<voList.size();i++) {
			scramblevoList.get(i).setAcc_num(scNumList.get(i));	// acc_num을 매칭된 sc_num으로 변경
			scBoardList.add( sqlSession_SCRAMBLER.selectOne("scrambleMapper.matchAccDeatil", scramblevoList.get(i)) );
		}
		// 여기서 Error나면 scNumList에 적절한 값이 안 들어갔다는 의미 <- matchAccNum 구현에 문제 있음.
		return scBoardList;
		// 결과물로 좌우 짝이 맞지 않으면, 전반적인 점에서 문제 있음, 특히 이 메소드!	
	}
	
	
	public String searchScAccNum(String acc_num) {
		return sqlSession_Connection.selectOne("scrambleMapper.getScrambledAccNum",acc_num);
	}
	

	public int getBalance(String acc_num, int acc_code) {
		if (acc_code == 81) 
			return sqlSession_HANA.selectOne("scrambleMapper.getBalance",acc_num);
		else if(acc_code == 4) 
			return sqlSession_KB.selectOne("scrambleMapper.getBalance",acc_num);
		else 
			return sqlSession_NH.selectOne("scrambleMapper.getBalance",acc_num);
	}
	public void updateDetail(ApiVO api) {
		sqlSession_SCRAMBLER.insert("scrambleMapper.insertScrambleDetailNow",api);
	}
	public int getLastNo() {
		return sqlSession_SCRAMBLER.selectOne("scrambleMapper.getLastNo");
	}
	
	
	
	

	@Override
	public List<String> searchMember(String acc_num, int bank_code) throws Exception {
		AccInfoVO accInfoVO = new AccInfoVO();
		
		List<String> member = new ArrayList<String>();
		
		if (bank_code == 4) {
			accInfoVO = (sqlSession_KB.selectOne("scrambleMapper.searchAccInfo", acc_num));
			
		}// 국민
		else if (bank_code == 11) {
			accInfoVO = (sqlSession_NH.selectOne("scrambleMapper.searchAccInfo", acc_num));
		}// 농협
		else {
			accInfoVO = (sqlSession_HANA.selectOne("scrambleMapper.searchAccInfo", acc_num));
		}// 하나
		member.add(accInfoVO.getUser_name());
		member.add(sqlSession_Member.selectOne("scrambleMapper.searchPhoneNum",accInfoVO.getUser_RRN()) );
		// 핸드폰번호 기입
		return member;
	}
	
	@Override
	public void insertLink(LinkVO vo) throws Exception {
		sqlSession_Connection.insert("scrambleMapper.insertLink", vo);
	}
	
	@Override
	public void insertScAccNum(String acc_num, String sc_num) {
		LinkVO vo = new LinkVO();
		vo.setAcc_num(acc_num);
		vo.setSc_acc_num(sc_num);
		
		sqlSession_Connection.update("scrambleMapper.insertScAccNum", vo);
	}
	
	@Override
	public List<BoardVO> searchDetailInfo(String acc_num, int bank_code) {
		if (bank_code == 4)
			return sqlSession_KB.selectList("scrambleMapper.searchDetailInfo", acc_num);
		else if (bank_code == 11)
			return sqlSession_NH.selectList("scrambleMapper.searchDetailInfo", acc_num);
		else
			return sqlSession_HANA.selectList("scrambleMapper.searchDetailInfo", acc_num);
	}
	
	@Override
	public void insertScrableDetail(List<BoardVO> voList) {
		for(BoardVO vo : voList)
		sqlSession_SCRAMBLER.insert("scrambleMapper.insertScrambleDetail", vo);
	}
	

	public List<String> getAccounts(int bank_code) {
		if (bank_code == 4)
			return sqlSession_KB.selectList("scrambleMapper.getAccounts");
		else if (bank_code == 11)
			return sqlSession_NH.selectList("scrambleMapper.getAccounts");
		else
			return sqlSession_HANA.selectList("scrambleMapper.getAccounts");
	}
	

	public int nestAccNumChk(String acc_num) {
		return sqlSession_Connection.selectOne("scrambleMapper.nestAccNumChk",acc_num);
	}
	
	public String isScNumChk(String acc_num) {
		return sqlSession_Connection.selectOne("scrambleMapper.isScNumChk",acc_num);
	}
	public boolean nestScAccNumChk(String sc_num) {
		if( (int)sqlSession_Connection.selectOne("scrambleMapper.nestScAccNumChk",sc_num) != 0)
			return true;
		// 같은게 있다.
		else
			return false;
	}
	
	
	
	
	
	
	public void insertRaw(ScrambleVO vo) throws Exception{
		sql.insert("rawMapper.insertRaw",vo);
	}
	
	public void insertScramble(ScrambleVO vo) throws Exception {
		sqlScramble.insert("scrambleMapper.insertScramble",vo);
	}
}