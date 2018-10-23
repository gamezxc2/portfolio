<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>게시글 수정하기</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	/*function doUpdate(){
		var frm = document.updateForm;
		
		var title = frm.title.value;
		if(title.length == 0){
			alert("제목을 입력하세요.");
			frm.title.focus();
			return;
		}
		frm.contents.value = ckEditor.getData();
		var contents = frm.contents.value;
		if(contents == '<p>&nbsp;</p>'){
			alert("내용을 입력하세요.");
			frm.contents.focus();
			return;
		}
		frm.action = "<c:url value='/board/update.do' />";
		frm.submit();
	}*/
	$(document).ready(function(){
		$('#btnUpdate').on('click', function(){
			var frm = document.updateForm;
			
			var title = $('#title').val();
			if(title.length == 0){
				alert("제목을 입력하세요.");
				frm.title.focus();
				return;
			}
			
			frm.contents.value = ckEditor.getData();
			var contents = frm.contents.value;
			if(contents == '<p>&nbsp;</p>'){
				alert("내용을 입력하세요.");
				frm.contents.focus();
				return;
			}
			frm.action = "<c:url value='/board/update.do' />";
			frm.submit();
		});
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

				<!-- title board write -->
				<div class="title_board_write">게시글 수정</div>
				<!-- //title board write -->

				<!-- board_area -->
				<div class="board_area">
					<form name="updateForm" method="post">
						<fieldset>
							<legend>게시글 수정</legend>

							<!-- board write table -->
							<table summary="표 내용은 게시글 수정 박스입니다."
								class="board_write_table">
								<caption>게시글 수정 박스</caption>
								<colgroup>
									<col width="20%" />
									<col width="80%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="tright"><label for="board_write_name">글번호</label></th>
										<td class="tleft">
										<c:if test="${gridValue != null }">
										<input type="hidden" name = "gridValue" value="${ gridValue }" />
										</c:if>
										<input type="hidden" name = "typeSeq" value="${ board.type_seq }" />
										<input type="hidden" name = "boardSeq" value="${ board.board_seq }" />
										<input type="text" id="name" title="글번호 입력박스" class="input_100" readonly="readonly" value="${ board.board_seq }"/>
										</td>
									</tr>
									<tr>
										<th class="tright"><label for="board_write_name">이름</label></th>
										<td class="tleft">
										<input type="text" id="memberNick" name="memberNick" title="이름 입력박스" class="input_100" readonly="readonly" value="${ sessionScope.memberNick }" />
										</td>
									</tr>
									
								    <tr>
										<th class="tright"><label for="board_write_title">제목</label></th>
										<td class="tleft">
										<input type="text" id="title" name="title" title="제목 입력박스" class="input_380" value="${ board.title }"/>
										</td>
									</tr>
									<tr>
										<th class="tright"><label for="board_write_title">내용</label></th>
										<td class="tleft">
											<div class="editer">
												<p>
												<textarea id="editor" name="contents" rows="20" cols="100" >${ board.content }</textarea>
												<script>
													var ckEditor;
    												ClassicEditor
       													 .create( document.querySelector( '#editor' ) )
       													 .then(editor => {
       														 ckEditor = editor;
       													 })
     													 .catch( error => {
           												 console.error( error );
        												} );
													</script>
												</p>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //board write table -->

							<!-- bottom button -->
							<div class="btn_bottom">
								<div class="btn_bottom_right">
									<a href="<c:url value='/board/list.do' />">
									<input type="button" value="목록으로" title="목록으로" /></a>
									<input type="button" id="btnUpdate" value="완료" title="완료" />
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