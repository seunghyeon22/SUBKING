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
			<div class="cart_list_wrapper">
				<div class="pd_tab">
					<span>장바구니 목록</span>
				</div>
				<div class="pd_list_wrapper">
					<ul class="list">
						<li>
							<div class="img">
								<img alt="상품이미지" src="">
							</div>
							<div class="texts">
								<dl>
								<dd><strong class="tit">상품이름</strong></dd>
								<dd><span>상품정보</span></dd>
								<dd><span>칼로리 : 500kcal</span></dd>
								<dd><span>가격 : 11111</span></dd>
								</dl>
								<input type="hidden" value="">
								<input type="checkbox" class="check">
							</div>
						</li>
					</ul>

					<div class="payment">
						<button type="button" class="before">주문하기로 돌아가기</button>
						<button type="button" class="payments" onclick="requestPay()">결제하기</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>

</html>