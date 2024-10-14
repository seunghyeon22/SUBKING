<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SubKing Home</title>
<link rel="stylesheet" type="text/css" href="../static/css/home.css">
</head>
<body>
	<c:import url="subking.jsp" charEncoding="utf-8"></c:import>

	<div class="container">
		<div class="slider">
			<div class="slides">
				<div class="slide">
					<img src="../static/image/homeimage1.jpg" alt="슬라이드 1">
				</div>
				<div class="slide">
					<img src="../static/image/homeimage2.jpg" alt="슬라이드 2">
				</div>
				<!--             <div class="slide"><img src="../static/image/homeimage4.jpg" alt="슬라이드 3"></div> -->
			</div>
		</div>
		<button class="prev" onclick="moveSlide(-1)">&#10094;</button>
		<button class="next" onclick="moveSlide(1)">&#10095;</button>
	</div>
	<c:import url="footer.jsp" charEncoding="utf-8"></c:import>
</body>
<script src="../static/js/home.js"></script>
</html>