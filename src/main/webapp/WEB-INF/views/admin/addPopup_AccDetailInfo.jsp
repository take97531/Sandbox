<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
   <head>
      <!-- 합쳐지고 최소화된 최신 CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
      <!-- 부가적인 테마 -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
       
       <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <title>AccDetailInfo추가</title>
   </head>
   <script type="text/javascript"> 

	   $(document).ready(function(){  
		   $("#submit").on("click", function(){
		       if($("#acc_num").val()==""){
		          alert("계좌번호를 입력해주세요.");
		          $("#acc_num").focus();
		          return false;
		       }
		       if($("#bank_deposit").val()==""){
		          alert("입/출금을 선택해주세요.");
		          $("#bank_deposit").focus();
		          return false;
		       }
		       if($("#bank_amount").val()==""){
		          alert("금액을 입력해주세요.");
		          $("#bank_amount").focus();
		          return false;
		       }
		       if($("#bank_store").val()==""){
		          alert("가맹점을 입력해주세요.");
		          $("#bank_store").focus();
		          return false;
		       }
		
		       $("#addForm").submit();
		       alert("추가완료");
		       opener.parent.location.reload();
		       //window.close();
		    });
	   })
   
   </script>
   <body>

   

   <div style="text-align: center;">
      
         <div class="container" >
            
               <div class="jumbotron navbar navbar-expand-sm navbar-dark bg-dark" style="padding-top:20px; width:350px;">
                  <h2 style="text-align: center;">AccDetailInfo 추가</h2>
             
                  <form role="form" action="/admin/write2_1" method="post" id="addForm" class="form-group has-feedback" style="text-align: center;">
  						
				<div style="text-align: center;">
                  <div class="input-group">	
				  	<span class="input-group-addon" style="width:100px">계좌번호</span>	
                    <input class="form-control" type="text" id="acc_num" name="acc_num" style="width:200px"/>
                  </div>
                  
                 
                  <div class="input-group">	
				  	<span class="input-group-addon" style="width:100px">입/출금</span>
                  	  <select id="bank_deposit" name="bank_deposit" class="form-control" style="width:200px">
								      <option value="n">-----</option>
								      <option value="입금">입금</option>
								      <option value="출금">출금</option>
						   			 </select>
                  </div>
                   
                   <div class="input-group">	
				  	<span class="input-group-addon" style="width:100px">처리금액</span>	
                  	<input class="form-control" type="text" id="bank_amount" name="bank_amount" style="width:200px"/>
                  </div>
				</div>
					
				  <div class="input-group">	
				  	<span class="input-group-addon" style="width:100px">가맹점</span>	
                    <input class="form-control" type="text" id="bank_store" name="bank_store" style="width:200px"/>
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