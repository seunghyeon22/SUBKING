<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Subway Header</title>
<link rel="stylesheet" type="text/css" href="../static/css/subking.css">
</head>
<body>
	<header class="header">
		<div class="logo">
			<img src="../static/image/logo.jpg"
					alt="햄버거 이미지">
		</div>
		<nav>
			<div class="menu-item">
				<a href="${ pageContext.request.contextPath }/custom/home">Home</a>
<!-- 				<div class="dropdown"> -->
<!-- 					<a href="#">Home Submenu 1</a> <a href="#">Home Submenu 2</a> -->
<!-- 				</div> -->
			</div>
			<div class="menu-item">
				<a href="${ pageContext.request.contextPath }/custom/custom">Custom</a>
<!-- 				<div class="dropdown"> -->
<!-- 					<a href="#">Submenu 1</a> <a href="#">Submenu 2</a> <a href="#">Submenu	3</a> -->
<!-- 				</div> -->
			</div>
			<div class="menu-item">
				<a href="${ pageContext.request.contextPath }/custom/kcal">Kcal Custom</a>
<!-- 				<div class="dropdown"> -->
<!-- 					<a href="#">Order Submenu 1</a> <a href="#">Order Submenu 2</a> -->
<!-- 				</div> -->
			</div>
			<div class="menu-item">
				<c:if test="${ user_role == 'admin' }">	
				<a href="#">관리자메뉴</a>
				<div class="dropdown">
					<a href="http://localhost:8080/240930subKingProject/static/html/ingredient_list.html">재료 리스트</a> 
					<a href="http://localhost:8080/240930subKingProject/static/html/ingredient_add.html">재료 추가</a>
					<a href="http://localhost:8080/240930subKingProject/static/html/sales.html">판매 실적</a>
				</div>
				</c:if>
				<c:if test="${ user_role != 'admin' }">
					<div>
						<a href="#">Contact</a>
					</div>
				</c:if>	
			</div>
		</nav>
		<div class="auth-links">
			<c:if test="${ empty user_id }">
				<a href="http://localhost:8080/240930subKingProject/api/v1/tempLogIn">로그인</a>
				<a href="http://localhost:8080/240930subKingProject/static/html/agreement.html">회원가입</a>
			</c:if>
			<c:if test="${ not empty user_id }">
				<a href="http://localhost:8080/240930subKingProject/api/v1/tempLogOut">로그아웃</a>
				<a href="${ pageContext.request.contextPath }/custom/orders">주문내역</a>
			</c:if>
		</div>
		<div class="auth-links">
			<c:if test="${ not empty user_id }">
				<a href="http://localhost:8080/240930subKingProject/api/v1/tempExit" id="userDelete">회원탈퇴</a>
			</c:if>
		</div>
	</header>
</body>
</html>
