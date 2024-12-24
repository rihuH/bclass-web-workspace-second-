<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
header.masthead {
	display: none;
}

.row {
	height: 800px;
}

tr:hover {
	cursor: pointer;
}
</style>

<br />
<br />

<jsp:include page="../include/header.jsp" />

<!-- Begin Page Content -->
<div class="container">
	<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-10">
			<div class="panel-body">
				<h2 class="page-header">
					<span style="color: #52b1ff;">KH</span> 공지사항 게시판
					<c:if test="${ loginUser.userNo eq 1 }">
						<a href="enrollForm.notice" class="btn float-right"
							style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
					</c:if>
				</h2>
				<table class="table table-bordered table-hover">
					<thead>
						<tr
							style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							<th width="100">번호</th>
							<th width="150">작성자</th>
							<th width="450">제목</th>
							<th width="200">작성일</th>
							<th width="100">조회수</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${ list }" var="n">
							<tr style="color: #52b1ff;" class="board" id="${ n.noticeNo }">
								<td>${ n.noticeNo }</td>
								<td>${ n.noticeWriter }</td>
								<td style="color: #52d6ffcc;"><a
									href="detail.notice?noticeNo=${ n.noticeNo }"> ${ n.noticeTitle }
										&nbsp; </td>
								<td>${ n.createDate }</td>
								<td>${ n.count }</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="paging-area" align="center">

				<c:if test="${ pi.currentPage ne 1 }">
					<button class="btn btn-outline-primary" style="color: #52b1ff;"
						onclick="location.href='list.notice?currentPage=${ pi.currentPage - 1 }'">이전</button>
				</c:if>

				<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }" var="i">
					<button class="btn btn-outline-primary" style="color: #52b1ff;"
						onclick="location.href='list.notice?currentPage=${i}'">${ i }</button>
				</c:forEach>

				<c:if test="${ pi.currentPage ne pi.maxPage }">
					<button class="btn btn-outline-primary" style="color: #52b1ff;"
						onclick="location.href='list.notice?currentPage=${ pi.endPage + 1 }'">다음</button>
				</c:if>

			</div>
		</div>
	</div>
</div>







<jsp:include page="../include/footer.jsp" />