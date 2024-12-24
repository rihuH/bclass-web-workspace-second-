<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 
	.list-area{
		text-align : center;
	}

	.thumbnail{
		box-shadow : 1px 1px 2px #0000002e;
		width : 300px;
		padding : 12px;
		margin : 25px;
		display : inline-block;
		background-color: #ffffffb0;
		color:#000000b0;
		font-weight: bold;
		border-radius: 12px;
	}

	.thumbnail > img{
		width : 270px;
		height : 180px;
		margin-bottom : 8px;
		border : 1px solid rgb(172 205 255 / 57%);
		border-radius: 12px;
	}

	.thumbnail:hover{
		cursor:pointer;
		opacity:0.8;
	}


</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	<div class="outer">
		
		<div style="margin-top : 15px; width : 400px; height : 280px; margin:auto;">
			<img width="100%" height="100%" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="">
		</div>
		
			<div style="align:right;" >
				<a class="btn btn-sm btn-primary"
				   style="background:rgb(193 229 255); border:none; width:100%"
				   href="enrollForm.thumbnail"
				>글작성</a>
			</div>

		<div class="list-area">
			
				<br>
			<c:choose>	
				<c:when test="${ empty list }">
					등록된 게시글이 존재하지 않습니다.<br>
				</c:when>
				<c:otherwise>
				<c:forEach items="${ list }" var="board">
			<div class="thumbnail" align="center">
				<input type="hidden" value="${ board.boardNo }"/> <!-- 클릭되면 넘버 넘어가도록 -->
				<img src="${ board.imagePath }" alt="이미지">
				<p>
					<label>No. ${ board.boardNo }</label> / <span>${ board.boardTitle }</span> <br>
					<label>조회수</label> : <span>${ board.count }</span>
				</p>
			</div>
				</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<script>
				$('.thumbnail').click(function(){
					// 클릭할 때마다 => url로 요청 => location.href의 값을 바꿔줘야함.
					console.log($(this).children().eq(2).children().eq(0).text().split(' ')[1]);
					// this(div요소임) 의 자식(img, p..)들 중에서 세번째 자식 p(eq(1)) 이 필요하고 그 자식(.childern) 중 첫번째 라벨(eq(0))의 텍스트(.text) 
					// 중에서 뒤에 숫자만 .split(' ')[1]
					// detail.tumbnail?boardNo=
					const boardNo = $(this).children().eq(0).val();// hidden으로 값을 넘길 경우 그 input요소의 val 확인
					location.href = 'detail.thumbnail?boardNo='+boardNo;
					
				})
			</script>

			


		</div>
	
	</div>
	
	<jsp:include page="../include/footer.jsp" />


</body>
</html>