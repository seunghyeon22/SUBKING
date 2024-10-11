<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../static/css/kcalCustom.css">
<link rel="stylesheet" type="text/css" href="../static/css/footer.css">
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>
	<div class="container">
		<div class="content-1">
			<div>
				<span class="addr">원하시는 칼로리를 입력해주세요.</span> <input type="number"
					class="kcal_input">
				<button type="button" class="kcal_button" >검색</button>
			</div>
		</div>
		<div class="content">
			<div class="menu_list_wrapper">
				<div class="pd_tab">
					<span>주문하기</span>
				</div>
				<div class="pd_list_wrapper">
					<ul class="list">
						<li>
							<div class="img">
								<img alt="재료이름" src="">
							</div> <strong class="tit">재료이름</strong> <span class="eng">가격</span> <span
							class="cal">칼로리</span> <input type="checkbox" class="check">
						</li>
					</ul>
					<div class="buttons"></div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>
</html>