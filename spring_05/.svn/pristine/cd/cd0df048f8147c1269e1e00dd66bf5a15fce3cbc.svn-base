<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- tag library 선언 : c tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<title>로그인  회원가입</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var panelOne = $('.form-panel.two').height(), 
			panelTwo = $('.form-panel.two')[0].scrollHeight;
	
		$('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
			e.preventDefault();
	
		    $('.form-toggle').addClass('visible');
		    $('.form-panel.one').addClass('hidden');
		    $('.form-panel.two').addClass('active');
		    $('.form').animate({
		      'height': panelTwo
		    }, 200);
		});
	
		$('.form-toggle').on('click', function(e) {
		    e.preventDefault();
		    $(this).removeClass('visible');
		    $('.form-panel.one').removeClass('hidden');
		    $('.form-panel.two').removeClass('active');
		    $('.form').animate({
		      'height': panelOne
		    }, 200);
		});
	});
	
	function doJoin(){

		var userId = document.getElementById("j_userId").value;
		if(userId == undefined || userId == ''){
			alert("ID를 입력하세요.");
			document.getElementById("j_userId").focus();
			return;
		}
		var userPw = $('#j_userPw').val();
		if(userPw == undefined || userPw == ''){
			alert("비밀번호를 입력하세요.");
			$('#j_userPw').focus();
			return;
		}
		// 비밀번호와 비밀번호확인 값이 같은지 확인
		if(pw1 != pw2){
			alert("비밀번호가 다름");
			return;
		}
				
		// ajax 이용하여 ID중복 확인 후 중복 아닐 때 폼 전송
		$.ajax({
			url: '<c:url value="/chkId.do"/>',
			type: "post",
			data: {'userId' : userId, 'abc' : 'def'},	
			success: function(result, textStatus, jqXHR) {
				if(result == 0){	// 중복 안됨
					var frm = document.joinForm;
					frm = $('form[name=joinForm]')[0];
					frm.action = '<c:url value="/join.do"/>';
					frm.method = 'post';
					frm.submit();
				}
				else {	// 중복 또는 문제가 있든...
					// ID가 중복됩니다. (경고창)
					alert("ID가 중복됩니다.")
					// 커서를 Id입력하는 곳으로 이동
					$('#j_userId').focus();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
</script>
</head>
<body>
	<!-- 디자인 출처 : http://www.blueb.co.kr/?c=2/34&where=subject%7Ctag&keyword=%EB%A1%9C%EA%B7%B8%EC%9D%B8&uid=4050 -->
	<!-- Form-->
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1>Account Login</h1>
				<font color="red"><b>${msg}</b></font>
			</div>
			<!-- 로그인 -->
			<div class="form-content">
				<form action="<c:url value='/login.do'/>" method="post">
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="userId" name="userId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="userPw" name="userPw" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> 
							<input type="checkbox" />Remember Me
						</label>
						<a href="#" class="form-recovery">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit">Log In</button>
					</div>
				</form>
			</div>
		</div>
		
		<!-- 회원가입 -->
		<div class="form-panel two">
			<div class="form-header">
				<h1>Register Account</h1>
			</div>
			<div class="form-content">
				<form name="joinForm">
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="j_userId" name="j_userId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="j_userPw" name="j_userPw" required="required" />
					</div>
					<div class="form-group">
						<label for="cpassword">Confirm Password</label> 
						<input type="password" id="j_cUserPw" required="required" />
					</div>
					<div class="form-group">
						<label for="name">Name</label> 
						<input type="text" id="j_name" name="j_name" required="required" />
					</div>
					<div class="form-group">
						<label for="nickname">Nickname</label> 
						<input type="email" id="j_nickname" name="j_nickname" required="required" />
					</div>
					<div class="form-group">
						<button type="button" onclick="doJoin()">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>