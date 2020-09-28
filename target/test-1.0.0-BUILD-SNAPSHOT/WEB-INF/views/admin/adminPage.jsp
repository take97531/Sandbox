<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		 <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	 	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>

	 	<title>게시판</title>
	 	<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
		
		 <style>
            /*datepicker에서 사용한 이미지 버튼 style적용*/
            img.ui-datepicker-trigger {
                margin-left:5px; vertical-align:middle; cursor:pointer; width:25; height:25;
			}
        </style>

		<style>
 		#container { position:float; width:1800px; height:1000px; }
 		#menubar { float:left;  width:200px; height: 400px;}
 	
 		</style>
 		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<!-- common CSS -->

	
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#accinfoBtn").on("click", function(){
			location.href="list2_accinfo";
		});
		$("#accdetailBtn").on("click", function(){
			location.href="list2_accdetail";
		});

		var scrabmeObjTwo = $("form[name='ScrambleTestTwo']");
		$(".allScrambleAdd").on("click", function(){
			scrabmeObjTwo.attr("action", "/adminPage");
			scrabmeObjTwo.attr("method", "post");
			scrabmeObjTwo.submit();
		});
		
		var autoFormObj = $("form[name='autoAddForm2']");
		$(".autoAddBtn").on("click", function(){
			autoFormObj.attr("action", "/adminPage");
			autoFormObj.attr("method", "post");
			autoFormObj.submit();
		});


		
		var formObjAuto = $("form[name='autoAddInfoForm']");
		$(".autoAddInfoBtn").on("click", function(){
			formObjAuto.attr("action", "/adminPage/info");
			formObjAuto.attr("method", "post");
			formObjAuto.submit();
		});
		
		var formObj = $("form[name='addInfoForm']");
		$(".addInfoBtn").on("click", function(){
			formObj.attr("action", "/adminPage/info");
			formObj.attr("method", "post");
			formObj.submit();
		});

		
	})
     </script>

	</head>
	<body>
	<!--메뉴바 추가 부분-->

		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
			<img src="${pageContext.request.contextPath}/resources/logo.png" width="140" height="30">
	
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarsExample03">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="/"><font size="2">Home</font><span class="sr-only">(current)</span></a>
		      </li>
		      
		      <li class="nav-item">
	    	    <a class="nav-link" href="board/memberTest"><font size="2">테스트 페이지 1</font></a>
			  </li>	      
		      <li class="nav-item">
	    	    <a class="nav-link" href="board/list"><font size="2">테스트 페이지 2</font></a>
			  </li>
			     <li class="nav-item">
	    	    <a class="nav-link" href="board/list2_accdetail"><font size="2">사용자 관리 페이지</font></a>
			  </li>
			  <li class="nav-item">
	    	    <a class="nav-link" href="adminPage"><font size="2">DB 관리 페이지</font></a>
			  </li>
			  
	
		      
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">메뉴</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown03">
	          <a class="dropdown-item" href="board/memberTest">테스트 페이지 1</a>
	          <a class="dropdown-item" href="board/list">테스트 페이지 2</a>
	          <a class="dropdown-item" href="board/list2_accdetail">사용자 관리 페이지 </a>
		          <a class="dropdown-item" href="admin/adminPage">DB 관리 페이지 </a>
		        </div>
		      </li>
		    </ul>
		
		    <form class="form-inline my-2 my-md-0">
		      <input class="form-control" type="text" placeholder="Search">
		    </form>
		  </div>
		</nav>
	
	
		<div style="float:top; text-align:top;" id=menubar class="navbar navbar-expand-sm navbar-dark bg-dark">
			
			<div style="float:top">
			<a><font size="3">BANK</font></a>
			<br>
			<br>
			<a><font size="2">BANK_HANA</font></a>
			<a class="nav-link" href="/admin/BANK_HANA_AccInfo"><font size="2">AccInfo</font></a>
			<a class="nav-link" href="/admin/BANK_HANA_AccDetailInfo"><font size="2">AccDetailInfo</font></a>
			
			<a><font size="2">BANK_KB</font></a>
			<a class="nav-link" href="/admin/BANK_KB_AccInfo"><font size="2">AccInfo</font></a>
			<a class="nav-link" href="/admin/BANK_KB_AccDetailInfo"><font size="2">AccDetailInfo</font></a>
			
			<a><font size="2">BANK_NH</font></a>
			<a class="nav-link" href="/admin/BANK_NH_AccInfo"><font size="2">AccInfo</font></a>
			<a class="nav-link" href="/admin/BANK_NH_AccDetailInfo"><font size="2">AccDetailInfo</font></a>
			</div>
		
		</div>
		
		<div id="container">
				<hr />
				<br>
				<hr />
				
				<section id="container">
				
				<form action="<%= request.getContextPath() %>/admin/adminPage/info"  method="post" name="addInfoForm">
				<div class="body">
				<hr />
					
					<br>	
				<div style="border: 2px; float: left; width: 70%; margin: 6;">
							
							<table class="table table-hover" >
								<!--<tr><th>원본데이터</th></tr>  -->
																
								<tr><th>은행</th><th>사용자</th><th>계좌번호</th><th>주민등록번호</th></tr>
								<tr>
								<td>
								
					 			<div>
								    <select id="bank" name="bank" class="form-control">
								      <option value="n">-----</option>
								      <option value="하나은행">하나은행</option>
								      <option value="국민은행">국민은행</option>
								      <option value="농협은행">농협은행</option>
						   			 </select>
					    		</div>
						
								</td>
									<td><input type="text" id="user_name" name="user_name" class="chk" /></td>
									<td><input type="text" id="acc_num" name="acc_num" class="chk" /></td>
									<td><input type="text" id="user_RRN" name="user_RRN" class="chk" /></td>
									<td><button type="submit" class="btn btn-primary form-control" id="addInfoBtn">추가</button></td>
								</tr>
								
								
							</table>
							
				</div>
				</div>
				</form>
					
				<form action="<%= request.getContextPath() %>/admin/adminPage/info" method="post" name="autoAddInfoForm">
				<div class="body">
				<hr />
					
					<br>	
				<div style="border: 2px; float: left; width: 70%; margin: 6;">
							
							<table class="table table-hover" >
								<!--<tr><th>원본데이터</th></tr>  -->
																
								<tr><th>은행</th><th>사용자 아이디</th><th>계좌갯수</th><th>주민등록번호</th></tr>
								<tr>
								<td>
								
					 			<div>
								    <select id="bank" name="bank" class="form-control">
								      <option value="n">-----</option>
								      <option value="auto하나은행">하나은행</option>
								      <option value="auto국민은행">국민은행</option>
								      <option value="auto농협은행">농협은행</option>
						   			 </select>
					    		</div>
						
								</td>
									<td><input type="text" id="user_name" name="user_name" class="chk" /></td>
									<td><select id="acc_num" name="acc_num" class="form-control">
								      <option value="0">-----</option>
								      <option value="1">1</option>
								      <option value="5">5</option>
								      <option value="10">10</option>
						   			</select></td>
									<td><input type="text" id="user_RRN" name="user_RRN" class="chk" /></td>
									<td><button type="submit" class="btn btn-primary form-control" id="autoAddInfoBtn">추가</button></td>
								</tr>
								
								
							</table>
				</div>
				</div>
				</form>
				
				
				
				
				
										<!-- 자동제작용 -->
					<form method="post" name="autoAddForm2">
							<div class="body">
							<hr />
								
								<br>	
							<div style="border: 2px; float: left; width: 70%; margin: 6;">
										
										<table class="table table-hover" >
											<!--<tr><th>원본데이터</th></tr>  -->
																			
											<tr><th>은행</th><th>계좌번호</th><th>처리량 개수</th><th>Auto 제작 / 원DB에 추가</th></tr>
											<tr>
											<td>
											
								 			<div>
											    <select id="acc_code" name="acc_code" class="form-control">
											      <option value="n">-----</option>
											      <option value="181">하나은행</option>
											      <option value="104">국민은행</option>
											      <option value="111">농협은행</option>
									   			 </select>
								    		</div>
									
											</td>
												<td><input type="text" id="acc_num" name="acc_num" class="chk" /></td>
												<td>
												<select id="bank_amount" name="bank_amount" class="form-control">
											      <option value="n">-----</option>
											      <option value="1">1</option>
											      <option value="5">5</option>
											      <option value="10">10</option>
									   			 </select>
												</td>
												
												<!-- 
												<td><input type="text" id="bank_store" name="bank_store" class="chk" /></td>	
												<td><input type="text" id="bank_deposit" name="bank_deposit" class="chk" /></td>
												<td><input type="text" id="bank_amount" name="bank_amount" class="chk" /></td>
												 -->
												 
												<td><button type="submit" class="btn btn-primary form-control" id="autoAddBtn">추가</button></td>
											</tr>
											
											
										</table>
							</div>
							</div>
							</form>
			
			
										<!-- TotalScramble제작용 -->
					<form method="post" name="ScrambleTestTwo">
						<div class="body">
						<hr />
							
							<br>	
						<div style="border: 2px; float: left; width: 70%; margin: 6;">
									
									<table class="table table-hover" >
										<!--<tr><th>원본데이터</th></tr>  -->
																		
										<tr><th>은행</th><th>TotalScramble제작용</th></tr>
										<tr>
										<td>
										
							 			<div>
										    <select id="acc_code" name="acc_code" class="form-control">
										      <option value="1000">-----</option>
										      <option value="1081">하나은행</option>
										      <option value="1004">국민은행</option>
										      <option value="1011">농협은행</option>
								   			 </select>
							    		</div>
								
										</td>
											
											<td><button type="submit" class="btn btn-primary form-control" id="allScrambleAdd">모든 AccNum에 대해 Connection 진행</button></td>
										</tr>
										
										
									</table>
						</div>
						</div>
						</form>
						
				</section>
			</div>
	</body>
</html>
