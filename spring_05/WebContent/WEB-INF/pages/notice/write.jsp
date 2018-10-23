<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>공지사항 작성하기</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />" />
<script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	/*function init(){
		alert('초기 펑션 실행');
	}
	$(document).ready(init); //onload 의 기능을 대신함. */
	
	$(document).ready(function(){
		console.log('브라우저 디버깅 수동 로그');
		
		//jQuery 이벤트 (click) 처리
		$('#btnWrite').on('click', function(){
			var frm = document.writeForm;
			var title = $('#title').val(); //var title = document.writeForm.title.value;
			if(title.length == 0){
				alert("제목을 입력하세요.");
				$('#title').focus();  //document.writeForm.title.focus();
				return;
			}
			
			frm.contents.value = ckEditor.getData();
			var conts = frm.contents.value;
			if(conts == '<p>&nbsp;</p>'){
				alert("내용을 입력하세요.");
				return;
			}
			frm.action = "<c:url value='/notice/write.do' />";
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
				<div class="title_board_write">글쓰기</div>
				<!-- //title board write -->

				<!-- board_area -->
				<div class="board_area">
					<form name="writeForm" method="post">
						<fieldset>
							<legend>Sea Food 글쓰기</legend>

							<!-- board write table -->
							<table summary="표 내용은 Sea Food 글쓰기 박스입니다."
								class="board_write_table">
								<caption>Sea Food 글쓰기 박스</caption>
								<colgroup>
									<col width="20%" />
									<col width="80%" />
								</colgroup>
								<tbody>

									<tr>
										<th class="tright"><label for="board_write_name">이름</label></th>
										<td class="tleft">
										<input type="hidden" name="typeSeq" value="1" />
										<input type="hidden" name="memberId" value="${sessionScope.memberId }" />
										<input type="text" id="memberNick" name="memberNick" title="이름 입력박스" class="input_100" readonly="readonly" value="${ sessionScope.memberNick }" />
										</td>
									</tr>

									<tr>
										<th class="tright"><label for="board_write_title">제목</label></th>
										<td class="tleft">
										<input type="text" id="title" name="title" title="제목 입력박스" class="input_380" />
										</td>
									</tr>
									<tr>
										<th class="tright"><label for="board_write_title">내용</label></th>
										<td class="tleft">
											<div class="editer">
												<p>
													<textarea id="editor" name="contents" rows="20" cols="100"></textarea>
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
									<input type="button" value="작성취소" title="작성취소" onclick="javascript:window.history.back();" /> 
									<input type="button" id="btnWrite" value="완료" title="완료" />
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