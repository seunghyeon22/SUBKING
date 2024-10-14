<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>칼로리 주문</title>
<link rel="stylesheet" type="text/css"
	href="../static/css/kcalCustom.css">
<link rel="stylesheet" type="text/css" href="../static/css/footer.css">
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>
	<div class="container">
		<div class="content1">

			<strong class="addr">원하시는 칼로리를 입력해주세요.</strong> <input type="search"
				class="kcal_input" placeholder="700kcal~1500kcal">
			<button type="button" class="kcal_button" >검색</button>

		</div>
		<div class="content">
			<div class="menu_list_wrapper">
				<div class="pd_tab">
					<span>주문하기</span>
				</div>
				<div class="pd_list_wrapper">
					<ul class="list">

					</ul>

					<div class="buttons">
						<input type="button" value="주문하기" class="order-button"> <input
							type="button" value="장바구니 담기" class="cart-button">
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>
<script type="text/javascript" src="../static/js/kcalcustom.js"></script>

</html>