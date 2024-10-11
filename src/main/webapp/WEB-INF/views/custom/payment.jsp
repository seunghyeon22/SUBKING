<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../static/css/payment.css">
<link rel="stylesheet" type="text/css" href="../static/css/footer.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="../static/js/payment.js"></script>
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>
	<div class="container">
		<div class="content">
			<span class="addr">배달 주소: 부산광역시 진구 어딘가 어딘가?</span>
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
		<div class="content">
			<div class="pd_list_wrapper">
				<div class="list">
					<label class="CartallPrice">주문금액 : 0원 </label>
				</div>
				<div class="payment">
					<button type="button" class="before">주문하기로 돌아가기</button>
					<button type="button" class="payments" onclick="requestPay()">결제하기</button>
				</div>
			</div>

		</div>
	</div>
	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>

</html>