package com.kke.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kke.dao.BoardDAO;
import com.kke.vo.AccDetailInfoVO;
import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.Criteria;
import com.kke.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Inject
	private ScrambleService scrambleService;
	
	// 게시물 목록 조회
	@Override
	public List<BoardVO> listAll(int a) throws Exception {
		return dao.listAll(a);
	}
	@Override
	public List<AccInfoVO> listAll_accInfo(int a) throws Exception{
		return dao.listAll_accInfo(a);
	}
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}
	
	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri, Map<Integer,List<String>> mapForAllAccountFromAccInfo) throws Exception {
		return dao.list(scri, mapForAllAccountFromAccInfo);
	}
	
	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		//System.out.println("Thrid break");
		//System.out.println("Scri : "+ scri.getSearchType() + scri.getKeyword());
		return dao.listCount(scri);
	}
	
	
	// 게시물 조회
	@Override
	public BoardVO read(int bno) throws Exception {

		return dao.read(bno);
	}
	
	@Override
	public void update(BoardVO boardVO) throws Exception {

		dao.update(boardVO);
	}

	@Override
	public void delete(int bno) throws Exception {
		
		dao.delete(bno);
	}
	@Override
	public void delete2(String acc_num) throws Exception {
		
		dao.delete2(acc_num);
	}
	
	@Override
	public Map<Integer,List<String>> listAccInfo(String user_RRN) throws Exception {
		return dao.listAccInfo(user_RRN);
	}
	
	
	public void write2(AccInfoVO accInfoVO) throws Exception{
		dao.write2(accInfoVO);
	}
	
	// 잔액 Update가 되지 않은) 원 DB 계좌내역 추가 -> 사용 안함
	public void write3(ApiVO apiVO) throws Exception{
		if(apiVO.getBank_deposit().equals("출금"))
			apiVO.setBank_balance(apiVO.getBank_balance() - apiVO.getBank_amount());
		else
			apiVO.setBank_balance(apiVO.getBank_balance() + apiVO.getBank_amount());
		
		dao.write3(apiVO);
	}

	
	
	@Override
	public void writeAccInfoAuto(AccInfoVO accInfoVO) throws Exception {
		List<AccInfoVO> voList = new ArrayList<AccInfoVO>();
		String acc_code;
		
		if(accInfoVO.getBank().equals("auto하나은행"))
			acc_code = "081";
		else if (accInfoVO.getBank().equals("auto국민은행"))
			acc_code = "004";
		else 
			acc_code = "011";
		
		
		for(int i = Integer.parseInt(accInfoVO.getAcc_num()); i>0;i--) {
			AccInfoVO vo = new AccInfoVO();
			String scrambled = scrambleService.scrambleAccount(acc_code);
			
			// 중복확인
			if(dao.checkAccNum(scrambled,acc_code) == 0) {
				//System.out.println("checkAccNum : 0");
				vo.setUser_RRN(accInfoVO.getUser_RRN());
				vo.setUser_name(accInfoVO.getUser_name());
				vo.setAcc_num(scrambled);
				voList.add(vo);
			}
		}
		// 하나의 vo에 account값만 바꿀수는 없는건지 궁금하다.
		dao.writeAccInfoAuto(voList,acc_code);
	}
	
	@Override
	public void writeAccDetailInfoAuto(ApiVO api) throws Exception{	
		
		// bankAmount에 몇개의 계좌내역을 생성할건지 적었다.
		for(int i = api.getBank_amount(); i>0;i--) {
			ApiVO vo = scrambleService.createBoard();
			vo.setAcc_num(api.getAcc_num());
			vo.setAcc_code(api.getAcc_code());
			
			// 중복확인
			// 여기서 중복확인해야할 것은 이미 기록이 있다면, 해당 Balance가 얼마인지?
			vo.setBank_balance(dao.checkBalance(vo.getAcc_num(),api.getAcc_code()));
			
			if (vo.getBank_deposit().equals("입금"))
				vo.setBank_balance(vo.getBank_balance() + vo.getBank_amount());
			else
				vo.setBank_balance(vo.getBank_balance() - vo.getBank_amount());
			dao.writeAccDetailInfoAuto(vo);
		}
	}
	
	
	@Override
	public List<BoardVO> listDate(SearchCriteria scri, List<BoardVO> boardList) throws Exception {
		return dao.listDate(scri, boardList);
	}
	
	@Override
	public void write2_1(AccDetailInfoVO accDetailInfoVO, int change)  throws Exception{
		int c = change;
		
		int acc_code = 0;
		
		if(c==1)
			acc_code = 81;
		else if(c==2)
			acc_code = 4;
		else if(c==3)
			acc_code = 11;
				
		//System.out.println("\n\n\n\n\n\n\n\n test acc_code : " + acc_code);

		//System.out.println("\n\n\n\n\n\n\n\n test c : " + c);
		
		//System.out.println("AccDetailInfoVO : " + accDetailInfoVO.toString() + "change : " + change);
			
			AccDetailInfoVO vo = new AccDetailInfoVO();
			
			vo.setAcc_num(accDetailInfoVO.getAcc_num());
			vo.setBank_deposit(accDetailInfoVO.getBank_deposit());
			vo.setBank_amount(accDetailInfoVO.getBank_amount());
			vo.setBank_store(accDetailInfoVO.getBank_store());
			// 중복확인
			// 여기서 중복확인해야할 것은 이미 기록이 있다면, 해당 Balance가 얼마인지?
			vo.setBank_balance(dao.checkBalance(vo.getAcc_num(),acc_code));
			
			//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n setBalanlce : "+vo.getBank_balance());
			
			//System.out.println(vo.getBank_deposit());
			
			if (vo.getBank_deposit().equals("입금"))
				vo.setBank_balance(vo.getBank_balance() + vo.getBank_amount());
			else
				vo.setBank_balance(vo.getBank_balance() - vo.getBank_amount());
			
			//System.out.println("\n balance : " + vo.getBank_balance() + "\n Deposit : " + vo.getBank_deposit() + "\n Amount : " + vo.getBank_amount());
		
		

		dao.write2_1(vo, c);
		
		
	}
	
	@Override
	public void write2_2(AccInfoVO accInfoVO, int change) throws Exception {
		int c = change;
		//System.out.println("C모냐!!!!!!!!!!!!"+c);
		AccInfoVO vo = new AccInfoVO();
		
		vo.setUser_name(accInfoVO.getUser_name());
		vo.setAcc_num(accInfoVO.getAcc_num());
		vo.setUser_RRN(accInfoVO.getUser_RRN());
		vo.setAcc_code(accInfoVO.getAcc_code());
		dao.write2_2(vo, c);
	}

}