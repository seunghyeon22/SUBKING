
// 주소 팝업창 열기
function goPopup() {
	var pop = window.open("http://localhost:8080/240930subKingProject/static/jsp/jusoPopup.jsp", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

// 팝업에서 반환된 데이터 처리
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2, zipNo) {
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

window.onload = function() {
    const id = document.getElementById("id");
    const pswd1 = document.getElementById("pswd1");
    const pswd2 = document.getElementById("pswd2");
    const name = document.getElementById("name");
    const yy = document.getElementById("yy");
    const mm = document.getElementById("mm");
    const dd = document.getElementById("dd");
    const gender = document.getElementById("gender");
    const mobile = document.getElementById("mobile");
    const email = document.getElementById("email");
    const errorNextBoxes = document.querySelectorAll('.error_next_box');

    // ID 중복 확인
    id.addEventListener("input", function() {
        checkDuplicateId();
    });

    function checkDuplicateId() {
		if (id === "") {
				alert("아이디를 입력해주세요.");
				return;
			}
			// 서버로 아이디 중복 확인 요청
			fetch('http://localhost:8080/240930subKingProject/api/v1/register', {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ user_id: id })
			})
				.then(response => response.json())
				.then(data => {
					if (data.isDuplicate) {
						alert('이미 사용 중인 아이디입니다.');
					} else {
						alert('사용 가능한 아이디입니다.');
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert('서버 오류가 발생했습니다.');
				});
    }

    // 모든 필드가 입력되었는지 확인
    function validateAllFields() {
        let isValid = true;

        // 각 필드별로 입력값 확인
        if (id.value.length < 1 || id.value.length > 20) {
            errorNextBoxes[0].style.display = "block";
            errorNextBoxes[0].innerText = "아이디는 1자 이상 20자 이하로 입력해주세요.";
            isValid = false;
        } else {
            errorNextBoxes[0].style.display = "none";
        }

        if (pswd1.value.length < 1 || pswd1.value.length > 20) {
            errorNextBoxes[1].style.display = "block";
            errorNextBoxes[1].innerText = "비밀번호는 1자 이상 20자 이하로 입력해주세요.";
            isValid = false;
        } else {
            errorNextBoxes[1].style.display = "none";
        }

        if (pswd1.value !== pswd2.value) {
            errorNextBoxes[2].style.display = "block";
            errorNextBoxes[2].innerText = "비밀번호가 일치하지 않습니다.";
            isValid = false;
        } else {
            errorNextBoxes[2].style.display = "none";
        }

        const nameRegExp = /^[가-힣a-zA-Z]+$/;
        if (!nameRegExp.test(name.value)) {
            errorNextBoxes[3].style.display = "block";
            errorNextBoxes[3].innerText = "이름은 한글 또는 영문만 입력 가능합니다.";
            isValid = false;
        } else {
            errorNextBoxes[3].style.display = "none";
        }

        if (yy.value.length !== 4 || isNaN(yy.value)) {
            errorNextBoxes[4].style.display = "block";
            errorNextBoxes[4].innerText = "태어난 년도는 4자리 숫자로 입력해주세요.";
            isValid = false;
        } else {
            errorNextBoxes[4].style.display = "none";
        }

        const selectedMonth = parseInt(mm.value);
        let maxDays;
        if (selectedMonth === 2) {
            maxDays = 27;
        } else if ([4, 6, 9, 11].includes(selectedMonth)) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }

        const day = parseInt(dd.value);
        if (day < 1 || day > maxDays || isNaN(day)) {
            errorNextBoxes[4].style.display = "block";
            errorNextBoxes[4].innerText = `${maxDays}일 이내로 입력해주세요.`;
            isValid = false;
        } else {
            errorNextBoxes[4].style.display = "none";
        }

        if (gender.value === "성별") {
            errorNextBoxes[5].style.display = "block";
            errorNextBoxes[5].innerText = "성별을 선택해주세요.";
            isValid = false;
        } else {
            errorNextBoxes[5].style.display = "none";
        }

        if (mobile.value.length === 0 || !/^01[016789]-\d{3,4}-\d{4}$/.test(mobile.value)) {
            errorNextBoxes[8].style.display = "block";
            errorNextBoxes[8].innerText = "휴대폰 번호를 다시 확인해주세요.";
            isValid = false;
        } else {
            errorNextBoxes[8].style.display = "none";
        }

        if (email.value.length > 0) {
            const emailRegExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
            if (!emailRegExp.test(email.value)) {
                errorNextBoxes[7].style.display = "block";
                errorNextBoxes[7].innerText = "이메일 주소를 다시 확인해주세요.";
                isValid = false;
            } else {
                errorNextBoxes[7].style.display = "none";
            }
        }

        return isValid;
    }

    // 폼 제출 시 검증
    document.getElementById("btnJoin").addEventListener("click", function() {
        if (!validateAllFields()) {
            alert("모든 필드를 올바르게 입력해주세요.");
        } else {
            submitForm();
        }
    });
};



