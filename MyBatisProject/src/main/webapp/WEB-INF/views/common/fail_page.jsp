<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 실패 페이지</title>
</head>
<body>

	<jsp:include page="../include/header.jsp"/><!-- 내 위치에서 상위폴더로 올라가기 ../ -->
	
	<h1 style="color:red; margin:50px">${ failMsg }</h1>
	
	
	
	
	
	
	
	
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>