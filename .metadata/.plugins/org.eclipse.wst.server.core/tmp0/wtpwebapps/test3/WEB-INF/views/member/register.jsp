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
		<title>회원가입</title>
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				location.href = "/";
			})
			
			//회원가입 제출 버튼 클릭시
			$("#submit").on("click", function(){
				if($("#user_id").val()==""){
					alert("아이디를 입력해주세요.");
					$("#user_id").focus();
					return false;
				}
				if($("#user_pw").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#user_pw").focus();
					return false;
				}
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
				var idChkVal = $("#idChk").val();
				if(idChkVal == "N"){
					alert("중복확인 버튼을 눌러주세요.");
				}else if(idChkVal == "Y"){ 
					$("#regForm").submit();
				}
			});
		})
		
		function fn_idChk(){
			$.ajax({
				url : "/member/idChk",
				type : "post",
				dataType : "json",
				data : {"user_id" : $("#user_id").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
					}else if(data == 0){
						$("#idChk").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
					}
				}
			})
		}
	</script>
	<body>
		<section id="container">
			<form action="/member/register" method="post" id="regForm">
				<div>
					<label>[회원가입]</label>
				</div>
				
				<div class="form-group has-feedback">
					<label class="control-label" for="user_id">아이디</label>
					<input class="form-control" type="text" id="user_id" name="user_id" />
					<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="user_pw">패스워드</label>
					<input class="form-control" type="password" id="user_pw" name="user_pw" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="user_name">성명</label>
					<input class="form-control" type="text" id="user_name" name="user_name" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="user_RRN">주민번호</label> 
					<input class="form-control" type="text" id="user_RRN" name="user_RRN" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="user_phoneNum">핸드폰번호</label>
					<input class="form-control" type="text" id="user_phoneNum" name="user_phoneNum" />
				</div>
				
				<div class="job row">
					 	<div class="col-xs-2 col-sm-2">
						    <select name="user_job" class="form-control">
						      <option value="n"<c:out value="${scri.userJob == null ? 'selected' : ''}"/>>-----</option>
						      <option value="일반사용자"<c:out value="${scri.userJob eq 't' ? 'selected' : ''}"/>>일반사용자</option>
						      <option value="관리자"<c:out value="${scri.userJob eq 'c' ? 'selected' : ''}"/>>관리자</option>
						    </select>
					    </div>
				</div>
				
			</form>
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="button" id="submit">회원가입</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
		</section>
		
	</body>
	
</html>