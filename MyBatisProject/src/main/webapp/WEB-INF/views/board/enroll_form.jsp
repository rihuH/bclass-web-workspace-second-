<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	form {
		width : 90%;
		margin : auto;
	}

</style>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
	
   <c:if test="${ loginUser eq null }">
   		<script>
   			alert('로그인해야 글 쓸 수 있습니다.');
   				//버튼 비활성화 못하겠음
   			location.href='/super';

   		</script>
   </c:if>
	

	<div class="outer">

        <h2 align="center">게시글 작성하기</h2>
        <br><br> <!-- 파일 첨부 한 개 -->
		
        <form action="insert.board" method="post" id="insert-form"
        	  enctype="multipart/form-data">
        	<!-- 파일 첨부 요청을 보낼 시 반드시 form태그에 enctype="multipart/form-date"라고 추가를 해줘야함.
        	서버로 보내는 데이터 전송이 이중화 되기 때문에. 평소에는 텍스트만 보냈지만
        	파일데이터와 요청 전달값이 따로 가기때문에 2개를 따로 보낼 때의 양식.
        	 -->
        	 <!-- 
        	 제목
        	 내용
        	 카테고리번호
        	 작성자번호
        	 파일
        	  -->
        	<input type="hidden" name="userNo" value="${ loginUser.userNo }" />
        	
        	<div class="form-group">
	        	<select name="category" class="form-control">
	        		<c:forEach items="${ categoryList }" var="c">
	                    <option value="${ c.categoryNo }">
	                       ${ c.categoryName }
	                    </option>
	        		</c:forEach>
	        	</select>
        	</div>

            <div class="form-group">
                <label for="usr">제목</label>
                <input type="text" class="form-control" id="usr" name="title">
            </div>

            <div class="form-group">
                <label for="comment">내용</label>
                <textarea class="form-control" name="content" rows="15" id="comment" style="resize:none;"></textarea>
            </div>
            
            <div class="form-group">
            	<input type="file" name="upfile">
            </div>

            <div align="center">
                <button type="submit" class="btn btn-sm btn-info">등록하기</button>
                <button type="button" class="btn btn-sm btn-secondary"
                onclick="history.back();">뒤로가기</button>
            </div>

        </form>
        
    </div>
    
    <jsp:include page="../include/footer.jsp" />

</body>
</html>