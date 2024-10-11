
const kcalInput = document.querySelector(".kcal_input");
const kcalBtn = document.querySelector(".kcal_button");
let orderBtn = document.querySelector(".order-button");
let cartBtn = document.querySelector(".cart-button");


kcalBtn.addEventListener("click", function() {
	let arr = [];
	let kcal = kcalInput.value;
	let url = "/240930subKingProject/api/v1/kcal";
	fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(kcal),
	})
		.then((resp) => resp.json())
		.then((dataArr) => {
			let list = document.querySelector(".list");

			for (let i = 0; i < dataArr.length; i++) {
				arr.push(dataArr[i]);
			}
			let menu = ``;
			for (let i = 0; i < arr.length; i++) {
				let igname = "";
				let price = 0;
				let cal = 0;;
				for (let j = 0; j < arr[i].length; j++) {
					igname += arr[i][j].ig_name + "  ";
					price += arr[i][j].ig_price;
					cal += arr[i][j].ig_kcal;
				}

				menu += `
					<li>
						<div class="img">
							<img alt="상품이름" src="">
						</div> 
						<strong class="tit">${kcal}칼로리 버거</strong> 
						<span class="text">${igname}</span>
						<span class="eng">${price} 원</span> 
						<span class="cal">${cal}kcal</span> <input
						type="button" class="plus" value="추가"> 
						<label class="counts">0</label> 
						<input type="button" class="minus" value="감소">
					</li>						
					`;
			}
			list.innerHTML = menu;
			plused();
			minused();
			cartBtn.addEventListener("click", function() {
				console.log("시바ㅓㄹ");
				let counts = document.querySelectorAll('.counts');
				for (let i = 0; i < counts.length; i++) {
					console.log(counts[i].innerText);
					let count=0;
					if (counts[i].innerText > 0) {
						let count = counts[i].innerText;
						let ig_nos = [];
						for (let j = 0; j < arr[i].length; j++) {
							ig_nos.push(arr[i][j].ig_no);
						}
						let url = "/240930subKingProject/api/v1/cart";
						for (let k = 0; k < count; k++) {
							fetch(url, {
								method: "POST",
								headers: {
									"Content-Type": "application/json",
								},
								body: JSON.stringify(ig_nos)
							}).then((resp) => window.location.href = "http://localhost:8080/240930subKingProject/custom/kcal");
						}

					}

				}

			})				
		});
})
function plused() {

	let counts = document.querySelectorAll('.counts');
	let plus = document.querySelectorAll('.plus');

	for (let i = 0; i < counts.length; i++) {
		plus[i].addEventListener("click", function() {
			let number = counts[i].innerText;
			number = parseInt(number) + 1;
			counts[i].innerText = number;
		})
	}
}
function minused() {
	let counts = document.querySelectorAll('.counts');
	let minus = document.querySelectorAll('.minus');

	for (let i = 0; i < counts.length; i++) {
		minus[i].addEventListener("click", function() {
			let number = counts[i].innerText;
			if (number > 0) {
				number = parseInt(number) - 1;
				counts[i].innerText = number;
			} else if (number <= 0) {
				alert("감소할 수 없습니다.")
			}
		})
	}
}
