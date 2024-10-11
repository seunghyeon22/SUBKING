<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 재설정</title>

<link rel="stylesheet" href="../css/resetpw.css">

</head>
<body>
	<div class="login-container">
		<h2>비밀번호 재설정</h2>
		<p class="login-error">${ result }</p>
		<form method="POST">
			<div class="form-group">
				<label for="user_phone">비밀번호 재설정</label>
				<input type="password" id="pw1" name="pw1" placeholder="비밀번호 재설정" required>
			</div>
			<div class="form-group">
				<label for="user_name">비밀번호 확인</label>
				<input type="password" id="pw2" name="pw2" placeholder="비밀번호 확인" required>
				<span class="error_next_box"></span>
			</div>
			<button type="submit" id="login">비밀번호 설정하기</button>
			<div class="button-group">
				<button type="button">아이디 찾기</button>
				<button type="button">비밀번호 찾기</button>
				<button type="button">회원가입</button>
			</div>
		</form>
	</div>
	<script	src="../js/resetpw.js"></script>
</body>
</html>
