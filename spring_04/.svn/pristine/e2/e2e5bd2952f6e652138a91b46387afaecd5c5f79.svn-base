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
	// function 함수명(매개변수 ....){    }
	function doJoin(){
		//var joinForm = document.getElementsByName("joinForm")[0]; 
		var joinForm = document.joinForm;
		var userId = joinForm.memberId.value; //joinForm의 userID의 값을 입력
		var userName = joinForm.memberName.value; 
		var pw = joinForm.memberPw.value; 
		
		if(userId == ""){ //입력하지 않았을 경우
			alert("ID를 입력하세요.");
			joinForm.memberId.focus;
			return; //이 시점에서 종료하고 싶을 경우
		}
		
		if(userId.length < 7 || userId.length > 12){ //입력하지 않았을 경우
			alert("ID는 7자 이상 12자 이하.");
			joinForm.memberId.focus;
			return; //이 시점에서 종료하고 싶을 경우
		}
		
		if(pw == ""){ //입력하지 않았을 경우
			alert("비밀번호를 입력하세요.");
			joinForm.memberPw.focus;
			return; //이 시점에서 종료하고 싶을 경우
		}
		
		var pw2 = joinForm.memberPw2.value;
		if(pw2 == ""){
			alert("암호 확인을 입력하세요")
			document.getElementById("userPw2").focus();
			return;
		}
		if(pw != pw2){
			alert("암호가 일치하지 않습니다.");
			joinFrom.userPw.focus;
			return;
		}
		
		if(userName == ""){
			alert("이름을 입력하세요.");
			joinForm.memberName.focus;
			return;
		}
		var userNick = joinForm.memberNick.value;
		if(userNick == ""){
			alert("닉네임을 입력하세요");
			return;
		}
		if(userNick.length > 11 ) {
			alert("닉네임은 12자 이하");
			return;
		}
		var birth = joinForm.birthDate.value;
		if(birth.charAt(4) != '-' || birth.charAt(7) != '-'){
			alert("yyyy-mm-dd 형식");
			return;
		}
				
		joinForm.action = "/spring_04/member/join.do";
		joinForm.method = "POST";
		joinForm.submit(); // FORM 태그에서 action하는 것이 아닌 유효성 검사후 sub하도록 설정.
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
				<c:if test="${ msg!= null }">
				<b><font color="red">${ msg }</font></b>
				</c:if>
			</div>
			<!-- 로그인 -->
			<div class="form-content">
				<form action="/spring_04/member/login.do" method="post" name="loginForm">
					<div class="form-group">
						<label for="username">User Id</label> 
						<input type="text" id="" name="memberId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="" name="memberPw" required="required" />
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
						<label for="username">Member Id</label> 
						<input type="text" id="" name="memberId" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password</label> 
						<input type="password" id="" name="memberPw" required="required" />
					</div>
					<div class="form-group">
						<label for="cpassword">Confirm Password</label> 
						<input type="password" id="memberPw2" required="required" />
					</div>
					<div class="form-group">
						<label for="name">Member Name</label> 
						<input type="text" id="" name="memberName" required="required" />
					</div>
					<div class="form-group">
						<label for="nickname">Nick Name</label> 
						<input type="text" id="" name="memberNick" required="required" />
					</div>
					<div class="form-group">
						<label for="nickname">e-mail</label> 
						<input type="email" id="" name="email" required="required" />
					</div>
					<div class="form-group">
						<label for="nickname">Birth Date</label> 
						<input type="text" id="" name="birthDate" value="yyyy-mm-dd" required="required" />
					</div>
					<div class="form-group">
						<button type="button" onclick="doJoin();">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>