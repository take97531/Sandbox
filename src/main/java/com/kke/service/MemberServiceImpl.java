package com.kke.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kke.dao.MemberDAO;
import com.kke.vo.AccConnVO;
import com.kke.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);
		
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//Controller에서 보내는 파라미터들을 memberUpdate(MemberVO vo)로 받고
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		
		//받은 vo를 DAO로 보내줌
		dao.memberUpdate(vo);
		
	}
	
	// 업데이트에서 처리한 내용과 같음
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = dao.passChk(vo);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}
	

	@Override
	public int inputToConn(String rrn) throws Exception {

		try {
		
		List<String> list = new ArrayList<String>();
		List<AccConnVO> voList = new ArrayList<AccConnVO>();
		
		int[] acc_code = {81,4,11};							//교체필요 하나 국민 농협
		for(int i = 0; i<3;i++) {

			//System.out.println("MemberServiceImpl Break Point 0");
			
			list.addAll(dao.getAccNums(rrn,i));

			//System.out.println("MemberServiceImpl Break Point 1");
			//vo.setAcc_num("-1");					// 계좌없을 것 대비
				for(int j=0;j<list.size();j++) {
					//System.out.println("MemberServiceImpl Break Point 2");
					AccConnVO vo = new AccConnVO();
					vo.setUser_RRN(rrn);
					vo.setAcc_code(acc_code[i]);
					vo.setAcc_num(list.get(j));
					voList.add(vo);
				}
	

				//System.out.println("MemberServiceImpl Break Point 3");
				for(String a : list) {
					//System.out.println("In List, acc_code : " + acc_code);
					//System.out.println("In List, Acc_num : " + a + "\n");
				}
				list.clear();

				//System.out.println("MemberServiceImpl Break Point 4");
			}
			if(voList.size() != 0) {
				for(AccConnVO vo : voList) 
					//System.out.println("In VO LIST : " + vo.getAcc_code());
				dao.getIntoAccConn(voList);
			}
		}
		catch(Exception e) {
			//System.out.println("ERROR!!!" + e.getStackTrace());
		}

			System.out.println("MemberServiceImpl Break Point 5");
		// 테스트하려면 은행이 필요했다.
		// 테스트 예상 문제 1. vo를 넣어서 vo가 초기화되면 어쩌냐
		// 테스트 예상 문제 2. removeAll문 저게 됨?
		// 

		//System.out.println("\n MemberServiceImpl Break Point 6");
		//System.out.println(" \n\n\n\n\n\n\n\n\n 왜안먹어 \n \n 일단 됐음 데이터 select와 저장문 뺴곤 다 됨, ex_ SqlSession List, \n");
		return 0;
	}
}