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
		$("#accinfoBtn").on("click", function(){
			location.href="list2_accinfo";
		})
		$("#accdetailBtn").on("click", function(){
			location.href="list2_accdetail";
		})
		

		
	})
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

			<button id="accinfoBtn" type="button" class="btn btn-default">AccInfo 관리</button>
			<button id="accdetailBtn" type="button" class="btn btn-default">AccDetailInfo 관리</button>
			
		</div>
	</body>
</html>
