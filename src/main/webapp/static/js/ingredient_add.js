document.getElementById('add-ingredient-form').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const formData = new FormData(this);
    const file = formData.get('ig_image');

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function() {
        const base64Image = reader.result.split(',')[1]; // base64 문자열 추출

        formData.set('ig_image', base64Image); // FormData에 인코딩된 이미지 추가

        // 폼 데이터 확인
        console.log("FormData entries:");
        for (const [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }

        // URLSearchParams를 사용하여 데이터 변환
        const params = new URLSearchParams(formData);

        fetch('/240930subKingProject/api/v1/ingredients', { // 절대 경로로 수정
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: params.toString(),
        })
        .then(response => {
            if (response.ok) {
                alert('재료가 추가되었습니다!');
                document.getElementById('load-ingredients').click(); // 재료 목록 다시 로드
            } else {
                return response.text().then(text => {
                    console.error('Server error:', text);
                    alert('재료 추가 실패! ' + text);
                });
            }
        })
        .catch(error => console.error('Error adding ingredient:', error));
    };
    reader.onerror = function(error) {
        console.error('Error reading file:', error);
    };
});
