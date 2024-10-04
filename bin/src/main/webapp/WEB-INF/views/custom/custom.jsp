<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/static/css/default.css">
<link rel="stylesheet" type="text/css" href="./static/css/custom.css">
</head>
<body>
	<main class="container">
		<div class="content">
			<div class="menu_list_wrapper">
				<div class="pd_tab">
					<span>주문하기</span>
					<ul>
						<li class="active">빵</li>
						<li>야채</li>
						<li>패티</li>
						<li>치즈</li>
						<li>소스</li>
						<li>사이드</li>
						<li>음료</li>
					</ul>
				</div>
				<div class="pd_list_wrapper">
					<ul class="list">
						<li class="bread">
							<div class="img">
								<img alt="재료이름" src="">
							</div> <strong class="tit">재료이름</strong> <span class="eng">가격</span> <span
							class="cal">칼로리</span>

						</li>
					</ul>
				</div>
			</div>
		</div>
	</main>
</body>
<script type="text/javascript" src="./static/js/custom.js"></script>
</html>