package com.kke.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kke.interceptor.Auth;
import com.kke.interceptor.Auth.Role;
import com.kke.service.BoardService;
import com.kke.service.MemberService;
import com.kke.service.ScrambleService;
import com.kke.vo.AccDetailInfoVO;
import com.kke.vo.AccInfoVO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.Criteria;
import com.kke.vo.MemberVO;
import com.kke.vo.PageMaker;
import com.kke.vo.SearchCriteria;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	int change_info = 0;
	int change_detail = 0;
	
	@Inject
	MemberService service;
	@Inject
	BoardService boardservice;
	@Inject
	ScrambleService scrambleService;
	
	// �Խ��� �� �ۼ� ȭ��
	@Auth(role = Role.ADMIN)
	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public void Admin(MemberVO vo, HttpSession session) throws Exception{
		logger.info("adminpage�� ���ô�");
		logger.info(session.getId());

	}
	
	@Auth(role = Role.ADMIN)
	@RequestMapping(value = "/adminPage", method = RequestMethod.POST)
	public String write(ApiVO api) throws Exception{
		
		//System.out.println("write");
		// Scramble ALL
		if (api.getAcc_code() >= 1000) {
			//System.out.println("Scramble로 들어옴");
			scrambleService.setLinkDB();
			return "redirect:/admin/adminPage";
		}
			// Auto
		else if(api.getAcc_code() > 100) {				//3이 아니라 4까지해야 auto, ㅁㅁ은행 dlfjgrp ehlsek.
	
			//System.out.println("write_get 100대");
			api.setAcc_code(api.getAcc_code()%100);
			boardservice.writeAccDetailInfoAuto(api);
			return "redirect:/admin/adminPage";
		}
			//return "redirect:/";
		return "redirect:/adminPage";
	}
	
	@Auth(role = Role.ADMIN)
	@RequestMapping(value = "/adminPage/Info", method = RequestMethod.POST) //accinfo 쓰기
	public String write2(Model model, AccInfoVO accInfoVO) throws Exception{
		logger.info("write");
		
		//System.out.println("accInfoVO.getBank().substring(0, 4)  : " + accInfoVO.getBank().substring(0, 4) );
		//model.addAttribute("bank", accInfoVO.getBank());
		if(accInfoVO.getBank().substring(0, 4).equals("auto")) {				//3이 아니라 4까지해야 auto, ㅁㅁ은행 이렇게 된다.
			//System.out.println("acc_code : " + accInfoVO.getBank() +  "  user_name : " + accInfoVO.getUser_name() + "  number : " + Integer.parseInt(accInfoVO.getAcc_num()) );
			boardservice.writeAccInfoAuto(accInfoVO);
		}
		else {
			//System.out.println("accInfoVO.getBank().substring(0, 4)  : " + accInfoVO.getBank().substring(0, 4) );
			boardservice.write2(accInfoVO);
		}
		
		//return "redirect:/";
		return "redirect:/admin/adminPage";
	}

	
	@RequestMapping(value = "/BANK_HANA_AccInfo", method = RequestMethod.GET)
	public String View_HANA_info(AccInfoVO accInfoVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list 하나로 왔다");
		//System.out.println("list 하나로 왔다");
		change_info=1;
		int a=1;
		
		List<AccInfoVO> accInfoList = new ArrayList<>();
		accInfoList = boardservice.listAll_accInfo(a);

		model.addAttribute("list", accInfoList);
		model.addAttribute("scri", scri);
		return "admin/BANK_HANA_AccInfo";
	}
	
	@RequestMapping(value = "/BANK_HANA_AccDetailInfo", method = RequestMethod.GET)
	public String View_HANA_detail(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list");
		change_detail=1;
		int a=1;
		
		List<BoardVO> boardList = new ArrayList<>();
		boardList = boardservice.listAll(a);
		
		model.addAttribute("list", boardList);
		model.addAttribute("scri", scri);
		return "admin/BANK_HANA_AccDetailInfo";
	}
	
	
	@RequestMapping(value = "/BANK_KB_AccInfo", method = RequestMethod.GET)
	public String View_KB_info(AccInfoVO accInfoVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list  국민으로 왔다");
		//System.out.println("list  국민으로 왔다");
		change_info=2;
		int a=2;
		
		List<AccInfoVO> accInfoList = new ArrayList<>();
		accInfoList = boardservice.listAll_accInfo(a);

		model.addAttribute("list", accInfoList);
		model.addAttribute("scri", scri);
		return "admin/BANK_KB_AccInfo";
	}
	
	@RequestMapping(value = "/BANK_KB_AccDetailInfo", method = RequestMethod.GET)
	public String View_KB_detail(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list");
		change_detail=2;
		int a=2;
		
		List<BoardVO> boardList = new ArrayList<>();
		boardList = boardservice.listAll(a);
		
		model.addAttribute("list", boardList);
		model.addAttribute("scri", scri);
		return "admin/BANK_KB_AccDetailInfo";
	}
	
	
	@RequestMapping(value = "/BANK_NH_AccInfo", method = RequestMethod.GET)
	public String View_NH_info(AccInfoVO accInfoVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list  농협으로 왔다");
		//System.out.println("list  농협으로 왔다");
		change_info=3;
		int a=3;
		
		List<AccInfoVO> accInfoList = new ArrayList<>();
		accInfoList = boardservice.listAll_accInfo(a);

		model.addAttribute("list", accInfoList);
		model.addAttribute("scri", scri);
		return "admin/BANK_NH_AccInfo";
	}
	
	@RequestMapping(value = "/BANK_NH_AccDetailInfo", method = RequestMethod.GET)
	public String View_NH_detail(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("list");
		change_detail=3;
		int a=3;
		
		List<BoardVO> boardList = new ArrayList<>();
		boardList = boardservice.listAll(a);
		
		model.addAttribute("list", boardList);
		model.addAttribute("scri", scri);
		return "admin/BANK_NH_AccDetailInfo";
	}

	
	@RequestMapping(value = "/addPopup_AccInfo", method = RequestMethod.GET)
	public void View_add_AccInfo(MemberVO vo, HttpSession session) throws Exception{
		
	}
	
	@RequestMapping(value = "/addPopup_AccDetailInfo", method = RequestMethod.GET)
	public void View_add_AccDetailInfo(MemberVO vo, HttpSession session) throws Exception{
		//logger.info(session.getAttribute("qwe").toString());
	}
	
	@RequestMapping(value = "/write2_1", method = RequestMethod.POST) //accDetailinfo ����
	public String write2_1(Model model, AccDetailInfoVO accDetailInfoVO) throws Exception{
		logger.info("AccDetailInfo �߰�");
		
		boardservice.write2_1(accDetailInfoVO,change_detail);
		return "redirect:/admin/addPopup_AccDetailInfo";
	}
	
	@RequestMapping(value = "/write2_2", method = RequestMethod.POST) //accInfo ����
	public String write2_2(Model model, AccInfoVO accInfoVO) throws Exception{
		//System.out.println("������!!!!!!!!!!!!!!!! 들어감");
		boardservice.write2_2(accInfoVO, change_info);
		return "redirect:/admin/addPopup_AccInfo";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST) //accDetailinfo ����
	public String delete(Model model, AccDetailInfoVO accDetailInfoVO, HttpSession session,
		     @RequestParam("idxArr") int[] idxArr, HttpServletRequest request) throws Exception{
		  for(int i : idxArr) {   
		   //System.out.println("���� " + i);
		   boardservice.delete(i);
		  }
		  
		  String cp = request.getHeader("Referer");

			return "redirect:"+cp;
	}
	
	@RequestMapping(value = "/delete2", method = RequestMethod.POST) //accinfo ����
	public String delete2(Model model, AccInfoVO accInfoVO, HttpSession session,
		     @RequestParam("idxArr") String[] idxArr, HttpServletRequest request) throws Exception{
		//System.out.println(idxArr);
		  for(String i : idxArr) {   
		  //System.out.println("���� " + i);
		   boardservice.delete2(i);
		  }

		  String cp = request.getHeader("Referer");

			return "redirect:"+cp;
	}
	
	@Auth(role = Role.ADMIN)
	@RequestMapping(value = "/admin/adminPage/scramble.do", method = RequestMethod.POST)
	public String write3(ApiVO api) throws Exception{
		logger.info("write3");
		//service.write3(api);

		//System.out.println("write");
			// Scramble ALL
		if (api.getAcc_code() >= 1000) {
			//System.out.println("Scramble로 들어옴");
			scrambleService.setLinkDB();
			return "redirect:/admin/adminPage";
		}
			// Auto
		else if(api.getAcc_code() > 100) {				//3이 아니라 4까지해야 auto, ㅁㅁ은행 dlfjgrp ehlsek.

			//System.out.println("write_get 100대");
			api.setAcc_code(api.getAcc_code()%100);
			boardservice.writeAccDetailInfoAuto(api);
			return "redirect:/admin/adminPage";
		}
		
		return "redirect:/admin/adminPage";
	}
	
	
	
}
	
	