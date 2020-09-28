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
      <title>AccInfo추가</title>
   </head>
   <script type="text/javascript">
   
	   $(document).ready(function(){  
		   $("#submit").on("click", function(){
			   if($("#user_name").val()==""){
			          alert("사용자 이름을 입력해주세요.");
			          $("#user_name").focus();
			          return false;
			       }
		       
		       if($("#acc_num").val()==""){
		          alert("계좌번호를 입력해주세요.");
		          $("#acc_num").focus();
		          return false;
		       }
		       
		       if($("#user_RRN").val()==""){
		          alert("주민등록번호를 입력해주세요.");
		          $("#user_RRN").focus();
		          return false;
		       }
		
			       $("#addForm").submit();
			       alert("추가완료");
			       opener.parent.location.reload();
			       window.close();
		    });
	   })
      
   </script>
   <body>

   <div style="text-align: center;">
      
         <div class="container" >
            
               <div class="jumbotron navbar navbar-expand-sm navbar-dark bg-dark" style="padding-top:20px; width:350px;">
                  <h2 style="text-align: center;">AccInfo 추가</h2>
                  
                  <form role="form" action="/admin/write2_2" method="post" id="addForm" class="form-group has-feedback" style="text-align: center;">
  
					<div style="text-align: center;">
	                  <div class="input-group">	
					  	<span class="input-group-addon" style="width:100px">사용자이름</span>	
	                    <input class="form-control" type="text" id="user_name" name="user_name" style="width:200px"/>
	                  </div>
	                  
	                  <div class="input-group">	
					 	<span class="input-group-addon" style="width:100px">계좌번호</span>	
	                    <input class="form-control" type="text" id="acc_num" name="acc_num" style="width:200px"/>
	                  </div>
	                  
	                  <div class="input-group">	
					  	<span class="input-group-addon" style="width:100px">주민번호</span>	
	                    <input class="form-control" type="text" id="user_RRN" name="user_RRN" style="width:200px"/>
	                  </div>
	                  
	                  <div class="input-group">	
					  	<span class="input-group-addon" style="width:100px">은행코드</span>	
	                  	<input class="form-control" type="text" id="acc_code" name="acc_code" style="width:200px"/>
	                  </div>
	                  
					</div>
	
	            		<div class="form-group has-feedback">
	                     <button class="btn btn-success" type="submit" id="submit">추가</button>
	                     <button class="cencle btn btn-danger" type="button" onclick="window.close()">취소</button>
	                  	</div>
	         
                  </form>
                    
                  
               </div>
            </div>
         </div>


   </body>
   
</html>