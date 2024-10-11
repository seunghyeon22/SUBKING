document.getElementById("login").onclick = function(event) {
	event.preventDefault();
	let pw1 = document.getElementById("pw1").value;
	let pw2 = document.getElementById("pw2").value;
	let userinfo = { "user_pw": pw1 };

	if (pw1 == pw2) {
		fetch("http://localhost:8080/240930subKingProject/api/v1/tempResetPw", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(userinfo)
		}).then(function() {
			alert("비밀번호 변경에 성공하셨습니다.");
			window.location.href = "http://localhost:8080/240930subKingProject/api/v1/tempLogIn";
		})
	} else {
		alert("비밀번호를 확인해주세요")
	}


};

// 비밀번호 재확인과 입력값 유효 체크
window.onload = function() {
	const pw1 = document.getElementById("pw1");
	const pw2 = document.getElementById("pw2");
	let errorNextBoxes = document.querySelector('.error_next_box');

	// 유효성 검사 함수
	function isPasswordMatch() {
		if (pw1.value !== pw2.value) {
			errorNextBoxes.style.display = "block";
			errorNextBoxes.innerText = "비밀번호가 일치하지 않습니다.";
			errorNextBoxes.style.color = "red";
		} else {
			errorNextBoxes.style.display = "none";
		}
	}
	// 실시간 비밀번호 일치 검사
	pw2.addEventListener("keyup", isPasswordMatch);
}





