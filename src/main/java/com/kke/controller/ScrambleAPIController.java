package com.kke.controller;


import java.net.URI;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.kke.service.*;
import com.kke.vo.ApiVO;

@RestController
@RequestMapping("/scapis")
public class ScrambleAPIController {
	private static final Logger logger = LoggerFactory.getLogger(ScrambleAPIController.class);
	
	@Autowired
	private ApiService service;

	// 마이데이터팀 회원가입시 요청 phoneId 입력
	@RequestMapping(value="/phoneId", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json")
	public ResponseEntity<Integer> phoneId(@Validated @RequestBody ApiVO api, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("register phoneID");
		
		if(service.scApiChk(api) == 0)
			return new ResponseEntity<>(1, HttpStatus.OK);
		
		if(service.scPhoneChk(api) != 0)
			return new ResponseEntity<>(1, HttpStatus.OK);
		
		service.scPhoneId(api);
		
		URI resourceUri = uriBuilder.path("scapis/phoneId/{userNum}")
				.buildAndExpand(api.getUser_RRN())
				.encode()
				.toUri();

		ResponseEntity.created(resourceUri).build();
		
		return new ResponseEntity<>(0, HttpStatus.OK);
	}
	
	// 전월 지출금액 response/request
	@RequestMapping(value="/withdraw", method=RequestMethod.POST) 
	public ResponseEntity<Integer> phoneChk(@Validated @RequestBody ApiVO api) throws Exception {
		
		int result = service.scPhoneChk(api);
		System.out.println("result = " + result);
		
		if(result != 0) {
			int sum = 0;
			List<ApiVO> accList = service.scGetAccNum(api);
			
			for(int i =0; i<accList.size(); i++) {
				accList.get(i).setStart_date(api.getStart_date());
				accList.get(i).setEnd_date(api.getEnd_date());
			}
			
			for(int i =0; i<accList.size(); i++) {
					
				int amount = service.scGetWithdraw(accList.get(i));
	
				sum += amount;
			}
			System.out.println("sum : " + sum);
				
			return new ResponseEntity<>(sum, HttpStatus.OK);
		
		} else {
			System.out.println("No phoneId");
				
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
	}
	
	
	// 전월 지출정보 response/request
		@RequestMapping(value="/apiList", method=RequestMethod.POST) 
		public ResponseEntity<List<ApiVO>> accountInfo(@Validated @RequestBody ApiVO api) throws Exception {
			
			int result = service.scPhoneChk(api);
			System.out.println("result = " + result);
			
			List<ApiVO> boardList = new ArrayList<>();
			
			if(result != 0) {
				List<ApiVO> accList = service.scGetAccNum(api);
		
				for(int i =0; i<accList.size(); i++) {
					accList.get(i).setStart_date(api.getStart_date());
					accList.get(i).setEnd_date(api.getEnd_date());
					accList.get(i).setBank_deposit(api.getBank_deposit());
					accList.get(i).setUser_phoneID(api.getUser_phoneID());
				}
				
				for(int i=0; i<accList.size(); i++) 
					boardList.addAll(service.scShowApiList(accList.get(i)));
				
				Collections.sort(boardList, new Comparator<ApiVO>(){ 
		            @Override
		            public int compare(ApiVO o1, ApiVO o2) {
		                return o1.getBank_date().compareTo(o2.getBank_date());
		            }
		        });
				
				return new ResponseEntity<>(boardList, HttpStatus.OK);
			} else {
				System.out.println("No phoneId");
				return new ResponseEntity<>(boardList, HttpStatus.OK);
			}
		}
	
		
		
		/*
		////////////////////////////////////////////////////////////////////////////
		// 지출 금액 send test
		@RequestMapping(value="/directData", method=RequestMethod.POST) 
		public ResponseEntity<ApiVO> directAmount(@Validated @RequestBody ApiVO api) throws Exception {
			
			System.out.println("apis/directData ===> apiVO : " + api);
			
			//return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(api, HttpStatus.OK);
		}
		*/
}
