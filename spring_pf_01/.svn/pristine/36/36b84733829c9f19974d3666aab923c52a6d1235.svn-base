<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시물 상세페이지</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
/*	var msg = "${msg}";
	//EL의 읽어오는 속도는 파일이 실행되기 전에 불러오므로  var msg = 삭제 실패! 라는 변수를 입력하는 것이 되어 오류가 뜸. 따옴표 붙힐것
	function init(){
		if(msg != ""){
			alert(msg);    -> body태그의 onload사용시 
		}
	}
	function del(){
		if(confirm("삭제 하시겠습니까?")){
			var frm = document.readForm;
			frm.action="<c:url value='/board/delete.do'/>";
			frm.submit();
		}
	}
	function goUpdatePage(){
		var frm = document.readForm;
		frm.action="<c:url value='/board/goUpdatePage.do'/>";
		frm.submit();
	} */
	$(document).ready(function(){
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
		
		$('#btnUpdate').on('click', function(){
			document.readForm.action="<c:url value='/notice/goUpdatePage.do'/>";
			document.readForm.submit();
		})
		$('#btnDelete').on('click', function(){
			if(confirm("삭제하시겠습니까?")){
				document.readForm.action="<c:url value='/notice/delete.do'/>";
				document.readForm.submit();
			}
		})
		$('#btnRecmnd').on('click', function(){
			if(confirm("해당 게시글을 추천하시겠습니까?")){
				document.readForm.action="<c:url value='/notice/recmnd.do'/>";
				document.readForm.submit();
			}
		})
	})
</script>
</head>
<body>

	<!-- wrap -->
	<div id="wrap">

		<!-- container -->
		<div id="container">

			<!-- content -->
			<div id="content">

				<!-- title board detail -->
				<div class="title_board_detail">게시물 보기</div>
				<!-- //title board detail -->

				<!-- board_area -->
				<div class="board_area">
					<form name="readForm" method="post">
						<!-- URL에 파라미터를 적는 거보단 hidden 태그를 이용하여 파라미터를 입력해서 보내줌. -->
						<input type="hidden" name="memberId" value="${board.member_id}" />
						<input type="hidden" name="boardSeq" value="${board.board_seq}" />
						<input type="hidden" name="hasFile" value="${board.has_file }" />
						<input type="hidden" name="typeSeq" value="${board.type_seq}" />
						<fieldset>
							<legend>게시물 상세 내용</legend>

							<!-- board detail table -->
							<table summary="표 내용은 게시물의 상세 내용입니다." class="board_detail_table">
								<caption>게시물 상세 내용</caption>
								<colgroup>
									<col width="12%" />
									<col width="29%" />
									<col width="12%" />
									<col width="20%" />
									<col width="12%" />
									<col width="15%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="tcenter">제목</th>
										<td colspan="5" class="tleft">${ board.title }</td>
									</tr>
									<tr>
										<th class="tcenter">작성자</th>
										<td colspan="5" class="tleft">${ board.member_nick }</td>
									</tr>
									<tr>
										<th class="tcenter">작성일</th>
										<td>${ board.create_date }</td>
										<th class="tcenter">조회수</th>
										<td class="tright">${ board.hits }</td>
										<th class="tcenter">추천수</th>
										<td class="tright">${ board.recmnd }</td>
									</tr>
									<tr>
										<td colspan="6" class="tleft">
											<div class="body">
												${ board.content }
												<!--<c:out value='${ board.content }'/>
													태그 등을 그대로 표시하게 할 경우 C태그로 출력 -->
											</div>
										</td>
									</tr>
									<c:if test="${empty files}">
										<tr>
											<th class="tcenter">첨부파일</th>
												<td colspan="3" style="text-align: left">
												</td>
										</tr>
									</c:if>
									<c:forEach items="${files}" var="file" varStatus="vs">
										<tr>
											<th class="tcenter">첨부파일${vs.count}</th>
											<td colspan="3" style="text-align: left">
												<c:choose>
													<c:when test="${file.linked == 0}">
													${file.filename} (서버에 파일을 찾을 수 없습니다.)
													</c:when>
													<c:otherwise>
														<a href="<c:url value="/notice/download.do?fileIdx=${file.file_idx}"/>">
														${file.filename}(${file.file_size} bytes)
														</a>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- //board detail table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_left">
									<input type="button" id="btnRecmnd" value="추천하기" title="추천하기" />
								</div>
								<div class="btn_bottom_right">
								<c:if test="${sessionScope.typeSeq == 9}">
								<input type="button" id="btnUpdate" value="수정" title="수정"/>
								<input type="button" id="btnDelete" value="삭제" title="삭제"/>
								</c:if>
								<a href="<c:url value='/notice/list.do'/>">
								<input type="button" value="목록" title="목록" />
								</a>
								</div>
							</div>
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