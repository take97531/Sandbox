package com.kke.service;

import java.util.List;

import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.MemberVO;
import com.kke.vo.ScrambleVO;

public interface ScrambleService {
	public MemberVO scrambleData(MemberVO vo) throws Exception;
	
	public ScrambleVO createData() throws Exception;
	
	public ApiVO createBoard() throws Exception;
	
	public String scrambleAccount(String bank_code) throws Exception;	// BoardServiceImpl, AutoAccInfo
	
	public boolean setLinkDBTest(String acc_num, int bank_code) throws Exception;
	public int scrambleBank(String acc_num, int bank_code, int bank_no) throws Exception;
	// update부분은 나중에 관여하자 ( jobScheduler가 할 일, 이건 acc_num을 가지고 해야할 것이야!!!)
	// update할 때 sender receiver도 힘들거같은데.. -> 변경 사항 O -> <acc_num List>로 알려줌 -> Connection에서 S acc_num을 가져옴 -> 해당 정보에 하나씩 기입하기
	// 지금 test로 만드는 작업을, <acc_num List>에다가 for문으로 돌려야겠지.

	public void setLinkDB() throws Exception;

	public void updateDetail(ApiVO api, int bank_code) throws Exception;

	public List<BoardVO> scrambledList(List<BoardVO> finalList);

	public void updateDetailForDate(ApiVO api);

}
