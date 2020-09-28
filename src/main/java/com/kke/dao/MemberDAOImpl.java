package com.kke.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kke.vo.AccConnVO;
import com.kke.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject 
	@Resource(name="sqlSession_Member")
	@Qualifier(value="dataSource_Member")
	private SqlSession sql;
	// 회원가입
	
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
	
	private List<SqlSession> sqls = new ArrayList<SqlSession>();


	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert("memberMapper.register", vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		
		return sql.selectOne("memberMapper.login", vo);
	}
	
	//서비스에서 보낸 파라미터들을 memberUpdate(MemberVO vo)에 담음
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// vo에 담긴 파라미터들은 memberMapper.xml에 memberMapper라는 namespace에 
		// 아이디가 memberUpdate인 쿼리에 파라미터들을 넣어줌.
		sql.update("memberMapper.memberUpdate", vo); 
	}
	
	// 업데이트와 마찬가지로 흐름은 같음.
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		// MemberVO에 담긴 값들을 보내줌.
		// 그럼 xml에서 memberMapper.memberDelete에 보면
		//  #{userId}, #{userPass}에 파라미터값이 매칭.
	sql.delete("memberMapper.memberDelete", vo);
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = sql.selectOne("memberMapper.passChk", vo);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = sql.selectOne("memberMapper.idChk", vo);
		return result;
	}
	
	public List<String> getAccNums(String rrn, int index) throws Exception {
		sqls.add(sqlSession_HANA);
		sqls.add(sqlSession_KB);
		sqls.add(sqlSession_NH);
		
		return sqls.get(index).selectList("memberMapper.getAccNums",rrn);
	}
	// 굳이 sql로 -> 여러 함수로 더럽히고 싶지 않아서 조금 생각하게 만듦, sql 배열 생성, 하나 국민 농협 순

	public void getIntoAccConn(List<AccConnVO> voList) throws Exception {
		for(AccConnVO vo : voList) {
			System.out.println("In DAO, VO : " + vo.getAcc_num());
			sql.insert("memberMapper.getIntoAccConn", vo);
		}
	}
}