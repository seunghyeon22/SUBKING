
// 주소 팝업창 열기
function goPopup(){
    var pop = window.open("http://localhost:8080/240930subKingProject/static/jsp/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}

// 팝업에서 반환된 데이터 처리
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,zipNo){
    document.getElementById("roadAddrPart1").value = roadAddrPart1;
    document.getElementById("roadAddrPart2").value = roadAddrPart2;
    document.getElementById("addrDetail").value = addrDetail;
    document.getElementById("zipNo").value = zipNo;
}

function submitForm() {
    // 입력된 값 가져오기
    const id = document.getElementById('id').value;
    const password1 = document.getElementById('pswd1').value;
    const password2 = document.getElementById('pswd2').value;
    const name = document.getElementById('name').value;
    const yy = document.getElementById('yy').value;
    const mm = document.getElementById('mm').value;
    const dd = document.getElementById('dd').value;
    let gender = document.getElementById('gender').value;
    const email = document.getElementById('email').value;
    const mobile = document.getElementById('mobile').value;
	
	let roadAddrPart1 = document.getElementById("roadAddrPart1").value;
	let roadAddrPart2 = document.getElementById("roadAddrPart2").value;
	let addrDetail = document.getElementById("addrDetail").value;
	
	const address = roadAddrPart1 + roadAddrPart2 + " 상세주소: " + addrDetail;

    // 입력된 비밀번호 확인 (예시)
    if (password1 !== password2) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
	
	if (gender == "M") {
		gender = 1;
	} else {
		gender = 0;
	}
	
	const birth = yy + "-" + mm + "-" + dd;
    // 회원가입 데이터를 객체로 구성
    const formData = {
        user_id: id,
        user_pw: password1,
        user_name: name,
		user_birth: birth,
//        birthYear: yy,
//        birthMonth: mm,
//        birthDay: dd,
        user_gender: gender,
        user_email: email,
        user_phone: mobile,
		user_address: address
    };

    // 서버로 데이터 전송 (fetch API)
    fetch('http://localhost:8080/240930subKingProject/api/v1/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('회원가입이 성공적으로 완료되었습니다!');
			// 회원가입 성공 후 로그인 페이지로 이동
            window.location.href = 'http://localhost:8080/240930subKingProject/api/v1/tempLogIn';
        } else {
            alert('회원가입에 실패했습니다: ' + data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('서버 오류가 발생했습니다.');
    });
}
// 사용자 삭제 함수
fetch('http://localhost:8080/240930subKingProject/api/v1/tempExit', {
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
    }
})
.then(response => response.json())
.then(data => {
    if (data.success) {
        alert('사용자가 성공적으로 삭제되었습니다!');
        // 필요한 경우, 사용자 목록 페이지로 이동 등 추가 작업
    } else {
        alert('사용자 삭제에 실패했습니다: ' + data.message);
    }
})
.catch(error => {
    console.error('Error:', error);
    alert('서버 오류가 발생했습니다.');
});
