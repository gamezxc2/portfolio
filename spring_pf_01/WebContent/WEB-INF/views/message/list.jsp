<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jqgrid/css/ui.jqgrid.css" />" />
<style type="text/css">
.board_search {
	margin-bottom: 5px;
	text-align: right;
	margin-right: -7px;
}
.board_search .btn_search, #btnWrite , #btnAllView{
	height: 20px;
	line-height: 20px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #e9e9e9;
	background-color: #f7f7f7;
	font-size: 12px;
	font-family: Dotum, "돋움";
	font-weight: bold;
	text-align: center;
	cursor: pointer;
}
select {
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left : 3px;
	padding: 0;
	font-size: 12px;
	height: 20px;
	line-height: 20px;
	border: 1px solid #d7d7d7;
	color: #7f7f7f;
	/* padding: 0 5px; */
	vertical-align: middle;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqgrid/js/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqgrid/js/i18n/grid.locale-kr.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-migrate-1.4.1.js" />"></script>

<script type="text/javascript">

$(document).ready(function(){
	// Tab
	$( "#tabs" ).tabs();
	var msg = "${msg}";
	if(msg != "") alert(msg);
	
	$("#boardGrid").jqGrid({
		url: '<c:url value="/message/getList.do"/>',
		datatype: "json",
		jsonReader: { id: "mess_idx"}, //Id를 board_seq로 사용하겠다 선언.
		prmNames: {id: 'mess_idx'}, //PK 컬럼 지정 
//		datatype : 'local',
//		data : myData,
		colModel: [
			{ label: 'No', name: 'mess_idx', width: 10, sorttype: 'number' , align:'center'},
			{ label: '송신ID', name: 'send_id', width: 30 , align:'center' },
			{ label: '송신자', name: 'send_nick', width: 20 , align:'center' },
			{ label: '수신ID', name: 'receive_id', width: 30 , align:'center' },
			{ label: '수신자', name: 'receive_nick', width: 20 , align:'center' },
			{ label: '내용', name: 'content', width: 100 },
			{ label: '송신일시', name: 'send_date', width: 45, align:'center' }      
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		width: 740,
		height: 200,
		rowNum: 5, //가져올 게시글 수 (기본)
		rowList: [5, 10, 15], // 가져올 게시글 수 (선택)
		caption : '메시지 목록', // 그리드 제목
		rownumbers : false, //그리드 목록에 대한 순번
		sortname : "mess_idx", //기본 정렬 컬럼
		sortorder : "desc", //기본 정렬
		scrollrows : true,  //스크롤 유무
		viewrecords : true, //row 보기
		pager: "#boardGridPager",
		onCellSelect : function(rowId, colIdx, content, event){ // 셀 이벤트
			if(colIdx == 3 || colIdx == 1){
				var url = "<c:url value='/message/goWritePage.do?receiveId="+ content + "'/>";
				window.location.href = url;
			}
		}
		
	});
	
	//navButtons
	$("#boardGrid").jqGrid('navGrid',"#boardGridPager",
		{// navbar options
			edit : false,
			add : false,
			del : true,
			search : false,
			refresh : true,
			view : true
		},
		{}, //edit
		{}, //add
		{   //del
			caption : '메시지 삭제',
			msg: "선택한 메시지를 삭제 하시겠습니까?",
			width : 250,
			recreateForm: true,
			url: '<c:url value="/message/delete.do"/>',
			beforeShowForm : function(e){
				var form = $(e[0]);
				return;
			},
			//완료된 후
			afterComplete : function(result, postdata){
				alert(result.responseJSON.msg);
			}
		}
	);
	
	$("#btnSearch").click(function(){ 
		var searchType = $("#searchType option:selected").val();
		$("#boardGrid").jqGrid('setGridParam',{
			postData : {
				searchType : searchType
			}, 
			page : 1
		}).trigger('reloadGrid');
		$.ajax({
			url : '<c:url value="/message/getList.do"/>',
			success : function(result, textStatus, XMLHttpRequest){ //통신 자체가 성공했을 경우
				alert("메시지를 표시합니다.");
			},
			//success 끝
			error : function(e) { // 통신 실패.
			}
			//error 끝
		})
		
	});
	$("#btnWrite").click(function(){ 
		var url = "<c:url value='/message/goWritePage.do'/>";
		window.location.href = url;
	});
	
	$("#btnAllView").click(function(){ 
		var searchType = $("#searchType option:selected").val();
		$("#boardGrid").jqGrid('setGridParam',{
			postData : {
				searchType : 4
			}, 
			page : 1
		}).trigger('reloadGrid');
		$.ajax({
			url : '<c:url value="/message/getList.do"/>',
			success : function(result, textStatus, XMLHttpRequest){ //통신 자체가 성공했을 경우
				alert("모든 메시지를 표시합니다.");
			},
			//success 끝
			error : function(e) { // 통신 실패.
			}
			//error 끝
		})
	});
	
});
</script>
<title></title>
</head>
<body>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">메시지</a></li>
	</ul>
	<div id="tabs-1">
		<!-- content -->
		<div id="content">

			<!-- board_search -->
			<div class="board_search">
				<form name="searchForm" method="get">
					<select id="searchType" name="searchType" title="선택메뉴">
						<option value="1" selected="selected">전체</option>
						<option value="2">수신</option>
						<option value="3">송신</option>
					</select>
					<input type="button" id="btnSearch" value="조회" title="조회버튼" class="btn_search" />
				</form>
			</div>
			
			<!-- //board_search -->

			<!-- board_area -->
			<div class="board_area board_search">
				<table id="boardGrid"></table>
  				<div id="boardGridPager"></div>
  				<c:if test="${ sessionScope.typeSeq == 9 }">
  				<button id="btnAllView" style="margin-bottom:3px;">전부 보기</button>
  				</c:if>
  				<c:if test="${ sessionScope.memberId != null }">
				<button id="btnWrite" style="margin-bottom:3px;">작성</button>
				</c:if>
			</div>
			<!-- //board_area -->

		</div>
		<!-- //content -->
	</div>
</div>	
</body>
</html>