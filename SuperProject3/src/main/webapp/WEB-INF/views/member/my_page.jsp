<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#hobby-wrap{
	    display: flex;
	    justify-content: center;
	    flex-direction: row;
	    flex-wrap: nowrap;
	    align-items: baseline;
	}
	input[type=checkbox]{
		margin-left : 50px;
	}
</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	<div style="width : 80%; margin : auto; padding : 50px;">
		<form action="update.me" name="signup" id="signUpForm" method="post"
						style="margin-bottom: 0;">
			<table
				style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
				<tr>
					<td style="text-align: left">
						<p><strong>아이디는 변경할 수 없습니다.</strong>&nbsp;&nbsp;&nbsp;<span id="idChk"></span></p>
					</td>							
				</tr>
				<tr>
					<td><input type="text" name="userId" id="user_id"
						class="form-control tooltipstered" maxlength="14"
						required="required" aria-required="true"
						style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
						value="${ sessionScope.loginUser.userId }" readonly> <!-- id바꾸면 안 되니까 readonly -->
						</td>
					
				</tr>

				<tr>
					<td style="text-align: left">
						<p><strong>이름을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="nameChk"></span></p>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="userName" id="user_name"
						class="form-control tooltipstered" maxlength="6"
						required="required" aria-required="true"
						style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
						placeholder="한글로 최대 6자" value="${ sessionScope.loginUser.userName }"></td>
				</tr>
				
				<tr>
					<td style="text-align: left">
						<p><strong>이메일을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="emailChk"></span></p>
					</td>
				</tr>
				<tr>
					<td><input type="email" name="email" id="user_email"
						class="form-control tooltipstered" 
						required="required" aria-required="true"
						style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
						placeholder="ex) kh@kh.com" value="${ sessionScope.loginUser.email }"></td>
				</tr>
				
				<tr>
					<td style="text-align: left">
						<p><strong>취미를 선택해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="hobbyChk"></span></p>
					</td>
				</tr>
				<tr>
					<td id="hobby-wrap">
											
						<input type="checkbox" id="baseball" value="야구" name="interest">&nbsp;<label for="baseball">야구</label>
						<input type="checkbox" id="device" value="전자제품" name="interest">&nbsp;<label for="device">전자제품</label>
						<input type="checkbox" id="camping" value="캠핑" name="interest">&nbsp;<label for="camping">캠핑</label>

						<br>

						<input type="checkbox" id="farming" value="농사" name="interest">&nbsp;<label for="farming">농사</label>
						<input type="checkbox" id="web" value="web" name="interest">&nbsp;<label for="tea">웹</label>
						<input type="checkbox" id="java" value="자바" name="interest">&nbsp;<label for="java">자바</label>
					</td>
				</tr>
				
				<script> /*여기 쓰면 위치 별로지만 공부해야하니까*/
					const interest = '${ loginUser.interest }';
					// 문자열값을 써야 하므로 '' 를 붙여줘야한다. 안 붙이고 {}만 있으면 야구 이런식으로 존재하게 되는것
					
					$('input[type=checkbox]').each((e, c) =>{
						console.log(c);
						// 순차적으로 접근한 checkbox의 value속성값이
						// interest에 포함되어 있을 경우 checked
						
						// 자바스크립트의 indexOf => 없으면 -1 반환
						// 아니면 정규표현식으로 interest.search($(c).val())...
						if(interest.search( c.value) != -1){
							c.checked = 'true';
						}
					});
				
				</script>
	
				<tr>
					<td style="padding-top: 10px; text-align: center">
						<p><strong>원하시는 서비스를 선택해주세요.</strong></p>
					</td>
				</tr>
				<tr>
					<td style="width: 100%; text-align: center; colspan: 2;"><input
						type="submit" value="정보수정" 
						class="btn form-control tooltipstered" id="signup-btn"
						style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">
					</td>
				</tr>
				<tr>
					<td style="width: 100%; text-align: center; colspan: 2;"><input
						type="button" value="비밀번호수정" 
						class="btn form-control tooltipstered" id="signup-btn"
						style="background-color: #52ffe9; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
						data-toggle="modal" data-target="#updatePwd"> <!-- updatePwd 아이디를 가진 게 뜨게 됨 -->
					</td>
				</tr>
				<tr>
					<td style="width: 100%; text-align: center; colspan: 2;"><input
						type="button" value="회원탈퇴" 
						class="btn form-control tooltipstered" id="signup-btn"
						style="background-color: #ff5352; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
						data-toggle="modal" data-target="#deleteForm">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<jsp:include page="../include/footer.jsp" />
	
	
	 	<!-- 회원 탈퇴 모달 -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <div class="modal-header">
	        <h4 class="modal-title" style="color:red;">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <div class="modal-body">
	        <form action="delete.me" method="post">
	        
			  <div class="form-group">
			    <label for="userPwd" style="font-size:12px; color:red">탈퇴를 원하신다면 비밀번호를 입력해주세요.</label>
			    <input type="password" name="userPwd" class="form-control" placeholder="비밀번호를 입력해주세요." id="deletePwd" required>
			  </div>
			  
			  <button type="submit" class="btn btn-danger" onclick="return deleteMember();">회원탈퇴</button>
			  
			  <script>
			  	function deleteMember(){
			  		
			  		const value = prompt('탈퇴를 원하신다면 "어쩌고저쩌고"를 정확히 입력해주세요.');
			  		
			  		if(value === '어쩌고저쩌고'){
						return true;	  			
			  		}
			  		else{
			  			return false;
			  		}
			  		
			  	}
			  </script>
			</form>
	      </div>

	
	    </div>
	  </div>
	</div>
	
	
	<!-- 비밀번호 변경 모달 -->
	<div class="modal" id="updatePwd">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	
	      <div class="modal-body">
	        <form action="updatePwd.me" method="post">
	        	<input type="hidden" name="userNo" value="${ loginUser.userNo }"/> <!-- ****사용자에게 보이지 않고 몰래 넘김 개발자도구 쓰면 보임 -->
			  <div class="form-group">
			    <label for="userPwd">현재 비밀번호 :</label>
			    <input type="password" name="userPwd" class="form-control" placeholder="비밀번호를 입력해주세요." id="userPwd" required>
			  </div>
			  
			  <div class="form-group">
			    <label for="changePwd">변경할 비밀번호 :</label>
			    <input type="password" name="changePwd" class="form-control" placeholder="변경할 비밀번호를 입력해주세요." id="changePwd" required>
			  </div>
			  
			  <div class="form-group">
			    <label for="checkPwd">변경할 비밀번호 확인 :</label>
			    <input type="password" class="form-control" placeholder="다시 한 번 입력해주세요." id="checkPwd" required>
			  </div>
			  
			  <button type="submit" class="btn btn-secondary" onclick="return validatePwd();">비밀번호 변경</button>
			  
			  <script>
			  	function validatePwd(){
			  		
			  		if($('#changePwd').val() != $('#checkPwd').val()){
			  			alert('비밀번호를 동일하게 입력해주세요!');
			  			$('#checkPwd').focus().val('');
			  			return false;
			  		}
			  		
			  		return true;
			  	}
			  </script>
			</form>
	      </div>

	
	    </div>
	  </div>
	</div>

</body>