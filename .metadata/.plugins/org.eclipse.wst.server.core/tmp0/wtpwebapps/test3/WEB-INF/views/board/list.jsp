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
	</head>
	<body>
		<div id="container">
			<header>
				<h1>사용자페이지</h1>
			</header>
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
					<div class="search row">
						 	<div class="col-xs-1 col-sm-1">
							    <select name="searchType" class="form-control">
							      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
							      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
							      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
							      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
							      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
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
						 </div>
					<div style="border: 2px; float: left; width: 40%; margin: 6;">
						
						<table class="table table-hover" >
							<tr><th>원본데이터</th></tr>
							<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>테스트</th><th>테스트</th><th>테스트</th></tr>
							
						
							<c:forEach items="${list}" var = "list">
								<tr>
									<td><c:out value="${list.bno}" /></td>
									<td>
										<a href="/board/readView?bno=${list.bno}&
																page=${scri.page}&
																perPageNum=${scri.perPageNum}&
																searchType=${scri.searchType}&
																keyword=${scri.keyword}"><c:out value="${list.title}" /></a>
									</td>
									<td><c:out value="${list.writer}" /></td>
									<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
									<td><c:out value="${list.test}" /></td>
									
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
						
					
						<div style="border: 2px; float: left; width: 40%; margin: 6;">
							<table class="table table-hover">
							<tr><th>스크램블데이터</th></tr>
								<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>테스트</th><th>테스트</th><th>테스트</th></tr>
								
							
								<c:forEach items="${list}" var = "list">
									<tr>
										<td><c:out value="${list.bno}" /></td>
										<td>
											<a href="/board/readView?bno=${list.bno}&
																	page=${scri.page}&
																	perPageNum=${scri.perPageNum}&
																	searchType=${scri.searchType}&
																	keyword=${scri.keyword}"><c:out value="${list.title}" /></a>
										</td>
										<td><c:out value="${list.writer}" /></td>
										<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
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
