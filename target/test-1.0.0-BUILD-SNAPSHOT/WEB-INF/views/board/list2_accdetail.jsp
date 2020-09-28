<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	 	<style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
		
		<style>
 		#container { position:float; width:1800px; height:1000px; }
 	
 		</style>
 		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<!-- common CSS -->
	</head>
	
	<script type="text/javascript">
		var scAccNum;
		var phoneID;
		
		function checkAccNum() {
			$.ajax({
				url : "/board/list2_accdetail/check",
				type : "POST",
				dataType : "json",
				data : {"acc_num" : $("#acc_num").val()},
				success : function(data) {
					if(data.sc_acc_num == null) {
						console.log(data.sc_acc_num);
						alert("해당 계좌번호가 없습니다.");
					}
					else {
						$("#addBtn").attr("value", "Y");
						alert("추가 가능한 계좌입니다.");
						console.log(data);
						scAccNum = data.sc_acc_num;
						phoneID = data.user_phoneID;
					}
				}
			})			
			
		}
	
		$(document).ready(function(){
			
			var dateFormObj = $("form[name='dateAddForm']");
			$(".dateAddBtn").on("click", function(){
				dateFormObj.attr("action", "/board/list2_accdetail");
				dateFormObj.attr("method", "post");
				dateFormObj.submit();
			});
			
			var formObj = $("form[name='addForm2']");

			$("#addBtn").on("click", function(){
				if($("#addBtn").val() == "Y") {		
					var acc_code = $("#acc_code");
					var acc_num = scAccNum;
					var bank_store = $("#bank_store");
					var bank_deposit = $("#bank_deposit");
					var bank_amount = $("#bank_amount");
					
					var acc_codeVal = acc_code.val();
					var acc_numVal = scAccNum;
					var bank_storeVal = bank_store.val();
					var bank_depositVal = bank_deposit.val();
					var bank_amountVal = bank_amount.val();
					
					var year = (new Date()).getFullYear();
					var month = (new Date()).getMonth() + 1;
					var day = (new Date()).getDate();
					var hours = (new Date()).getHours();
					var minutes =(new Date()).getMinutes();
					
					var bank_dateVal = String(year) + "-" + String(month) + "-" + String(day) + " " + String(hours) + ":" + String(minutes);
					
					var apiObject = {
							"user_phoneID" : phoneID,
							"acc_code" : acc_codeVal,
							"acc_num" : acc_numVal,
							"bank_store" : bank_storeVal,
							"bank_deposit" : bank_depositVal,
							"bank_date" : bank_dateVal
					};											
					
					console.log("phoneID : " + phoneID);
					if(phoneID != null) {
						console.log("aaaaaaaaaaaaaaaaa");
						
						$.ajax({
							url : "https://fc6a7a4d.ngrok.io/finance-inform/", //"http://localhost:8080/apis/directData",  // "/board/list2_accdetail",   
							type : "POST",
							dataType : "json",
							data :  JSON.stringify(apiObject),
							async : false,
						});
					}
				
				//formObj.attr("action", "/board/list2_accdetail");
				//formObj.attr("method", "post");
				//formObj.submit();
				}
				else {
					alert("계좌 확인버튼을 눌러주세요.");
				
				}
			});
		});	
		
		function SetBank(){
 	    	var setbank = $("#bank option:selected").val(); // title, board_title, reg_id
		}

	</script>
	
	
	
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
    	    <a class="nav-link" href="memberTest"><font size="2">테스트 페이지 1</font></a>
		  </li>	      
	      <li class="nav-item">
    	    <a class="nav-link" href="list"><font size="2">테스트 페이지 2</font></a>
		  </li>
		     <li class="nav-item">
    	    <a class="nav-link" href="list2_accdetail"><font size="2">사용자 관리 페이지</font></a>
		  </li>
		  

	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">메뉴</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown03">
	          <a class="dropdown-item" href="board/memberTest">테스트 페이지 1</a>
	          <a class="dropdown-item" href="board/list">테스트 페이지 2</a>
	          <a class="dropdown-item" href="board/list2_accdetail">사용자 관리 페이지 </a>
	        </div>
	      </li>
	    </ul>
	
	    <form class="form-inline my-2 my-md-0">
	      <input class="form-control" type="text" placeholder="Search">
	    </form>
	  </div>
	</nav>

		<div id="container">
			<a class="navbar-brand" href="#"><font size="3">사용자 관리 페이지</font></a>
			
			<hr />
			 
			<div>
				<!--<%@include file="nav.jsp" %>-->
				<li>
					<c:if test="${member != null}"><a href="/member/logout">로그아웃</a></c:if>
					<c:if test="${member == null}"><a href="/">로그인</a></c:if>
				</li>
				<li>
					<c:if test="${member != null}">
						<p>${member.user_id}님 안녕하세요.</p>
					</c:if>
				</li>
			</div>
			<br>
			<hr />
			
			<section id="container">
			
									<!-- 수동제작용 -->
				<form method="post" name="addForm2">
				<div class="body">
				<hr />
					
					<br>	
			<div style="border: 2px; float: left; width: 70%; margin: 6;">
							
							<table class="table table-hover" >
								<!--<tr><th>원본데이터</th></tr>  -->
																
								<tr><th>은행</th><th>계좌번호</th><th>가맹점</th><th>입/출금</th><th>처리금액</th></tr>
								<tr>
								<td>
								
					 			<div>
								    <select id="acc_code" name="acc_code" class="form-control">
								      <option value="n">-----</option>
								      <option value="81">하나은행</option>
								      <option value="4">국민은행</option>
								      <option value="11">농협은행</option>
						   			 </select>
						   			 
					    		</div>
						
								</td>
									<td><input type="text" id="acc_num" name="acc_num" class="chk" /></td>
									<td><input type="text" id="bank_store" name="bank_store" class="chk" /></td>
									<td><input type="text" id="bank_deposit" name="bank_deposit" class="chk" /></td>
									<td><input type="text" id="bank_amount" name="bank_amount" class="chk" /></td>
									
									<td><button type="submit" class="btn btn-primary form-control" id="addBtn" value="N">추가</button></td>
									<td><button class="check btn-primary form-control" type="button" id="check" onclick="checkAccNum();" value="N">계좌 확인</button></td>
								</tr>
								
							</table>
				</div>

				</div>
				</form>
				
										<!-- 자동제작용 -->
				<form method="post" name="dateAddForm">
				<div class="body">
				<hr />
					
					<br>	
				<div style="border: 2px; float: left; width: 70%; margin: 6;">
							
							<table class="table table-hover" >
								<!--<tr><th>원본데이터</th></tr>  -->
																
								<tr><th>은행</th><th>계좌번호</th><th>처리량 개수</th><th>날짜 입력</th><th>Auto제작 / ex) 1996-04-12 08:13 Form</th></tr>
								<tr>
								<td>
								
					 			<div>
								    <select id="acc_code" name="acc_code" class="form-control">
								      <option value="n">-----</option>
								      <option value="10081">하나은행</option>
								      <option value="10004">국민은행</option>
								      <option value="10011">농협은행</option>
						   			 </select>
					    		</div>
						
								</td>
									<td><input type="text" id="acc_num" name="acc_num" class="chk" /></td>
									<td>
									<select id="bank_amount" name="bank_amount" class="form-control">
								      <option value="1">1</option>
						   			 </select>
									</td>
									<td><input type="text" id="bank_store" name="bank_store" class="chk" /></td>
									 
									<td><button type="submit" class="btn btn-primary form-control" id="dateAddBtn">추가</button></td>
								</tr>
								
								
							</table>
				</div>
				</div>
				</form>
				
				
				
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				
				<div style="border: 2px; float: left; width: 65%; margin: 6;">
						
						<table class="table table-hover" >
							<tr><th>스크램블 데이터</th></tr>
							<tr><th>날짜</th><th>은행명</th><th>계좌번호</th><th>입/출금</th><th>처리금액</th><th>잔액</th><th>가맹점</th></tr>
							
						
							<c:forEach items="${list}" var = "list">
								<tr>
									<td><fmt:formatDate value="${list.bank_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td> 
										<c:if test="${list.acc_code == 81}">
											<c:out value="하나은행" />
										</c:if>
										<c:if test="${list.acc_code == 4}">
											<c:out value="국민은행" />
										</c:if>
										<c:if test="${list.acc_code == 11}">
											<c:out value="농협은행" />	
										</c:if>						
									</td>			
									<td><c:out value="${list.acc_num}" /></td>
									<td><c:out value="${list.bank_deposit}" /></td>
									<td><c:out value="${list.bank_amount}" /></td>
									<td><c:out value="${list.bank_balance}" /></td>
									<td><c:out value="${list.bank_store}" /></td>
									
								</tr>
							</c:forEach>
							
						</table>
					</div>
				
			</section>
			
		</div>
	</body>
</html>
