document.getElementById("login").onclick = function(event) {
	event.preventDefault();
	let pw1 = document.getElementById("pw1").value;
	let pw2 = document.getElementById("pw2").value;
	let userinfo = { "user_pw": pw1 };

    fetch("http://localhost:8080/240930subKingProject/api/v1/tempResetPw", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userinfo)
    })
    .then(response => {
        return response.json();
    })
    .then(data => {
        console.log(data); 
        if (data.success) {
            alert(data.message);
            window.location.href = "http://localhost:8080/240930subKingProject/static/jsp/resetpw.jsp";
        } else {
            alert(data.message);
        }
    })
};

// 비밀번호 재확인과 입력값 유효 체크
window.onload = function() {
	const pw1 = document.getElementById("pw1");
	const pw2 = document.getElementById("pw2");
}

// 비밀번호 유효성 검사
pw1.addEventListener("blur", function() {
	if (pw1.value.length < 1 || pw1.value.length > 20) {
		errorNextBoxes[1].style.display = "block";
		errorNextBoxes[1].innerText = "비밀번호는 1자 이상 20자 이하로 입력해주세요.";
	} else {
		errorNextBoxes[1].style.display = "none";
	}
});


// 유효성 검사 함수
function isPasswordMatch() {
	if (pw1.value !== pw2.value) {
		errorNextBoxes[2].style.display = "block";
		errorNextBoxes[2].innerText = "비밀번호가 일치하지 않습니다.";
	} else {
		errorNextBoxes[2].style.display = "none";
	}
}
// 실시간 비밀번호 일치 검사
	pw2.addEventListener("keyup", isPasswordMatch);
