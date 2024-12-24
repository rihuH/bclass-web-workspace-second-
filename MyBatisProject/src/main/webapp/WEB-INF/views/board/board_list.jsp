<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

header.masthead {
   display: none;
}   
.row{
	height : 800px;
}
tr:hover{
	cursor : pointer;
}

</style>

<br/><br/> 
 
   <jsp:include page="../include/header.jsp"/>
   
   <!-- Begin Page Content -->
   <div class="container">
      <div class="row">
         <div class="col-lg-1">
         </div>
         <div class="col-lg-10">
            <div class="panel-body">
            <h2 class="page-header"><span style="color: #52b1ff;">KH</span> 자유 게시판
            <!--  if not empty loginUser 버튼 가리기/  -->
               <a href="enrollForm.board" id="test-btn" class="btn float-right" style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
            </h2>
               <table class="table table-bordered table-hover">
                  <thead>
	                  <tr style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
	                     <th width="100">번호</th>
	                     <th width="150">작성자</th>
	                     <th width="450">제목</th>
	                     <th width="200">작성일</th>
	                     <th width="100">조회수</th>
	                  </tr>
                  </thead>
                  <tbody>
                  <!-- 게시글 리스트 목록이 출력될 영역. forEach items에 순차적으로 돌릴 요소, 배열 등을 작성해준다. var은 변수명-->
                  
                  <c:forEach items="${ list }" var="board">
	                
                    <tr style="color: #52b1ff;"
                        class="board"
                        id="${ board.boardNo }">
                        <td>
                        ${ board.boardNo }
                        </td>
                        <td>
                        ${ board.boardWriter }
                        </td>
                        <td style="color: #52d6ffcc;">
                        ${ board.boardTitle } &nbsp;
                        </td>
                        <td>
                        ${ board.createDate }
                        </td>
                        <td>
                        ${ board.count }
                        </td>
                    </tr>    
                    </c:forEach>
                  </tbody>
               </table>
               <script>
               		$(function(){
               			$('.board').click( e => {
               				// detail.board 현재 클릭한 친구의 보드넘버 필요
               				//console.log(e); 모르겠으면 찍어보기
               				const targetId = e.currentTarget.id; // currentTarget 현재 요소가 선택이 됨. -> id속성에 접근
               				location.href = 'detail.board?boardNo='+ targetId;
               			});
               		})
               </script>               
            </div>         
            
         <div id="search-area" class="form-group">
         	<form action="search.board" method="get">
         		<input type="hidden" name="currentPage" value="1"/>
				<select class="form-control" name="condition">
					<option value="writer">작성자</option>
					<option value="content">내용</option>
					<option value="title">제목</option>
				</select>
				<input type="text" name="keyword" class="form-control"/>
				<button type="submit" class="btn btn-block">검색</button>
         	</form>
         </div>   
         <!--  마이바티스에서 강력한 요소 resultMap과 동적SQL,
         동적SQL을 사용하여 검색기능 구현 -->
         
         <!-- 
         <script>
         	window.onload = function(){
         		const currentUrl = window.location.href;
         		const urlObj = new URL(urrentUrl); // url을 인자로 넣어서 URL객체를 만들 수 있따.
         		const keyword = urlObj.searchParams.get('keyword');
         		console.log(`keyword : \${keyword}`);
         	}
         
         </script>
          -->
          
          <script>
          	window.onload = function(){
          		$('option[value="${map.condition}"]').prop('selected', true);
          		console.dir($('input[name="keyword"]')[0]);
          		$('input[name="keyword"]')[0].prop('val', ${map.keyword});
          		
          	}
          </script>
         
         
         
         
          
         <div class="paging-area" align="center" >
        		<!-- 이전페이지는 한칸씩 앞으로, 다음페이지는 바로 10페이지 다음으로 하도록 작성 -->
        		<c:if test="${pi.currentPage > 1 }">
	        	<button 
	       		class="btn btn-outline-primary" style="color:#52b1ff;"
	       		onclick="location.href='list.board?currentPage=${pi.currentPage - 1}'">이전</button>
	       		</c:if>
        
				<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }"
						   var="i">
				<c:choose>
					<c:when test="${ empty map }">
                <button 
	                class="btn btn-outline-primary" style="color:#52b1ff;"
	                onclick="location.href='list.board?currentPage=${i}'">${ i }</button>
	                </c:when>
	                <c:otherwise>
	                	<button 
	                class="btn btn-outline-primary" style="color:#52b1ff;"
	                onclick="location.href='search.board?currentPage=${i}&condition=${map.condition }&keyword=${map.keyword }'">${ i }</button>
	                </c:otherwise>
	                
	                
	            </c:choose>    
	                
	                
				</c:forEach>
				
				<c:if test="${ pi.endPage ne pi.maxPage }">
	        	<button 
	       		class="btn btn-outline-primary" style="color:#52b1ff;"
	       		onclick="location.href='list.board?currentPage=${ pi.getEndPage() + 1}'">다음</button>
	       		</c:if>
        	
        </div>
      
      
         </div>
      </div>
      
      
   </div>
   
     
     
   <jsp:include page="../include/footer.jsp"/>
