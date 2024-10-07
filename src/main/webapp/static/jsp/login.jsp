<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/static/css/login2.css">
    <title>로그인 페이지</title>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <p>${ result }</p>
        <form method="POST">
            <input type="text" name="username" placeholder="사용자 이름" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <button type="submit">로그인</button>
            <div class="button-group">
                <button type="button">아이디 찾기</button>
                <button type="button">비밀번호 찾기</button>
                <button type="button">회원가입</button>
            </div>
        </form>
    </div>
</body>
</html>