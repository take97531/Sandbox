package com.kke.dao;

import java.util.List;

import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.LinkVO;
import com.kke.vo.ScrambleVO;

public interface ScrambleDAO {
	
	public void insertRaw(ScrambleVO vo) throws Exception;
	public void insertScramble(ScrambleVO vo) throws Exception;

	public List<String> searchMember(String acc_num, int bank_code) throws Exception;
	public void insertLink(LinkVO vo) throws Exception;
	public void insertScAccNum(String acc_num, String sc_num);
	public List<BoardVO> searchDetailInfo(String acc_num, int bank_code);
	public void insertScrableDetail(List<BoardVO> voList);
	public List<String> getAccounts(int i);
	public int nestAccNumChk(String acc_num);
	public String searchScAccNum(String acc_num);
	public void updateDetail(ApiVO api);
	public String isScNumChk(String acc_num);
	public boolean nestScAccNumChk(String sc_num);
	public int getLastNo();
	public int getBalance(String acc_num, int acc_code);
	public List<String> matchAccNum(List<BoardVO> voList);
	public List<BoardVO> matchAccDeatil(List<String> scNumList, List<BoardVO> voList);
	public void updateDetailForDate(ApiVO vo);
	public void clearSDb();
}
