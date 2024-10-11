<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 페이지</title>

<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/static/css/login.css">

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js" integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4" crossorigin="anonymous"></script>

</head>
<body>
	<div class="login-container">
		<h2>로그인</h2>
		<p class="login-error">${ result }</p>
		<form method="POST">
			<input type="text" name="username" placeholder="사용자 이름" required>
			<input type="password" name="password" placeholder="비밀번호" required>
			<button type="submit" id="login">로그인</button>
			<div class="button-group">
				<form></form>
				<button type="button" onclick="location.href='http://localhost:8080/240930subKingProject/static/jsp/findid.jsp'">아이디 찾기</button>
				<button type="button" onclick="location.href='http://localhost:8080/240930subKingProject/static/jsp/findpw.jsp'">비밀번호 찾기</button>
				<button type="button" onclick="location.href='http://localhost:8080/240930subKingProject/static/html/agreement.html'">회원가입</button>
			<button class="kakao-login" id="kakao-login-btn" onclick="loginWithKakao()">
            카카오로 로그인
        </button>
        <p id="token-result"></p>
			</div>
		</form>
	</div>
</body>
<script>
        Kakao.init('323f7176876dcf324ddc55bbb874736d');

        
        function loginWithKakao() {
            Kakao.Auth.authorize({
                redirectUri: 'http://localhost:8080/240930subKingProject/User',
            });
        }

        displayToken();
        function displayToken() {
            var token = getCookie('authorize-access-token');

            if (token) {
                Kakao.Auth.setAccessToken(token);
                Kakao.Auth.getStatusInfo()
                    .then(function(res) {
                        if (res.status === 'connected') {
                            document.getElementById('token-result').innerText = 'login success, token: ' + Kakao.Auth.getAccessToken();
                        }
                    })
                    .catch(function(err) {
                        Kakao.Auth.setAccessToken(null);
                    });
            }
        }

        function getCookie(name) {
            var parts = document.cookie.split(name + '=');
            if (parts.length === 2) { return parts[1].split(';')[0]; }
        }
</script>
</html>