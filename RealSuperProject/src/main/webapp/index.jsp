<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안뇽 나는 웰컴파일</title>
</head>
<body>

	<jsp:include page="WEB-INF/views/include/header.jsp" />
	<!-- 내 경로서부터 찾아가기. 상대경로 -->
	<jsp:include page="WEB-INF/views/include/main.jsp" />
	<jsp:include page="WEB-INF/views/include/footer.jsp" />
	<!-- 위에서부터 순서대로 들어가야함 -->

</body>
</html>