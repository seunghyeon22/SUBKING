window.onload = function() {
    const url = "http://localhost:8080/240930subKingProject/api/v1/ingredients"; // 절대 경로로 수정
    fetch(url)
        .then(response => {
            console.log(response); // 응답 객체 로그로 확인
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);  // API 응답 데이터 확인
            const container = document.getElementById('ingredients-container');
            const categories = {};

            // 카테고리별로 재료를 그룹화
            data.forEach(ingredient => {
                if (!categories[ingredient.ig_category]) {
                    categories[ingredient.ig_category] = [];
                }
                categories[ingredient.ig_category].push(ingredient);
            });

            // 카테고리별로 재료 출력
            for (const category in categories) {
                const categoryDiv = document.createElement('div');
                categoryDiv.className = 'category';

                const categoryTitle = document.createElement('h2');
                categoryTitle.innerText = category;
                categoryDiv.appendChild(categoryTitle);

                const ingredientsRow = document.createElement('div');
                ingredientsRow.className = 'ingredients-row';

                categories[category].forEach(ingredient => {
                    console.log(ingredient.ig_image);  // base64 이미지 데이터 확인
                    const ingredientCard = document.createElement('div');
                    ingredientCard.className = 'ingredient';
                    ingredientCard.innerHTML = `
                        <img src="data:image/jpeg;base64,${ingredient.ig_image}" alt="${ingredient.ig_name}" class="ingredient-image">
                        <h3>${ingredient.ig_name}</h3>
                        <p>칼로리: ${ingredient.ig_kcal} kcal</p>
                    `;
                    ingredientsRow.appendChild(ingredientCard);
                });

                categoryDiv.appendChild(ingredientsRow);
                container.appendChild(categoryDiv);
            }
        })
        .catch(error => console.error('Error loading ingredients:', error));
}