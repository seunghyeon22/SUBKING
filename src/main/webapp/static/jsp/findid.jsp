<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 페이지</title>

<link rel="stylesheet" href="../css/findid.css">

</head>
<body>
	<div class="login-container">
		<h2>아이디 찾기</h2>
		<p class="login-error">${ result }</p>
		<form method="POST">
			<div class="form-group">
				<label for="user_name">이름</label>
				<input type="text" id="user_name" name="user_name" placeholder="이름" required>
			</div>
			<div class="form-group">
				<label for="user_phone">휴대폰 번호</label>
				<input type="text" id="user_phone" name="user_phone" placeholder="휴대폰 번호" required>
			</div>
			<button type="submit" id="login">아이디 찾기</button>
			<div class="button-group">
				<button type="button">아이디 찾기</button>
				<button type="button" onclick="location.href='http://localhost:8080/240930subKingProject/static/jsp/findpw.jsp'">비밀번호 찾기</button>
				<!-- <button type="button">비밀번호 찾기</button> -->
				<button type="button">회원가입</button>
			</div>
		</form>
	</div>
	<script	src="../js/findid.js"></script>
</body>
</html>
