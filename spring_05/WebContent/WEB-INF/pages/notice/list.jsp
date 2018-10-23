<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시물 목록페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script type="text/javascript">
	var msg = "${msg}";
	function init(){
		if(msg != "") alert(msg);
	}
</script>
</head>
<body onload="init()">

	<!-- wrap -->
	<div id="wrap">

		<!-- container -->
		<div id="container">

			<!-- content -->
			<div id="content">

				<!-- board_search -->
				<div class="board_search">
					<select title="선택메뉴">
						<option selected="selected">전체</option>
						<option>제목</option>
						<option>내용</option>
					</select> 
					<input type="text" title="검색어 입력박스" class="input_100" /> 
					<input type="button" value="검색" title="검색버튼" class="btn_search" />
				</div>
				<!-- //board_search -->

				<!-- board_area -->
				<div class="board_area">
					<form method="get">
						<fieldset>
							<legend>Sea Food 게시물 목록</legend>
							<!-- board list table -->
							<table summary="표 내용은 Ses & Food 게시물의 공지사항입니다." class="board_list_table">
								<caption>Sea Food 게시물 목록</caption>
								<colgroup>
									<col width="6%" />
									<col width="40%" />
									<col width="15%" />
									<col width="20%" />
									<col width="7%" />
									<col width="7%" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">번호</th>
										<th scope="col">제목</th>
										<th scope="col">작성자</th>
										<th scope="col">작성일</th>
										<th scope="col">조회</th>
										<th scope="col">추천</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ list }" var="row">
									<tr>
										<td>${row.board_seq }</td>
										<td class="tleft">
										<span class="bold"><a href="<c:url value="/notice/read.do?boardSeq=${row.board_seq}&typeSeq=1"/>">
										${row.title}</a></span>
										</td>
										<td>${row.member_nick }</td>
										<td>${row.create_date }</td>
										<td class="tright">${row.hits }</td>
										<td class="tright">0</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- //board list table -->

							<!--paginate start -->
							<div class="paginate">
								<!-- Start블럭이 1이 아니라면 이전 페이지 버튼 출력
									 다른 방법 : 현재 페이지, 스타트블럭이  블럭사이즈보다 작을 경우 -->
								<c:if test="${ pageBlockStart != 1 }" >
								<a class="pre" href="<c:url value='/notice/list.do?currentPage=${pageBlockStart-1}' />">이전페이지</a>
								</c:if>
								<c:forEach begin="${ pageBlockStart }" end="${ pageBlockEnd }" step="1" var="pageNo">
									<c:choose>
										<c:when test="${ pageNo == currentPage }">
										<strong>${currentPage }</strong>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/notice/list.do?currentPage=${pageNo}' />">
												${ pageNo }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${ pageBlockEnd != totalPageCnt }" >
								<a class="next" href="<c:url value='/notice/list.do?currentPage=${pageBlockEnd +1}' />">다음페이지</a>
								</c:if>
							</div>
							<!--//paginate end -->

							<!-- bottom button -->
							<c:choose>
							<c:when test="${ sessionScope.memberId != null }">
							<div class="btn_bottom">
								<div class="btn_bottom_right">
									<c:if test="${ sessionScope.typeSeq == 9 }">
									<a href="<c:url value='/notice/goWritePage.do'/>">
										<input type="button" value="글쓰기" title="글쓰기" />
									</a>
									</c:if>
								</div>
							</div>
							</c:when>
							</c:choose>
							<!-- //bottom button -->

						</fieldset>
					</form>
				</div>
				<!-- //board_area -->

			</div>
			<!-- //content -->

		</div>
		<!-- //container -->

	</div>
	<!-- //wrap -->

</body>
</html>