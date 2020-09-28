package com.kke.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kke.service.ApiService;
import com.kke.service.BoardService;
import com.kke.service.ScrambleService;
import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.Criteria;
import com.kke.vo.MemberVO;
import com.kke.vo.PageMaker;
import com.kke.vo.SearchCriteria;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	@Inject
	ApiService apiService;
	@Inject
	ScrambleService scrambleService;
	
	
	Map<Integer,List<String>> mapForAllAccountFromAccInfo;

	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("writeView");
	}
	
	// 게시판 글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception{
		logger.info("write");
		
		service.write(boardVO);
		
		//return "redirect:/";
		return "redirect:/board/list";
	}

	//MemberScramble Test GET
	@RequestMapping(value = "/memberTest", method = RequestMethod.GET)
	public String memberTest(Model model, @ModelAttribute("mem") MemberVO vo, HttpSession session) throws Exception{
		logger.info("list");

		model.addAttribute("mem", vo);
		return "board/memberTest";
	}
	
	//MemberScramble Test POST
	@RequestMapping(value = "/memberTest", method = RequestMethod.POST)
	public String memberTestPost(Model model, @ModelAttribute("mem") MemberVO vo) throws Exception{
		logger.info("listPOST");
		
		MemberVO voScrambled = scrambleService.scrambleData(vo);

		model.addAttribute("mem", vo);
		model.addAttribute("scrambledList", voScrambled);
		return "board/memberTest";
	}
	
	
	// 게시판 목록 조회
		@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri, HttpSession session) throws Exception{
			logger.info("list");
			
			MemberVO vo = (MemberVO) session.getAttribute("member");
			List<BoardVO> boardList = new ArrayList<>();
			List<BoardVO> finalList = new ArrayList<>();
			
			//System.out.println("here is ");
			//System.out.println("Session in  : " + vo.toString());
			//System.out.println("scri1!!!!!!!!!!!!!!!!!!! : " + scri);
			
			if(scri.getSearchType().contains("d")) {
				if(scri.getSearchType() == "d") {
					int a = scri.getKeyword().indexOf(" ");
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					
					Date start = fm.parse(scri.getKeyword().substring(0, a));
					scri.setStart_date(start);
					
					Date end = fm.parse(scri.getKeyword().substring(a+1));
					scri.setEnd_date(end);
				}
				else {
					int a = scri.getKeyword().indexOf(" ");
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					String date = scri.getKeyword().substring(a+1);
					scri.setKeyword(scri.getKeyword().substring(0, a));
					int b = date.indexOf(" ");
					
					Date start = fm.parse(date.substring(0, b));
					scri.setStart_date(start);
					
					Date end = fm.parse(date.substring(b+1));
					scri.setEnd_date(end);
				}
			}
			//System.out.println("scri2!!!!!!!!!!!!!!!!!!! : " + scri);
			
			
			mapForAllAccountFromAccInfo = service.listAccInfo(vo.getuser_RRN());
			
			boardList = service.list(scri,mapForAllAccountFromAccInfo);
			
			if(scri.getSearchType().contains("d"))
				finalList = service.listDate(scri, boardList);
			else
				finalList.addAll(boardList);
			
			model.addAttribute("list", finalList);
			
			List<String> acc_numList = new ArrayList<>();
			for(BoardVO vo1 : finalList) 
				acc_numList.add(vo1.getAcc_num());
			
			List<BoardVO> boardVOListScramble = scrambleService.scrambledList(finalList);
			// 중복처리 해결 문제, 각 계좌세부사항을 비교해볼 방법이 없음
			// 같은 시간 분 초에 작업한 동일한 값이 있을 때 비교할 방법이 없기때문
			// + 현재 값이 1개일 때에도 못찾아온다. why?
			// 왜인지 모르겠으나 amount가 같음에도 못찾아옴, balance 0으로 되어있을떄
			//             	AND bank_amount = #{bank_amount} 빼둠 -> 그러면 제 값이 들어옴 + limit 1 제거해봄(어라? 먹힘... 뭐죠?)
			// 근데 또 안먹힐 때도 있네요 진짜 뭐죠?, 아냐 그냥 limit 1d 제거한게 저장 안되서 그래서 된듯 고로 limit 1 은 필요하다 이말이야, 이걸 하면 몇개는 중복 일어나겠지. 20 - 25초면 된다. 
			
			int i =0;
			for(BoardVO vo1 : finalList) {
				vo1.setAcc_num(acc_numList.get(i++));
			}
			model.addAttribute("scrambledList", boardVOListScramble);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(scri);
			pageMaker.setTotalCount(service.listCount(scri));

			//System.out.println("Fifth break");
			model.addAttribute("pageMaker", pageMaker);
			
			return "board/list";
			
		}

		// 게시판 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("read");
		
		model.addAttribute("read", service.read(boardVO.getBank_no()));
		model.addAttribute("scri", scri);
		return "board/readView";
	}
	
	// 게시판 수정뷰
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("updateView");
		
		model.addAttribute("update", service.read(boardVO.getBank_no()));
		model.addAttribute("scri",scri);
		
		return "board/updateView";
	}
	
	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("update");
		
		service.update(boardVO);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		logger.info("delete");
		
		service.delete(boardVO.getBank_no());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	// 게시판 조회
	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public String list2(Model model, @ModelAttribute("scri") SearchCriteria scri, HttpSession session) throws Exception{
		logger.info("list2");
		
/*
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		System.out.println("here is list2");
		System.out.println("Session in  list2: " + vo.toString());
		
		model.addAttribute("list", service.list(scri,mapForAllAccountFromAccInfo));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		*/
		return "board/list2";
		
	}
	
	
	@RequestMapping(value = "/list2_accinfo", method = RequestMethod.POST) //accinfo 쓰기
	public String write2(Model model, AccInfoVO accInfoVO) throws Exception{
		logger.info("write");
		
		//System.out.println("accInfoVO.getBank().substring(0, 4)  : " + accInfoVO.getBank().substring(0, 4) );
		//model.addAttribute("bank", accInfoVO.getBank());
		if(accInfoVO.getBank().substring(0, 4).equals("auto")) {				//3이 아니라 4까지해야 auto, ㅁㅁ은행 이렇게 된다.
			//System.out.println("acc_code : " + accInfoVO.getBank() +  "  user_name : " + accInfoVO.getUser_name() + "  number : " + Integer.parseInt(accInfoVO.getAcc_num()) );
			service.writeAccInfoAuto(accInfoVO);
		}
		else {
			//System.out.println("accInfoVO.getBank().substring(0, 4)  : " + accInfoVO.getBank().substring(0, 4) );
			service.write2(accInfoVO);
		}
		
		//return "redirect:/";
		return "redirect:/board/list2_accinfo";
	}
	
	@RequestMapping(value = "/list2_accinfo", method = RequestMethod.GET)
	public String list2_member(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list2_member");
		
		return "board/list2_accinfo";
	}
	
	@RequestMapping(value = "/list2_accdetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) //accdetailinfo쓰기
	public String write3(ApiVO api) throws Exception{
		logger.info("write3");
		//service.write3(api);
		
		if (api.getAcc_code() >= 10000) {
			//System.out.println("Date 변경으로 들어옴, Date 값 없을 때, 인석이 형이 원했던 것중 랜덤");
			api.setAcc_code(api.getAcc_code()%10000);
			scrambleService.updateDetailForDate(api);
			return "redirect:/board/list2_accdetail";
		}
		// inputed Date to Store in jsp file.
		//System.out.println("api : " + api);
		
		if(api.getAcc_num()==null||api.getBank_store()==null || api.getBank_deposit()==null) 
			return "redirect:/board/list2_accdetail";
		
		
		if(apiService.getScVO(api.getAcc_num()) == null)
			return "redirect:/board/list2_accdetail";
		
		scrambleService.updateDetail(api,api.getAcc_code());
		
		return "redirect:/board/list2_accdetail";
	}
	
	
	@RequestMapping(value = "/list2_accdetail", method = RequestMethod.GET)
	public String list2_bank(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		logger.info("list2_accdetail");
		
		model.addAttribute("list", apiService.listAll());
		
		return "board/list2_accdetail";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/list2_accdetail/check", method = RequestMethod.POST)
	public ApiVO check(ApiVO api) throws Exception {
		ApiVO vo = new ApiVO();
		
		//System.out.println("check : " + api);
		
		if(apiService.getScVO(api.getAcc_num()) == null) {
			//System.out.println("no");
			return vo;
		}
		else {	
			vo = apiService.getScVO(api.getAcc_num());
			//System.out.println("vo : " + vo);
			
			return vo;
		}
	}
	
}