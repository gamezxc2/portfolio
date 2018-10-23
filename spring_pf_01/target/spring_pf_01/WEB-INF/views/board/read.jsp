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
		if(confirm("지울래여?")){
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
			document.readForm.action="<c:url value='/board/goUpdatePage.do?gridValue=0'/>";
			document.readForm.submit();
		})
		$('#btnDelete').on('click', function(){
			if(confirm("삭제하시겠습니까?")){
				document.readForm.action="<c:url value='/board/delete.do?gridValue=0'/>";
				document.readForm.submit();
			}
		})
		$('#btnRecmnd').on('click', function(){
			if(confirm("해당 게시글을 추천하시겠습니까?")){
				document.readForm.action="<c:url value='/board/recmnd.do?gridValue=0'/>";
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
						<input type="hidden" name="typeSeq" value="${board.type_seq}" />
						<fieldset>
							<legend>Sea Food 게시물 상세 내용</legend>

							<!-- board detail table -->
							<table summary="표 내용은 Ses & Food 게시물의 상세 내용입니다." class="board_detail_table">
								<caption>Sea Food 게시물 상세 내용</caption>
								<colgroup>
									<col width="15%" />
									<col width="35%" />
									<col width="15%" />
									<col width="10%" />
									<col width="15%" />
									<col width="10%" />
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
												<!--<c:out value='${ board.content }'/>-->
											<!--<p>곽현준(메탈배스)님은 대한민국 루어낚시계를 대표하는 홀리피셔맨으로서 생선회를 즐겨먹는 미식가로서
													회뜨는 솜씨도 거의 달인 수준입니다. 메탈배스님께서 노량진 수산시장에 뜨는 날에는 모든 칼잡이들이 꼬리를
													내리고 수족관 안으로 숨을 정도입니다.</p>
												<p class="mt_5">주로 태안의 갯바위에 거꾸로 매달리면서 마치 툼레이더의 안젤리나 졸리처럼
													공중 3회전을 하며 절벽 사이 사이를 날아다니면서 포인트의 우럭과 광어를 원샷 원킬로 타작하는 솜씨는
													용왕님께서도 인정하시고 태안의 어촌계장님도 인정하셨습니다.</p>
												<p class="mt_5">2015년에는 플라즈마 류하리그의 번쩍이는 섬광을 내면서 서해와 동해,
													남해권까지 모든 어종들의 버롯을 고쳐가며 다양한 어류생태에 대한 연구는 물론, 생선회를 활어회, 씽씽회,
													선어회, 삭힌회까지 새로운 패턴을 개발하여 비려서 회를 먹지 못하는 분들을 위해서 더욱 비린 맛을 내는
													감칠맛의 극치를 자랑하는 내장까지 팍팍 썩힌회를 제공할 예정입니다.</p>
												<p class="mt_5">아울러 올 여르에는 연못에 사는 붕어회 선물세트는 물론 미꾸라지
													세꼬시회까지 신제품으로 출시하여 많은 여성 미식가들을 공략하실 예정이며 회를 드시지 못하시는 분들을 훈계할
													계획이십니다.</p>-->
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //board detail table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_left">
									<input type="button" id="btnRecmnd" value="추천하기" title="추천하기" />
								</div>
								<div class="btn_bottom_right">
								<c:if test="${sessionScope.memberId == board.member_id}">
								<input type="button" id="btnUpdate" value="수정" title="수정"/>
								<input type="button" id="btnDelete" value="삭제" title="삭제"/>
								</c:if>
								<a href="<c:url value='/board/list.do'/>">
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