document.getElementById("login").onclick = function(event) {
	let username = document.getElementById("user_name").value;
	let userphone = document.getElementById("user_phone").value; // 전화번호 입력 필드 필요
	let userinfo =  { "user_name" : username,  "user_phone" : userphone }

	fetch('http://localhost:8080/240930subKingProject/api/v1/tempFindId', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(userinfo)
	})
		.then(response => {
			if (response.success) {
				return response.text(); // 또는 JSON.parse(response.text())로 변환
			} else {
				throw new Error('Network response was not ok.');
			}
		})
		.then(data => {
			if(data.success){
			alert(data.message);
				        window.location.href = 'http://localhost:8080/240930subKingProject/static/jsp/tempFindId.jsp';
							// 성공적인 응답 처리
			}else{
				alert(data.message);
			}
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
			
			
		});
};