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
		})
		$("#accdetailBtn").on("click", function(){
			location.href="list2_accdetail";
		})
		$('.check-all').click(function(){
				$('.check-one').prop('checked',this.checked);
		})
		
		
		$("input:checkbox[name='all_check']").bind('click', function(){
			if($(this).is(":checked") ==true ){
				$("input:checkbox[name='idxArr']").each(function(){
					$(this).attr("checked", true);

				});

			}else {

				$("input:checkbox[name='idxArr']").each(function(){
					$(this).attr("checked", false);

				});
			}

		});



		var formObj = $("form[role='form']");
		$('#popupBtn').on("click",function(){
			 var url="addPopup_AccInfo"
			 var name="addPopup_AccInfo"
			var option="width=350 height=350 left=100 top=50 location=no";
				window.open(url,name,option)
			})

			
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
    	    <a class="nav-link" href="list"><font size="2">테스트 페이지 1</font></a>
		  </li>	      
	      <li class="nav-item">
    	    <a class="nav-link" href="list"><font size="2">테스트 페이지 2</font></a>
		  </li>
		     <li class="nav-item">
    	    <a class="nav-link" href="list2"><font size="2">사용자 관리 페이지</font></a>
		  </li>
		  <li class="nav-item">
    	    <a class="nav-link" href="adminPage"><font size="2">DB 관리 페이지</font></a>
		  </li>

	      
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">메뉴</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown03">
	          <a class="dropdown-item" href="board/list">테스트 페이지 1</a>
	          <a class="dropdown-item" href="board/list">테스트 페이지 2</a>
	          <a class="dropdown-item" href="board/list2">사용자 관리 페이지 </a>
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
	
	<div >
		<div>
		<h2>&nbsp;&nbsp;농협은행 AccInfo</h2>
		</div>
		
		<div style="border: 2px; float: left; width: 70%; margin: 10;">
					<form action="/admin/delete2" method="post">
						
						<button type="button" id='popupBtn' class="btn btn-success" style="margin-left:10">추가</button>
						<button type="submit" id='deleteBtn' class="btn btn-danger">삭제</button>
						
						<table class="table table-hover" >
							<tr><th><input type="checkbox" name="all_check" class="check-all"/></th><th>사용자이름</th><th>계좌번호</th><th>주민등록번호</th><th>은행코드</th></tr>
							
							<c:forEach items="${list}" var = "list">
								<tr>
									<td><input type="checkbox" name="idxArr" value="${list.acc_num}" class="check-one"/></td>
									<td><c:out value="${list.user_name}" /></td>
									<td><c:out value="${list.acc_num}" /></td>
									<td><c:out value="${list.user_RRN}" /></td>
									<td><c:out value="${list.acc_code}" /></td>
								</tr>
							</c:forEach>
							
						</table>

						</form>
		
	</div>
	</div>
		
		
	</body>
</html>
