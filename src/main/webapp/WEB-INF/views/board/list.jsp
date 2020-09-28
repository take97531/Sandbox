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
 	
 		</style>
 		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<!-- common CSS -->

  		<script>
            $(function() {
            
            	$('#searchDateBtn').click(function() {
			          var fromDate = $("#fromDate");
			          var toDate = $("#toDate");
			          
			          var fromDateVal = fromDate.val();
			          var toDateVal = toDate.val();
			          
			          console.log("fromDate : " + fromDateVal);
			          console.log("toDate : " + toDateVal);
			          
			         var searchType = $("select option:selected").val() + 'd';
			  		 var keyword = $('#keywordInput').val() + " " + fromDateVal + " " + toDateVal;
			        
			  		console.log("searchType : " + searchType);
			          console.log("keyword : " + keyword);
			       //   console.log("scri : " + $(scri.searchType));
			  		 
			          self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + searchType + "&keyword=" + encodeURIComponent(keyword);
			        });
            
                //오늘 날짜를 출력
                $("#today").text(new Date().toLocaleDateString());

                //datepicker 한국어로 사용하기 위한 언어설정
                $.datepicker.setDefaults($.datepicker.regional['ko']); 
                
                // 시작일(fromDate)은 종료일(toDate) 이후 날짜 선택 불가
                // 종료일(toDate)은 시작일(fromDate) 이전 날짜 선택 불가

                //시작일.
                $('#fromDate').datepicker({
                    showOn: "both",                     // 달력을 표시할 타이밍 (both: focus or button)
                    buttonImage: "${pageContext.request.contextPath}/resources/calendar.png", // 버튼 이미지
                    buttonImageOnly : true,             // 버튼 이미지만 표시할지 여부
                    buttonText: "날짜선택",             // 버튼의 대체 텍스트
                    dateFormat: "yy-mm-dd",             // 날짜의 형식
                    changeMonth: true,                  // 월을 이동하기 위한 선택상자 표시여부
                    //minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
                    onClose: function( selectedDate ) {    
                        // 시작일(fromDate) datepicker가 닫힐때
                        // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                        $("#toDate").datepicker( "option", "minDate", selectedDate );
                    }                
                });

                //종료일
                $('#toDate').datepicker({
                    showOn: "both", 
                    buttonImage: "${pageContext.request.contextPath}/resources/calendar.png" , 
                    buttonImageOnly : true,
                    buttonText: "날짜선택",
                    dateFormat: "yy-mm-dd",
                    changeMonth: true,
                    //minDate: 0, // 오늘 이전 날짜 선택 불가
                    onClose: function( selectedDate ) {
                        // 종료일(toDate) datepicker가 닫힐때
                        // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
                        $("#fromDate").datepicker( "option", "maxDate", selectedDate );
                    }                
                });
            });
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
			<a class="navbar-brand" href="#"><font size="3">테스트 페이지 2</font></a>
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
				<form role="form" method="get">
				<div class="body">
				<hr />
				
					<div class="search row">
					
						 	<div class="col-xs-1 col-sm-1">
							    <select name="searchType" class="form-control">
							      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
							      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>계좌번호</option>
							      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>가맹점명</option>
							      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>입/출금</option>
							      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>은행명</option>
							    </select>
						    </div>
							
							<div class="col-xs-4 col-sm-4">
								<div class="input-group">
						    		<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" class="form-control"/>
						    		<span class="input-group-btn">
						    			<button id="searchBtn" type="button" class="btn btn-default">검색</button>
						    		</span>
						    	</div>
						    </div>
						    
						    <script>
						      $(function(){
						        $('#searchBtn').click(function() {
						          self.location = "list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
						        });
						      });   
						    </script>
					
						<div>
							<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
						</div>
					
						<div class="clear">
                            <form>
					          <label for="fromDate">조회 시작일</label>
					          <input type="text" name="fromDate" id="fromDate" value="${scri.start_date}">
					          ~
					          <label for="toDate">조회 종료일</label>
					          <input type="text" name="toDate" id="toDate" value="${scri.end_date}">
					        </form>
                        	
						    <button id="searchDateBtn" type="button" class="btn btn-default">검색</button>
						   	
                        </div>
                        
					
					</div>
					
					<div style="border: 2px; float: left; width: 45%; margin: 6;">
						
						<table class="table table-hover" >
							<tr><th>원본데이터</th></tr>
							<tr><th>날짜</th><th>은행명</th><th>계좌번호</th><th>입/출금</th><th>처리금액</th><th>잔액</th><th>가맹점</th></tr>
							
						
							<c:forEach items="${list}" var = "list">
								<tr>
									<td><fmt:formatDate value="${list.bank_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<c:if test="${list.acc_code == 81}">
										<c:out value="하나은행"/>
										</c:if>
										<c:if test="${list.acc_code == 4}">
										<c:out value="국민은행"/>
										</c:if>
										<c:if test="${list.acc_code == 11}">
										<c:out value="농협은행"/>
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
						<div class="col-md-offset-3">
						<br>
						  <ul class="pagination">
						    <c:if test="${pageMaker.prev}">
						    	<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
						    </c:if> 
						
						    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						    	<li <c:out value="$(pageMaker.cri.page == idx ? 'class=info' : ''}" />>
						    	<a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
						    </c:forEach>
						
						    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						    	<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
						    </c:if> 
						  </ul>
						</div>
						
						</div>
						
					
						<div style="border: 2px; float: left; width: 45%; margin: 6;">
							<table class="table table-hover">
							<tr><th>스크램블데이터</th></tr>
							<tr><th>날짜</th><th>은행명</th><th>계좌번호</th><th>입/출금</th><th>처리금액</th><th>잔액</th><th>가맹점</th></tr>
							
						
							<c:forEach items="${scrambledList}" var = "scrambledList">
								<tr>
									<td><fmt:formatDate value="${scrambledList.bank_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<c:if test="${scrambledList.acc_code == 81}">
										<c:out value="하나은행"/>
										</c:if>
										<c:if test="${scrambledList.acc_code == 4}">
										<c:out value="국민은행"/>
										</c:if>
										<c:if test="${scrambledList.acc_code == 11}">
										<c:out value="농협은행"/>
										</c:if>
									</td>
									<td><c:out value="${scrambledList.acc_num}" /></td>
									<td><c:out value="${scrambledList.bank_deposit}" /></td>
									<td><c:out value="${scrambledList.bank_amount}" /></td>
									<td><c:out value="${scrambledList.bank_balance}" /></td>
									<td><c:out value="${scrambledList.bank_store}" /></td>
									
								</tr>
							</c:forEach>
								
							</table>
							
						</div>
						<br>
						
					</div>
					
				</form>
				
			</section>
			
		</div>
	</body>
</html>
