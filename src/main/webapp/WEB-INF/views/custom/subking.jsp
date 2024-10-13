<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="User_header">
			<ul class="User_header_List">
				<c:if test="${ empty user_id }">
					<li><a
						href="http://localhost:8080/240930subKingProject/api/v1/tempLogIn">로그인</a></li>
					<span>&#124;</span>
					<li><a
						href="http://localhost:8080/240930subKingProject/static/html/agreement.html">회원가입</a></li>
					<span>&#124;</span>
				</c:if>
				<c:if test="${ not empty user_id }">
					<li>${ user_id }님 안녕하세요.</li>
					<li><a
						href="http://localhost:8080/240930subKingProject/api/v1/tempLogOut">로그아웃</a></li>
				</c:if>
			</ul>

		</div>
		<div class="mainMenu_List">
			<div class="logo">
				<img src="../static/image/logo.jpg" alt="햄버거 이미지">
			</div>
			<nav>
				<ul class="mainMenu">
					<li><a href=""><span>새소식</span></a>
						<ul class="subMenu">
							<li><a href="">프로모션</a></li>
							<li><a href="">광고영상</a></li>
							<li><a href="">공지사항</a></li>
						</ul></li>
					<li><a href=""><span>재료 소개</span></a>
						<ul class="subMenu">
							<li><a href="http://localhost:8080/240930subKingProject/custom/introduction">재료 소개</a></li>
							<!-- 							<li><a href=""></a></li> -->
						</ul></li>
					<li><a href=""><span>주문하기</span></a>
						<ul class="subMenu">
							<li><a
								href="http://localhost:8080/240930subKingProject/custom/custom">커스텀
									주문</a></li>
							<li><a
								href="http://localhost:8080/240930subKingProject/custom/kcal">칼로리
									주문</a></li>
						</ul></li>
					<li class=mypage-Menue><a href=""><span>내 목록</span></a>
						<ul class="subMenu">
							<li><a href="http://localhost:8080/240930subKingProject/custom/payment">장바구니</a></li>
							<li><a href="http://localhost:8080/240930subKingProject/custom/orders">주문목록</a></li>
						</ul></li>
					<c:if test="${ user_role eq 'user_admin'}">
						<!-- 여긴 admin -->
						<li class=mypage-Menue><a href=""><span>관리자</span></a>
							<ul class="subMenu">
								<li><a href="">재료추가</a></li>
								<li><a href="">매출확인</a></li>
							</ul></li>
					</c:if>
				</ul>


				<!-- 			<div class="menu-item"> -->
				<%-- 				<a href="${ pageContext.request.contextPath }/custom/home">Home</a> --%>
				<!-- <!-- 				<div class="dropdown"> -->
				<!-- <!-- 					<a href="#">Home Submenu 1</a> <a href="#">Home Submenu 2</a> -->
				<!-- <!-- 				</div> -->
				<!-- 			</div> -->
				<!-- 			<div class="menu-item"> -->
				<%-- 				<a href="${ pageContext.request.contextPath }/custom/custom">Custom</a> --%>
				<!-- <!-- 				<div class="dropdown"> -->
				<!-- <!-- 					<a href="#">Submenu 1</a> <a href="#">Submenu 2</a> <a href="#">Submenu	3</a> -->
				<!-- <!-- 				</div> -->
				<!-- 			</div> -->
				<!-- 			<div class="menu-item"> -->
				<!-- 				<a href="#">Kcal Custom</a> -->
				<!-- <!-- 				<div class="dropdown"> -->
				<!-- <!-- 					<a href="#">Order Submenu 1</a> <a href="#">Order Submenu 2</a> -->
				<!-- <!-- 				</div> -->
				<!-- 			</div> -->
				<!-- 			<div class="menu-item"> -->
				<!-- 				<a href="#">Contact</a> -->
				<!-- <!-- 				<div class="dropdown"> -->
				<!-- <!-- 					<a href="#">Contact Submenu 1</a> <a href="#">Contact Submenu 2</a> -->
				<!-- <!-- 				</div> -->
				<!-- 			</div> -->
			</nav>

		</div>
	</header>
</body>
</html>
