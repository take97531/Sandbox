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
      <title>개인정보 스크램블 테스트</title>
      <style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
			input {height:40px; font-size:20px;}
		</style>
	
 		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<!-- common CSS -->
   </head>
   <script type="text/javascript">
      $(document).ready(function(){
         
         //회원가입 제출 버튼 클릭시
         $("#submit").on("click", function(){
            if($("#user_name").val()==""){
               alert("성명을 입력해주세요.");
               $("#user_name").focus();
               return false;
            }
            if($("#user_phoneNum").val()==""){
               alert("핸드폰번호를 입력해주세요.");
               $("#user_name").focus();
               return false;
            }
            if($("#user_RRN").val()==""){
               alert("주민등록번호를 입력해주세요.");
               $("#user_RRN").focus();
               return false;
            }
               $("#regForm").submit();
         });
      })
   </script>
   <body>


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

   <div style="text-align: center; margin: 100 auto;">
      
         <div class="container">
               <div class="jumbotron" style="padding-top:20px; float:left; width:45%; height:70%; font-size:20px;">
                  <h2 style="text-align: center;">Personal Data</h2>
                        	<br/>
                        	<br/>
                  <form action="/board/memberTest" method="post" id="regForm">
                     <div class="form-group has-feedback" >
                        <label class="control-label" for="user_name">성명</label>
                        <input class="form-control" type="text" id="user_name" name="user_name" value="${mem.user_name}" 
            style= "height:30px; font-size:16px;"/>
                        	<br/>
                     </div>
                     <div class="form-group has-feedback">
                        <label class="control-label" for="user_RRN">주민번호</label> 
                        <input class="form-control" type="text" id="user_RRN" name="user_RRN"  value="${mem.user_RRN}"
            style= "height:30px; font-size:16px;"/>
                        	<br/>
                     </div>
                     <div class="form-group has-feedback">
                        <label class="control-label" for="user_phoneNum">핸드폰번호</label>
                        <input class="form-control" type="text" id="user_phoneNum" name="user_phoneNum"  value="${mem.user_phoneNum}"
            style= "height:30px; font-size:16px;"/>
                        	<br/>
                     </div>
         
                  </form>
                     <div class="form-group has-feedback">
                        <button class="btn btn-success" type="button" id="submit">Scramble!</button>
                     </div>
               </div>
               
               
               
               <div class="jumbotron" style="padding-top:20px; margin-right:30px; float:right; width:45%;  height:70%; font-size:20px;">
                  <h2 style="text-align: center;">Scramble</h2>
                        	<br/>
                        	<br/>
                  <form action="/member/register" method="post" id="regForm">
                     <div class="form-group has-feedback">
                        <label class="control-label" for="user_name">성명</label>
                        	<br/>
                        <c:if test="${scrambledList.user_name == null}">
                        	<c:out value="값을 입력하세요."/>
						</c:if>
                        <c:out value="${scrambledList.user_name}"/>
                        	<br/>
                        	<br/>
                     </div>
                     
                     <div class="form-group has-feedback">
                        <label class="control-label" for="user_RRN">주민번호</label> 
                        	<br/>
                        <c:if test="${scrambledList.user_name == null}">
                        	<c:out value="값을 입력하세요."/>
						</c:if>
                        <c:out value="${scrambledList.user_RRN}"/>
                        	<br/>
                        	<br/>
                     </div>
                     
                     <div class="form-group has-feedback">
                        <label class="control-label" for="user_phoneNum">핸드폰번호</label>
                        	<br/>
                        <c:if test="${scrambledList.user_name == null}">
                        	<c:out value="값을 입력하세요."/>
						</c:if>
                        <c:out value="${scrambledList.user_phoneNum}"/>
                        	<br/>
                        	<br/>
                     </div>
                     
                  </form>
               </div>
            </div>             
         </div>


   </body>
   
</html>