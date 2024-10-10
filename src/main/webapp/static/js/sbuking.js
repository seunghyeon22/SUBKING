let currentIndex = 0;

let deleteLink = document.getElementById('userDelete');
deleteLink.addEventListener("click", function(e) {
	e.preventDefault(); // 기본 동작 방지
	
	if (confirm("정말 삭제하시겠습니까?")) {
		userDelete();
	} else {
		alert('삭제가 취소되었습니다.');
	}
});

// 사용자 삭제 함수
function userDelete() {
	fetch('http://localhost:8080/240930subKingProject/api/v1/user', {
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
}


