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
		$(document).ready(function(){
			var formObjAuto = $("form[name='autoAddForm']");
			$(".autoAddBtn").on("click", function(){
				formObjAuto.attr("action", "/board/list2_accinfo");
				formObjAuto.attr("method", "post");
				formObjAuto.submit();
			});
			
			var formObj = $("form[name='addForm']");
			$(".addbtn").on("click", function(){
				formObj.attr("action", "/board/list2_accinfo");
				formObj.attr("method", "post");
				formObj.submit();
			});

		})
		
		
		
		
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
    	    <a class="nav-link" href="list2/list2_accdetail"><font size="2">사용자 관리 페이지</font></a>
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
				
				<form method="post" name="addForm">
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
									<td><button type="submit" class="btn btn-primary form-control" id="addBtn">추가</button></td>
								</tr>
								
								
							</table>
							
				</div>
				</div>
				</form>
					
				<form method="post" name="autoAddForm">
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
									<td><button type="submit" class="btn btn-primary form-control" id="autoAddBtn">추가</button></td>
								</tr>
								
								
							</table>
							
				</div>
				</div>
				</form>
				
			</section>
		</div>
	</body>
</html>
