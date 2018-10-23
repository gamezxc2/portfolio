<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jqgrid/css/ui.jqgrid.css" />" />
<style type="text/css">
.board_search{
	margin-bottom: 5px;
	text-align: right;
	margin-right: -7px;
}
.board_search .btn_search, #btnDelete{
	height: 20px;
	line-height: 20px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #e9e9e9;
	background-color: #f7f7f7;
	font-size: 10px;
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
/*	var myData = [{'CategoryName':'IT Device', 
				   'ProductName':'iPhone XS',
				   'Country':'KOR',
				   'Price':'20000',
				   'Quantity':'10' },
				  {'CategoryName':'IT Device', 
			 	   'ProductName':'iPhone 5S',
				   'Country':'USA',
				   'Price':'3500',
				   'Quantity':'15' }]; */
	$("#jqGrid").jqGrid({
		url: '<c:url value="/member/getListData.do"/>',
		datatype: "json",
		jsonReader: { id: "member_idx"},
		prmNames: {id: 'member_idx'}, //PK 컬럼 지정 
//		datatype : 'local',
//		data : myData,
		colModel: [
			{ label: 'No', name: 'member_idx', width: 15, sorttype: 'number' },
			{ label: '회원ID', name: 'memberId', width: 70 },
			{ label: '회원 이름', name: 'memberName', width: 80},
			{ label: '닉네임', name: 'memberNick', width: 40 },
			{ label: 'E-mail', name: 'email', width: 80},
			// sorttype is used only if the data is loaded locally or loadonce is set to true
			{ label: '가입일', name: 'createDate', width: 50 }                   
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		width: 740,
		height: 200,
		rowNum: 8, //가져올 게시글 수 (기본)
		rowList: [5, 10, 15], // 가져올 게시글 수 (선택)
		caption : '회원 목록', // 그리드 제목
		rownumbers : true, //그리드 목록에 대한 순번
		sortname : "member_idx", //기본 정렬 컬럼
		sortorder : "desc", //기본 정렬
		scrollrows : true,  //스크롤 유무
		viewrecords : true, //row 보기
		pager: "#jqGridPager"
	});
	//navButtons
	$("#jqGrid").jqGrid('navGrid',"#jqGridPager",
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
			caption : '회원정보 삭제',
			msg: "선택하신 회원을 삭제 하시겠습니까?",
			width : 250,
			recreateForm: true,
			url: '<c:url value="/member/delMember.do"/>',
			beforeShowForm : function(e){
				var form = $(e[0]);
				return;
			},
			//보내기 전
			beforeSubmit : function(post){
				//post : 자율 key 값
				console.log('beforeSubmit : '+post);
				//post로 row 전체 값 가져오기 , return type = Object
				var rowData = $("#jqGrid").jqGrid('getRowData',post);
				console.log(rowData);
				var loginId = '${sessionScope.memberId}';
				if(loginId == rowData.memberId){
					return [false, "자기 자신을 지울 수 없습니다."];
				} 
				return[true, ""];
			},
			//완료된 후
			afterComplete : function(result, postdata){
				alert(result.responseJSON.msg);
			}
		}
	);
	//$("#btnDelete").click(function(){ });
	$("#btnDelete").on('click', function(){
		//selrow : 선택한 Row, getGridParam : 그리드 파라미터 가져오기
		var rowId = $('#jqGrid').jqGrid('getGridParam','selrow');
		//선택한 행(ROW)가 있다면 rowID가 무조건 존재함
		console.log("rowID : "+rowId);
		if(rowId == null){
			alert("삭제할 row를 선택하세요");
			return;
		} else {
			var rowData = $('#jqGrid').jqGrid('getRowData', rowId);
			console.log(rowData);
			$.ajax({
				url : '<c:url value="/member/delMember.do"/>',
				//URL에 보낼 데이터
				data : { member_idx : rowData.member_idx },
				success : function(result, textStatus, XMLHttpRequest){ //통신 자체가 성공했을 경우
					console.log(result);
					alert(result.msg);
					//그리드 데이터 새로고침
					$("#jqGrid").jqGrid('setGridParam',{page : 1}).trigger("reloadGrid");
				},
				//success 끝
				error : function(e) { // 통신 실패.
					alert(e);
				}
				//error 끝
			})	 
		}
	});
	$("#btnSearch").click(function(){ 
		var searchType = $("#searchType option:selected").val();
		var searchText = $("#searchText").val();
		$("#jqGrid").jqGrid('setGridParam',{
			postData : {
				searchType : searchType,
				searchText : searchText
			}, 
			page : 1
		}).trigger('reloadGrid');
		$.ajax({
			url : '<c:url value="/member/getListData.do"/>',
			success : function(result, textStatus, XMLHttpRequest){ //통신 자체가 성공했을 경우
				alert("검색 결과를 표시합니다.");
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
		<li><a href="#tabs-1">회원 목록</a></li>
	</ul>
	<div id="tabs-1">
		<!-- content -->
		<div id="content">

			<!-- board_search -->
			<div class="board_search">
				<form name="searchForm" method="get">
					<select id="searchType" name="searchType" title="선택메뉴">
						<option value="1" selected="selected">전체</option>
						<option value="2">ID</option>
						<option value="3">이메일</option>
					</select> 
					<input type="text" id="searchText" name="searchText" title="검색어 입력박스" class="input_100" /> 
					<input type="button" id="btnSearch" value="검색" title="검색버튼" class="btn_search" />
				</form>
			</div>
			
			<!-- //board_search -->

			<!-- board_area -->
			<div class="board_area board_search">
				<button id="btnDelete" style="margin-bottom:3px;">삭제</button>
				<table id="jqGrid"></table>
  				<div id="jqGridPager"></div>
			</div>
			<!-- //board_area -->

		</div>
		<!-- //content -->
	</div>
</div>
</body>
</html>