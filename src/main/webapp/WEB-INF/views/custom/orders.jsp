<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<link rel="stylesheet" type="text/css" href="../static/css/orders.css">

<title>서브킹 주문 내역</title>
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>
	
	<section class="section-container"></section>

	<template id="order-template">
		<div class="container">
			<div class="image-container">
				<img
					src="../static/image/logo.jpg"
					alt="햄버거 이미지">
			</div>
			<div class="content">
				<h2>서브킹</h2>
				<p>주문날짜: 10월 2일</p>
				<p>주문 상태: 배달완료</p>
				<p>햄버거 1개 외 총 금액: 10000원</p>
				<div class="button-container">
<!-- 				<button class="same-menu">같은 메뉴 담기</button> -->
					<button class="order-details">주문 상세</button>
				</div>
			</div>
			<dialog class="order-dialog">
				<template id="menu-template">
				 <div class="menu-border">
					<div class="label-conatiner">
						<label class="menu-name">커스텀버거</label>
						<label class="menu-kcal">2000kcal</label>
						<label class="menu-price">15300원</label>
					</div>
					<ul class="ingredients-list">
						<li class="ig-li">빵</li>
							<ul class="ingredients-bread">
							</ul>
						<li class="ig-li">야채</li>
							<ul class="ingredients-vegetable">
							</ul>
						<li class="ig-li">패티</li>
							<ul class="ingredients-patty">
							</ul>
						<li class="ig-li">치즈</li>
							<ul class="ingredients-cheeze">
							</ul>
						<li class="ig-li">소스</li>
							<ul class="ingredients-sauce">
							</ul>
					</ul>
					<button class="btn-copy">장바구니에 담기</button>
				 </div>
				</template>
					<button class="btn-tocart">장바구니로 이동</button>
					<button class="btn-close">닫기</button>
<!-- 				<a href="http://localhost:8080/240930subKingProject/custom/payment">장바구니로 이동</a> -->
			</dialog>
		</div>
	</template>

	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>
	<script	src="../static/js/orders.js"></script>
</html>