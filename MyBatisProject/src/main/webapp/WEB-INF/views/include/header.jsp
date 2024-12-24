<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">

  <title>웹개발 시작하기</title>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="resources/css/agency.min.css" rel="stylesheet">
  <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
  <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>

<style>
    #mainNav .navbar-nav .nav-item .nav-link {
        font-weight: 600;
    }

    #sub-bg{
        width : 80%;
        margin : auto;
        height : 1200px;
        padding-top : 60px;
        padding-bottom : 60px;
        margin-top : 300px;
    }

    #sub-1{
        width : 70%;
        height : 40%;
        margin-right : auto;
        background-image: url(https://www.kh-academy.co.kr/resources/images/main/main_renewal/sub/sub02/educationinfo/jongro/04.jpg);
        background-size: cover;
        background-repeat: none;
    }

    #sub-2{
        margin-top : 120px;
        width : 70%;
        margin-left : auto;
        height : 40%;
        background-image: url(https://www.kh-academy.co.kr/resources/images/main/main_renewal/sub/sub02/educationinfo/jongro/05.jpg);
        background-size: cover;
        background-repeat: none;
    }

    footer{
        border-top: #52b1ff28 1px solid;
    }

    .dropdown:hover > .dropdown-menu { 
        display: block;  
    }
</style>


</head>

<body id="page-top">

	<c:if test="${ not empty alertMsg }">
		<script>
			alert('${ alertMsg }'); // 조건문 쓰지 않으면 성공하지 않았을 때도 빈 문자열인 알림창이 뜨게 되므로조건문
			// 그런데 문제는 session에 담았으므로 헤더를 만날때마다(새로고침 할 때마다) 알림창이 계속 뜨게됨
			// 따라서 한 번 쓰고 나면 값을 지워줄 필요가 있음
		</script>	
		<c:remove var="alertMsg" scope="session" />
	</c:if>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand" href="#">
      	<img class="img-fluid" src="https://www.kh-academy.co.kr/resources/images/main/logo.svg" alt="로고없음" style="width:130px; height:50px;" />
      </a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive">
        메뉴
        <i class="fas fa-bars"></i>
      </button>
      
      <!-- href 수정하면 마지막 / 뒤에 추가해준다. 뒤에 / 자동으로 붙여주는 것은 home만이다. -->
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav text-uppercase ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="/mybatis">HOME</a> 
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="list.notice?currentPage=1">공지사항</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="list.board?currentPage=1">게시판</a> <!-- 1페이지로 요청!! -->
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="list.thumbnail">사진게시판</a>
          </li>
          
          
          
          <!-- 로그인 전 : 로그인 / 회원가입
          	   로그인 후 : 내정보 / 로그아웃 
          -->
          <!-- null이랑 비교하면 null이랑만 비교하는 것이고, empty는 null과 ""빈문자열인지도 비교하게 된다. -->
          <c:choose>
          	<c:when test="${ empty sessionScope.loginUser }"> 
	          <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" data-toggle="modal" data-target="#log-in">로그인</a>
	          </li>
	          <li class="nav-item">
	          <a class="nav-link js-scroll-trigger" href="join.me">회원가입</a>
	          </li>
	         </c:when>
	         
	
	          <c:otherwise>
		          <li class="nav-item">
		          <a class="nav-link js-scroll-trigger" href="myPage.me">내정보</a>
		          </li>
		          <li class="nav-item">
		          <a class="nav-link js-scroll-trigger" href="logout.me" onclick="return confirm('진짜로 로그아웃 하려고?')">로그아웃</a>
		          </li>
	          </c:otherwise>
          </c:choose>
          <!-- confirm 확인/취소 버튼 뜨는 것. 각 true/false 값이 돌아온다. return을 앞에 달아두면 return true/false가 되어
          return false면 onclick이 먼저 수행되므로 그 앞에 있는 행위 즉 href로 요청보내는 행위가 일어나지 않게 된다. -->
          
          
          
        </ul>
      </div>
    </div>
  </nav><br><br><br>
  
  

  <!-- 로그인 Modal-->
<div class="modal fade" id="log-in">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">
					<span style="color: #52b1ff;">KH</span> 로그인
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
			
			<!-- 
				=> http://localhost:84/super/login.me(서블릿 매핑값)
				
				form태그 안에 존재하는 submit버튼을 클릭 했을 때
				form태그의 action속성에 작성된 URL로 요청(제출)
				
				==> Controller(Servlet)을 호출
				
				* 경로 지정 방식
				
				절대 경로 방식(/) : /로 시작. /contextroot/요청할URL
									TOMCAT포트 뒤 localhost:84 뒤에 action에 작성한 값이 붙으면서 요청
				상대 경로 방식(매핑값) : / 없이 시작. 현재 이 페이지에서 보여지는 URL경로중에 
									마지막 /로부터 뒤에 action속성에 작성한 값이 붙으면서 요청
			
			 -->
			<!-- 아래 폼태그 경로도 이렇게 작성. 파일명 바뀔 경우를 대비 -->
				<form action=" ${ pageContext.request.contextPath }/login.me" name="sign-in" method="post" id="signInForm"
					style="margin-bottom: 0;">
					<table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
						<tr>
							<td style="text-align: left">
								<p><strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="text" name="userId" id="signInId"
								class="form-control tooltipstered" maxlength="10"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
								placeholder="최대 15자"></td>
						</tr>
						<tr>
							<td style="text-align: left">
								<p><strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20" id="signInPw"
								name="userPwd" class="form-control tooltipstered" 
								maxlength="20" required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
								placeholder="최소 8자"></td>
						</tr>
						<tr>
							<td style="padding-top: 10px; text-align: center">
								<p><strong>로그인하셔서 서비스를 이용해보세요~~!</strong></p>
							</td>
						</tr>
						<tr>
							<td style="width: 100%; text-align: center; colspan: 2;"><input
								type="submit" value="로그인" class="btn form-control tooltipstered" id="signIn-btn"
								style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>


</body>