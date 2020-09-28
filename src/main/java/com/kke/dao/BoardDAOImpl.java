package com.kke.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kke.vo.AccDetailInfoVO;
import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.Criteria;
import com.kke.vo.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

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
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession_HANA.insert("boardMapper.insert", boardVO);
		sqlSession_KB.insert("boardMapper.insert", boardVO);
		sqlSession_NH.insert("boardMapper.insert", boardVO);
	}
	
	public void write2(AccInfoVO accInfoVO) throws Exception { //여기서 무슨 은행인지 조건을 만들어줘야할듯!!!
		
		String bankname= accInfoVO.getBank();
		System.out.println(bankname);
		
		if(bankname.equals("하나은행"))
			sqlSession_HANA.insert("boardMapper.insert2", accInfoVO);
		else if(bankname.equals("국민은행"))
			sqlSession_KB.insert("boardMapper.insert2", accInfoVO);
		else if(bankname.equals("농협은행"))
			sqlSession_NH.insert("boardMapper.insert2", accInfoVO);
		else {
			
		}
	}
	
	public void write3(ApiVO api) throws Exception { //여기서 무슨 은행인지 조건을 만들어줘야할듯!!!
		
		int bankCode= api.getAcc_code();
		System.out.println(bankCode);
		
		if(bankCode == 81)
			sqlSession_HANA.insert("boardMapper.insert", api);
		else if(bankCode == 4)
			sqlSession_KB.insert("boardMapper.insert", api);
		else if(bankCode == 11)
			sqlSession_NH.insert("boardMapper.insert", api);
	}

	public void writeAccDetailInfoAuto(ApiVO api) throws Exception{
		
		if(api.getAcc_code() == 81) {
			sqlSession_HANA.insert("boardMapper.insertCreated", api);
		}
		else if (api.getAcc_code() == 4){
			sqlSession_KB.insert("boardMapper.insertCreated", api);
		}
		else {
			sqlSession_NH.insert("boardMapper.insertCreated", api);
		}
	}
	
	public void writeAccInfoAuto(List<AccInfoVO> voList, String acc_code) throws Exception {
		// accInfo에 RRN과 Name 들어옴 만들건 scramble뿐
		if(acc_code.equals("081")) {
			for(AccInfoVO vo : voList)
			sqlSession_HANA.insert("boardMapper.insert2", vo);
		}
		else if (acc_code.equals("004")){
			for(AccInfoVO vo : voList)
			sqlSession_KB.insert("boardMapper.insert2", vo);
		}
		else {
			for(AccInfoVO vo : voList)
			sqlSession_NH.insert("boardMapper.insert2", vo);
		}
	}
	
	@Override
	public int checkAccNum(String acc_num, String acc_code) throws Exception{
		
		if(acc_code.equals("081"))
			return sqlSession_HANA.selectOne("boardMapper.checkAccNum", acc_num);

		else if (acc_code.equals("004"))
			return sqlSession_KB.selectOne("boardMapper.checkAccNum", acc_num);
		else 
			return sqlSession_NH.selectOne("boardMapper.checkAccNum", acc_num);
	}
	
	@Override
	public int checkBalance (String acc_num, int acc_code) throws Exception {
		int res = 0;
		if(acc_code == 81) {
			if(sqlSession_HANA.selectOne("boardMapper.checkBalance", acc_num)!=null) {
				res = sqlSession_HANA.selectOne("boardMapper.checkBalance", acc_num);
				System.out.println("there is something");
			}
		}
		else if (acc_code == 4) {
			if(sqlSession_KB.selectOne("boardMapper.checkBalance", acc_num)!=null) {
				res= sqlSession_KB.selectOne("boardMapper.checkBalance", acc_num);
				System.out.println("there is something");
			}
		}
		else if (acc_code == 11) {
			if(sqlSession_NH.selectOne("boardMapper.checkBalance", acc_num)!=null) {
				res = sqlSession_NH.selectOne("boardMapper.checkBalance", acc_num);
				System.out.println("there is something");
			}
		}
		return res;
	}
	// 내역이 없으면 어떤 값이 나오는가? 0이 나오는가?
	
	
	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri, Map<Integer,List<String>> mapForAllAccountFromAccInfo) throws Exception {
		
		Map<String,Object> mapForScriANDAccounts = new HashMap<String,Object>();
		mapForScriANDAccounts.put("SearchCriteria", scri);
		
		List<BoardVO> voList = new ArrayList<BoardVO>();
		
		List<BoardVO> voListHana = new ArrayList<BoardVO>();
		for(int i =0; i<mapForAllAccountFromAccInfo.get(0).size();i++) {
			mapForScriANDAccounts.put("acc_num", mapForAllAccountFromAccInfo.get(0).get(i));
			System.out.println("mapForScriANDAccounts : " + mapForScriANDAccounts.get("SearchCriteria").toString() + "\n Second : " + mapForScriANDAccounts.get("acc_num").toString());
			voListHana.addAll(sqlSession_HANA.selectList("boardMapper.listPageTest",mapForScriANDAccounts));
		}
		
		List<BoardVO> voListKb = new ArrayList<BoardVO>();
		for(int i =0; i<mapForAllAccountFromAccInfo.get(1).size();i++) {
			mapForScriANDAccounts.put("acc_num", mapForAllAccountFromAccInfo.get(1).get(i));
			voListKb.addAll(sqlSession_KB.selectList("boardMapper.listPageTest",mapForScriANDAccounts));
		}
		
		List<BoardVO> voListNh = new ArrayList<BoardVO>();
		for(int i =0; i<mapForAllAccountFromAccInfo.get(2).size();i++) {
			mapForScriANDAccounts.put("acc_num", mapForAllAccountFromAccInfo.get(2).get(i));
			voListNh.addAll(sqlSession_NH.selectList("boardMapper.listPageTest",mapForScriANDAccounts));
		}
		
		for(int i = 0; i<voListHana.size();i++) 
			voListHana.get(i).setAcc_code(81);

		for(int i = 0; i<voListKb.size();i++) 
			voListKb.get(i).setAcc_code(4);

		for(int i = 0; i<voListNh.size();i++) 
			voListNh.get(i).setAcc_code(11);
		
		if(scri.getSearchType().equals("tc") || scri.getSearchType().equals("tcd")) {
			if(scri.getKeyword().contains("하나") || scri.getKeyword().toUpperCase().contains("HANA")) {
				voList.addAll(voListHana);
			}
			else if(scri.getKeyword().contains("농협") || scri.getKeyword().toUpperCase().contains("NH")) {
				voList.addAll(voListNh);
			}
			else if(scri.getKeyword().contains("국민") || scri.getKeyword().toUpperCase().contains("KB")) {
				voList.addAll(voListKb);
			}
		}
		else {
			voList.addAll(voListHana);
			voList.addAll(voListKb);
			voList.addAll(voListNh);
		}
		
		Collections.sort(voList, new Comparator<BoardVO>(){ 
			@Override
            public int compare(BoardVO o1, BoardVO o2) {
                return o1.getBank_no() > o2.getBank_no() ? -1 : o1.getBank_no() < o2.getBank_no() ? 1:0;
            }
        });

		return voList;
	}
	
	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		
		int count;
		count = sqlSession_HANA.selectOne("boardMapper.listCount", scri);
		count += (int)(sqlSession_KB.selectOne("boardMapper.listCount", scri));
		count += (int)(sqlSession_NH.selectOne("boardMapper.listCount", scri));
		

		System.out.println("Fourth break");
		
		return count;
	}
	
	// 게시물 조회
	@Override
	public BoardVO read(int bno) throws Exception {
			
		return sqlSession_HANA.selectOne("boardMapper.read", bno);
	}
	// read가 뭐
	
	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {

		sqlSession_HANA.update("boardMapper.update", boardVO);
		sqlSession_KB.update("boardMapper.update", boardVO);
		sqlSession_NH.update("boardMapper.update", boardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		
		sqlSession_HANA.delete("boardMapper.delete", bno);
		sqlSession_KB.delete("boardMapper.delete", bno);
		sqlSession_NH.delete("boardMapper.delete", bno);
		
	}
	
	@Override
	public void delete2(String acc_num) throws Exception{
		sqlSession_HANA.delete("boardMapper.delete2", acc_num);
		sqlSession_KB.delete("boardMapper.delete2", acc_num);
		sqlSession_NH.delete("boardMapper.delete2", acc_num);
	}


	//AccInfo에서 해당하는 계좌번호 담아오기
	@Override
	public Map<Integer,List<String>> listAccInfo (String user_RRN) throws Exception {
		
		List<String> listAll = new ArrayList<String>();
		
		Map<Integer,List<String>> mapListAll = new HashMap<Integer,List<String>>();
		
		
		List<String> listHana = sqlSession_HANA.selectList("boardMapper.listAccInfo",user_RRN);
		List<String> listKb = sqlSession_KB.selectList("boardMapper.listAccInfo",user_RRN);
		List<String> listNH = sqlSession_NH.selectList("boardMapper.listAccInfo",user_RRN);

		mapListAll.put(0, listHana);
		mapListAll.put(1, listKb);
		mapListAll.put(2, listNH);

		List<String> list1 = mapListAll.get(0);
		List<String> list2 = mapListAll.get(1);
		List<String> list3 = mapListAll.get(2);

		for(int i = 0; i<list1.size();i++)
			System.out.println("하나은행 Account Number : " + list1.get(i));
		for(int i = 0; i<list2.size();i++)
			System.out.println("국민은행 Account Number : " + list2.get(i));
		for(int i = 0; i<list3.size();i++)
			System.out.println("농협 Account Number : " + list3.get(i));
		
		return mapListAll;
	}
	
	@Override
	public List<BoardVO> listDate(SearchCriteria scri, List<BoardVO> boardList) throws Exception {
		int h=0;
		int k=0;
		int n=0;
		List<BoardVO> finalList = new ArrayList<>();
		
		for(int i=0; i<boardList.size(); i++) {
			if(boardList.get(i).getAcc_code() == 81)
				h++;
			else if(boardList.get(i).getAcc_code() == 4)
				k++;
			else if(boardList.get(i).getAcc_code() == 11)
				n++;
			
			boardList.get(i).setStart_date(scri.getStart_date());
			boardList.get(i).setEnd_date(scri.getEnd_date());
		}
		
		List<BoardVO> finalListH = new ArrayList<>();
		List<BoardVO> finalListK = new ArrayList<>();
		List<BoardVO> finalListN = new ArrayList<>();
		
		for(int i=0; i<boardList.size(); i++) {
			finalListH.addAll(sqlSession_HANA.selectList("boardMapper.listDate", boardList.get(i)));
			finalListK.addAll(sqlSession_KB.selectList("boardMapper.listDate", boardList.get(i)));
			finalListN.addAll(sqlSession_NH.selectList("boardMapper.listDate", boardList.get(i)));
		}
		
		for(int i=0; i<finalListH.size(); i++) {
			finalListH.get(i).setAcc_code(81);
			finalListH.get(i).setBank_name("하나은행");
		}
		for(int i=0; i<finalListK.size(); i++) {
			finalListK.get(i).setAcc_code(4);
			finalListK.get(i).setBank_name("국민은행");
		}
		for(int i=0; i<finalListN.size(); i++) {
			finalListN.get(i).setAcc_code(11);
			finalListN.get(i).setBank_name("농협은행");
		}
		
		finalList.addAll(finalListH);
		finalList.addAll(finalListK);
		finalList.addAll(finalListN);
		
		Collections.sort(finalList, new Comparator<BoardVO>(){ 
			@Override
            public int compare(BoardVO o1, BoardVO o2) {
                return o1.getBank_no() > o2.getBank_no() ? -1 : o1.getBank_no() < o2.getBank_no() ? 1:0;
            }
        });
		
		return finalList;
	}
	

	@Override
	   public List<BoardVO> listAll(int a) throws Exception {
	      List<BoardVO> boardList = new ArrayList<>();
	      if(a==1) {
	    	  boardList = sqlSession_HANA.selectList("boardMapper.listAll");
	    	  for(int i=0; i<boardList.size(); i++) {
	 	         boardList.get(i).setAcc_code(81);
	 	         boardList.get(i).setBank_name("하나은행");
	 	      }
	      }
	      else if(a==2) {
	    	  boardList = sqlSession_KB.selectList("boardMapper.listAll");
	    	  for(int i=0; i<boardList.size(); i++) {
	 	         boardList.get(i).setAcc_code(4);
	 	         boardList.get(i).setBank_name("국민은행");
	 	      }
	      }
	      else if(a==3) {
	    	  boardList = sqlSession_NH.selectList("boardMapper.listAll");
	    	  for(int i=0; i<boardList.size(); i++) {
	 	         boardList.get(i).setAcc_code(11);
	 	         boardList.get(i).setBank_name("농협은행");
	 	      }
	      }
	      
	       
	      Collections.sort(boardList, new Comparator<BoardVO>(){ 
	            @Override
	            public int compare(BoardVO o1, BoardVO o2) {
	                return o2.getBank_date().compareTo(o1.getBank_date());
	            }
	        });
	      
	      return boardList;
	   }
	@Override
	public List<AccInfoVO> listAll_accInfo(int a) throws Exception {
	      List<AccInfoVO> accInfoList = new ArrayList<>();
	      if(a==1) {
	    	  accInfoList = sqlSession_HANA.selectList("boardMapper.listAll_accInfo");	   
	      }
	      else if(a==2){
	    	  accInfoList = sqlSession_KB.selectList("boardMapper.listAll_accInfo");
	      }
	      else if(a==3) {
	    	  accInfoList = sqlSession_NH.selectList("boardMapper.listAll_accInfo");
	      }
	      
	      return accInfoList;
	   }
	
	public void write2_1(AccDetailInfoVO accDetailInfoVO, int change) throws Exception { 
		
		int c= change;
		if(c==1) {
			sqlSession_HANA.insert("boardMapper.insert", accDetailInfoVO);
		}
		else if(c==2) {
			sqlSession_KB.insert("boardMapper.insert", accDetailInfoVO);
		}
		else if(c==3) {
			sqlSession_NH.insert("boardMapper.insert", accDetailInfoVO);
		}
		
	}
	
	public void write2_2(AccInfoVO accInfoVO, int change) throws Exception { 
		
		int c= change;
		if(c==1) {
			sqlSession_HANA.insert("boardMapper.insert2", accInfoVO);
		}
		else if(c==2) {
			sqlSession_KB.insert("boardMapper.insert2", accInfoVO);
		}
		else if(c==3) {
			sqlSession_NH.insert("boardMapper.insert2", accInfoVO);
		}
		
	}
	
}
// delete, insert , (Read는 불확실)는 어떤 정보를 없앨것인지 구분을 잘해서 넣어줘야할 것같은데
// 그냥 다 넣거나 지우거나 하는 방식이다.
// 고로 crud할 정보를 구분해줘야할것같은데
