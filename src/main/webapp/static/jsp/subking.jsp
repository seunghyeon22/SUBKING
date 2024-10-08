<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../css/tempSubking.css">
<title>Subway Header</title>
</head>
<body>
	<header class="header">
		<div class="logo">
			<img
					src="../image/logo.jpg"
					alt="햄버거 이미지">
		</div>
		<nav>
			<div class="menu-item">
				<a href="#">Home</a>
				<div class="dropdown">
					<a href="#">Home Submenu 1</a> <a href="#">Home Submenu 2</a>
				</div>
			</div>
			<div class="menu-item">
				<a href="#">Menu</a>
				<div class="dropdown">
					<a href="#">Submenu 1</a> <a href="#">Submenu 2</a> <a href="#">Submenu
						3</a>
				</div>
			</div>
			<div class="menu-item">
				<a href="#">Order</a>
				<div class="dropdown">
					<a href="#">Order Submenu 1</a> <a href="#">Order Submenu 2</a>
				</div>
			</div>
			<div class="menu-item">
				<a href="#">Contact</a>
				<div class="dropdown">
					<a href="#">Contact Submenu 1</a> <a href="#">Contact Submenu 2</a>
				</div>
			</div>
		</nav>
		<div class="auth-links">
			<c:if test="${ empty user_id }">
				<a href="http://localhost:8080/240930subKingProject/api/v1/tempLogIn">로그인</a>
				<a href="../html/agreement.html">회원가입</a>
			</c:if>
			<c:if test="${ not empty user_id }">
				<a href="http://localhost:8080/240930subKingProject/api/v1/tempLogOut">로그아웃</a>
				<a href="../html/orders.html">주문내역</a>
			</c:if>
		</div>
	</header>
</body>
</html>