<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/common/css/common.css" />" />

<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-3.2.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery/js/jquery-migrate-1.4.1.js" />"></script>

<script type="text/javascript">
var adjustSize = false; // 변수를 선언하여 1번만 실행하도록 할 변수
$(document).ready(function(){
	$('#erdDialog').hide();
	
	//Tab
	$( "#tabs" ).tabs();
	
	$('#btnErd').on('click', function(){ 
		$('#erdDialog').dialog({//Dialog창을 띄운다.
			//my : dialog 위치
			//at : dialog(↑) 왼쪽 상단 모서리 위치
			//of : 위치시킬 element (해당 창의 기준으로 위치를 잡음)
			// center top : 가로 center 세로 top
			position : { my:"center top", at:"top", of:"#tabs-1" } ,
			open : function(){
				$('#erdDialog').show();
				if(!adjustSize){
					$('#erdDialog').dialog('option', 'width', $('#erdImage').width()*1.1);
					$('#erdDialog').dialog('option', 'height', $('#erdImage').heiget()*1.1);
					$('#erdImage').css({width:'100%'});
					adjustSize = true;
				}	
			}
		});
		//{ key1 : value1, key2 : value2 }
	});
	
	$('#btnNote1').on('click', function(){ 
		window.open('https://www.evernote.com/l/AfxOm_Q5SKlAKqEe7R_vYg5RVqV1miZnpMk/','_blank');
	});
	$('#btnNote2').on('click', function(){ 
		window.open('https://www.evernote.com/l/AfxHW7i2nbVCcL_7eY98jXi9npZoZpbF5KM/','_blank');
	});
	$('#btnNote3').on('click', function(){ 
		window.open('https://www.evernote.com/l/AfzB2n77tjpKLa50UCcCsMh0M7gbLpmMHUw/','_blank');
	});
	$('#btnNote4').on('click', function(){ 
		window.open('https://www.evernote.com/l/AfzZymYPYdhBwoLvr_kRLGc_U_V6uSyMY5U/','_blank');
	});
});

</script>
<title></title>
</head>
<body>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Notes</a></li>
	</ul>
	<div id="tabs-1">
  		<ul class="page_desc">
			<li>사이트 DB 모델링(JPG)
				<input type="button" id="btnErd" value="보기"/>
				<br/><br/>
			</li>
			<li>사이트 DB 모델링(MWB)
				<a href="<c:url value='/file/downloadErd.do' />">
				<input type="button" value="다운로드" /></a>
				<br/><br/>
			</li>
			<li>강의 내용 정리 - DML
				<input type="button" id="btnNote1" value="이동" />
				<br/><br/>
			</li>
			<li>강의 내용 정리 - JSTL
				<input type="button" id="btnNote2" value="이동" />
				<br/><br/>
			</li>
			<li>강의 내용 정리 - 효율적인 템플릿 적용
				<input type="button" id="btnNote3" value="이동" />
				<br/><br/>
			</li>
			<li>MyBatis Query문 정리
				<input type="button" id="btnNote4" value="이동" />
				<br/><br/>
			</li>
		</ul>
		<br/>
	</div>
</div>

<div id="erdDialog" title="모델링 JPG">
	<img id="erdImage" src='<c:url value="resources/erd.jpg"  />' />
</div>

</body>
</html>