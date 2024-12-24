<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업데이트~~~</title>
<style>
form {
	width: 80%;
	margin: auto;
}
</style>
</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<div class="outer">

		<h2 align="center">게시글 수정하기</h2>
		<br>
		<br>

		<form action="update.notice?noticeNo=${ notice.noticeNo }"
			method="post" id="update-form">



			<div class="form-group">
				<label for="usr">제목</label> <input type="text" class="form-control"
					id="usr" name="title" value="${ notice.noticeTitle }">
			</div>

			<div class="form-group">
				<label for="comment">내용</label>
				<textarea class="form-control" name="content" rows="15" id="comment"
					style="resize: none;">${ notice.noticeContent }</textarea>
			</div>


			<div align="center">
				<button type="submit" class="btn btn-sm btn-info">수정하기</button>
				<button type="button" class="btn btn-sm btn-secondary"
					onclick="history.back();">뒤로가기</button>
			</div>

		</form>

	</div>

	<jsp:include page="../include/footer.jsp" />


</body>
</html>