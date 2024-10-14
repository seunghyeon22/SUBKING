<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" type="text/css" href="../static/css/payment.css">
<link rel="stylesheet" type="text/css" href="../static/css/footer.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="../static/js/payment.js"></script>
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>
	<div class="container">
		<div class="content1">
			<strong class="addr" >배달지역 : ${ user_address }</strong>
			<button type="button" class="updateBtn" onclick="update()">변경</button>
			<span class="error_next_box">일단 확인</span>
		</div>
		<div class="content">
			<div class="cart_list_wrapper">
				<div class="pd_tab">
					<span>장바구니 목록</span>
				</div>
				<div class="pd_list_wrapper">
					<ul class="list">

					</ul>

				</div>
			</div>
		</div>
		<div class="content2">
			<div class="pd_list_wrappers">
				<div class="lists">
					<label class="CartallPrice">주문금액 : 0원 </label>
				</div>
				<div class="buttons">
					<button type="button" class="payments" onclick="requestPay()">결제하기</button>
					<button type="button" class="before">주문하기로 돌아가기</button>
				</div>
			</div>

		</div>
	</div>
	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>

</html>