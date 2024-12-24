<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<br/><br/>

	<jsp:include page="../include/header.jsp" />
	
	<div class="outer">
		<div class="container">
		
		<div class="row">
		  <div class="offset-lg-2 col-lg-8">
		    <div class="card">
		      <div class="card-header text-white" style="background-color: #52b1ff;">${ board.boardNo }</div>
		      <div class="card-body"> 
		
		          <div class="form-group">
		            <label>카테고리</label><br>
		            <span>${ board.category }</span>
		          </div>      
		        
		          <div class="form-group">
		            <label>작성자</label>
		            <input type="text" class="form-control" name='writer' value="${ board.boardWriter }" readonly>
		          </div>
		          
		          <div class="form-group">
		            <label>제목</label>
		            <input type="text" class="form-control" name='title' value="${ board.boardTitle }" readonly>
		          </div>
		
		          <div class="form-group">
		            <label>내용</label>
		            <textarea class="form-control" rows="5" name='content' readonly style="resize:none;">${ board.boardContent }</textarea>
		          </div>
		
		          <div class="form-group">
		            <label>첨부파일</label>
			            	<!-- 첨부파일은 있을수도있음 -->
			            	<!-- 첨부파일의 경로 
			            	/super/resources/board_upfiles/바뀐이름
			            	download="어떤 이름으로 다운받게 할 것인지" -->
					<c:choose>
						<c:when test="${ attachment ne null }">
			            	<a 
			            	download="${ attachment.originName }"
			            	href="${ attachment.filePath }/${ attachment.changeName }"
			            	>${ attachment.originName }</a><br>
			            	
			            	<img src="${ attachment.filePath }/${ attachment.changeName }"/ width="360" height=200/>
						</c:when>

			            <c:otherwise>
			            	&nbsp;&nbsp;<span>첨부파일이 존재하지 않습니다.</span>
			            </c:otherwise>
			         </c:choose>
			            	<!-- 첨부파일은 없을수도있음 -->
		          </div>
		         
		          <a class="btn" href="list.board?currentPage=1"
		             style="background-color: #52b1ff; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
		          >목록</a>&nbsp;&nbsp;
		          
		          <!-- 
		          식별값 : MEMBER -> PK(USER_NO), UNIQUE(USER_ID)
		          게시글 작성자 : BOARD -> BOARD_WRITER (USER_ID)
		          
		          수정버튼의 href 속성값이 updateForm.board?boardNo=115 -> 이게 언제 만들어지는 것인가****************
		          사용자의 리퀘스트 http://localhost/super/detail.board?boardNo=115 --- Tomcat이 받음
		          받아서 서블릿의 doGet호출, Service(), Dao(), DB,,, 갔다 와서 사용자에게 html형식으로 데이터를 만들어서 보내줘야 볼 수 있다.
		          그래서response객체를 써야 하는데, 서블릿에서 처리가 힘들어서 jsp쪽으로 기능을 뺀 것이다.
		          jsp에서 html을 만드는 것.
		          jsp가 서버에서 c:if문을 실행할 때, 사용자아이디가 일치해서 버튼을 만들 때 ${board.boardNo}가 생성되어 클라이언트에게 보내지는 것이다.
		          이걸 서버사이드렌더링 이라고 함. 서버에서 모두 만들고 나서 사용자에게 보내짐.
		          따라서 사용자의 리퀘스트인 href속성값은 클라이언트에게 보내지기 전에 미리 완성되어 보내짐. 즉 화면이 생성되면서 버튼을 출력할지말지 렌더링할 때 이미 정해짐
		           -->
		          <c:if test="${ board.boardWriter eq sessionScope.loginUser.userId }">
			          <a 
			            class="btn" 
			            href="updateForm.board?boardNo=${ board.boardNo }"
			      		style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
			      		>수정</a>&nbsp;&nbsp;
			          
			          <a 
			            class="btn" 
			            href="delete.board?boardNo=${ board.boardNo }" onclick="return confirm('정말로 삭제하시겠습니까?')"
			      		style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
			      		>삭제</a>&nbsp;&nbsp;
			      </c:if>

		      </div>
		    </div>
		  </div>
		</div>
		</div>
		<div id="reply-area">
			
			<table class="form-group" align="center">
				<thead>
					<tr>
						<th>댓글작성</th>
						
						<c:if test="${ sessionScope.loginUser ne null }">
							<td>
								<textarea id="replyContent" cols="50" rows="3" style="resize:none;" class="form-control"></textarea>
							</td>
							<td><button onclick="insertReply();" class="btn" style="width : 100%; height : 100%; background-color: #52b1ff; color : white;">댓글등록</button></td>
						</c:if>
						<c:if test="${ sessionScope.loginUser eq null }">
							<td>
								<textarea readonly cols="50" rows="3" style="resize:none;" class="form-control">로그인 후 이용가능한 서비스입니다.</textarea>
							</td>
							<td><button class="btn" style="width : 100%; height : 100%; background-color: #52b1ff; color : white;">댓글등록</button></td>
						</c:if>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			<br><br><br><br>
	    </div>

	</div>
	
	<jsp:include page="../include/footer.jsp" />
	
	<script>
		function selectReplyList(){
			$.ajax({
				url : 'list.reply',
				type : 'get',
				data : {
					boardNo : ${ board.boardNo }
				},
				success : function(list){
					//console.log(list);
					const result = list.map(e => `<tr>`
											   + `<td>\${e.replyWriter}</td>`
											   + `<td>\${e.replyContent}</td>`
											   + `<td>\${e.createDate}</td>`
											   + `</tr>`).join('');
					console.log(result);
					$('#reply-area tbody').html(result);
					
				}
				
			});
		};
		
		$(function(){
			selectReplyList();
		})
		
		function insertReply(){
			$.ajax({
				url : 'insert.reply',
				type : 'post',
				data : {
					content : $('#replyContent').val(), // textarea도 input 친척이어서 값이 val에 들어간다.
					boardNo : ${board.boardNo}
				},
				success : function(result){
					//console.log(result);
					if(result === 'success'){
						$('#replyContent').val(''); // 댓글 다는거 성공하면 textarea 비우기
						selectReplyList(); // 댓글목록 새로 조회해서, 새로 다는 댓글도 보이게하기
					}
				}
			})
		}
	</script>
	
	
	
	
</body>
</html>