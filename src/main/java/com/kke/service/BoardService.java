package com.kke.service;

import java.util.List;
import java.util.Map;

import com.kke.vo.AccDetailInfoVO;
import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.Criteria;
import com.kke.vo.SearchCriteria;

public interface BoardService {

	// 寃뚯떆湲� �옉�꽦
	public void write(BoardVO boardVO) throws Exception;
	
	// 寃뚯떆臾� 紐⑸줉 議고쉶
		public List<BoardVO> list(SearchCriteria scri, Map<Integer,List<String>> mapForAllAccountFromAccInfo) throws Exception;
		
	// 寃뚯떆臾� 珥� 媛��닔
	public int listCount(SearchCriteria scri) throws Exception;
	
	// 寃뚯떆臾� 紐⑸줉 議고쉶
	public BoardVO read(int bno) throws Exception;
	
	// 寃뚯떆臾� �닔�젙
	public void update(BoardVO boardVO) throws Exception;
	
	// 寃뚯떆臾� �궘�젣
	public void delete(int bno) throws Exception;
	public void delete2(String acc_num) throws Exception;
	
	//AccInfo�뿉�꽌 �빐�떦�븯�뒗 怨꾩쥖踰덊샇 �떞�븘�삤湲�
	public Map<Integer,List<String>> listAccInfo(String user_RRN) throws Exception;
	
	public void write2(AccInfoVO accInfoVO) throws Exception;
	public void write3(ApiVO apiVO) throws Exception;
	
	public void writeAccInfoAuto(AccInfoVO accInfoVO) throws Exception;
	public void writeAccDetailInfoAuto(ApiVO vo) throws Exception;
	
	public List<BoardVO> listDate(SearchCriteria scri, List<BoardVO> boardList) throws Exception;
	
	public List<BoardVO> listAll(int a) throws Exception;
	public List<AccInfoVO> listAll_accInfo(int a) throws Exception;
	
	public void write2_1(AccDetailInfoVO accDetailInfoVO, int change)  throws Exception;
	public void write2_2(AccInfoVO accInfoVO, int change) throws Exception;
	
	
	
}