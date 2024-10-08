const igName = document.querySelector(".ig_name");
const igPrice = document.querySelector(".ig_price");
const igKcal = document.querySelector(".ig_kcal");
const buttons = document.querySelector(".buttons")


let breads = [];
let vegetables = [];
let pattys = [];
let sauces = [];
let cheezes = [];
let sides = [];
let drinks = [];
let count = 0;

let item = [];
let item1 = [];
let item2 = [];
let item3 = [];
let item4 = [];
let item5 = [];
let item6 = [];

loadData();

function loadCustomData() {
	let url = "/240930subKingProject/api/v1/custom";
	return fetch(url)
		.then((resp) => resp.json());

}


function loadData() {
	loadCustomData().then((igArr) => {
		for (let i = 0; i < igArr.length; i++) {
			if (igArr[i].ig_category == "빵") {
				breads.push(igArr[i]);

			}
			if (igArr[i].ig_category == "야채") {
				vegetables.push(igArr[i]);
			}
			if (igArr[i].ig_category == "패티") {
				pattys.push(igArr[i]);
			}
			if (igArr[i].ig_category == "소스") {
				sauces.push(igArr[i]);
			}
			if (igArr[i].ig_category == "치즈") {
				cheezes.push(igArr[i]);
			}
		}
		console.log(breads);
		console.log(vegetables);
		console.log(pattys);
		console.log(sauces);
		console.log(cheezes);

		selectBread();
	})
}

function selectBread() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < breads.length; i++) {
		tableStr += `	
		<li class="bread">
			<div class="img">
				<img alt="재료이름" src="">
			</div>
			<input type="hidden" class="ig_id" value = "${breads[i].ig_no}">
			<strong class="tit">${breads[i].ig_name}</strong> 
			<span class="eng">${breads[i].ig_price}원</span>
			<span class="cal">${breads[i].ig_kcal}kcal</span>
			<input type="checkbox" class="check">
		</li>
		`;
	}
	let btnplus = `	
		<input type="button" value="다음으로" class="next-button">
					`;
	list.innerHTML = tableStr;
	buttons.innerHTML = btnplus;
	nextButton();
}

function selectVegetables() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < vegetables.length; i++) {
		tableStr += `	
		<li class="vegetable">
			<div class="img">
				<img alt="재료이름" src="">
			</div>
			<input type="hidden" class="ig_id" value = "${vegetables[i].ig_no}">
			<strong class="tit">${vegetables[i].ig_name}</strong> 
			<span class="eng">${vegetables[i].ig_price}원</span>
			<span class="cal">${vegetables[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="추가" >
			<label class="counts">0</label>
			<input type = "button" class="minus" value ="감소">
		</li>
		`;
	}
	let btnplus = `	
	<input type="button" value="이전으로" class="before1-button">
	<input type="button" value="다음으로" class="next1-button">
				`;
	list.innerHTML = tableStr;
	buttons.innerHTML = btnplus;
	plused();
	minused();
	next1();
	before1();
}


function selectPattys() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < pattys.length; i++) {
		tableStr += `	
		<li class="patty">
			<div class="img">
				<img alt="재료이름" src="">
			</div>
			<input type="hidden" class="ig_id" value = "${pattys[i].ig_no}">
			<strong class="tit">${pattys[i].ig_name}</strong> 
			<span class="eng">${pattys[i].ig_price}원</span>
			<span class="cal">${pattys[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="추가" >
			<label class="counts">0</label>
			<input type = "button" class="minus" value ="감소">
		</li>
		`;
	}
	let btnplus = `	
		<input type="button" value="이전으로" class="before2-button">
		<input type="button" value="다음으로" class="next2-button">
					`;
	list.innerHTML = tableStr;
	buttons.innerHTML = btnplus;
	plused();
	minused();
	next2();
}

function selectSauces() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < sauces.length; i++) {
		tableStr += `	
		<li class="sauce">
			<div class="img">
				<img alt="재료이름" src="">
			</div>
			<input type="hidden" class="ig_id" value = "${sauces[i].ig_no}">
			<strong class="tit">${sauces[i].ig_name}</strong> 
			<span class="eng">${sauces[i].ig_price}원</span>
			<span class="cal">${sauces[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="추가" >
			<label class="counts">0</label>
			<input type = "button" class="minus" value ="감소">
		</li>
		`;
	}
	let btnplus = `	
		<input type="button" value="이전으로" class="before3-button">
		<input type="button" value="다음으로" class="next3-button">
					`;
	list.innerHTML = tableStr;
	buttons.innerHTML = btnplus;
	plused();
	minused();
	next3();
}

function selectCheeses() {
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < cheezes.length; i++) {
		tableStr += `	
		<li class="cheese">
			<div class="img">
				<img alt="재료이름" src="">
			</div>
			<input type="hidden" class="ig_id" value = "${cheezes[i].ig_no}">
			<strong class="tit">${cheezes[i].ig_name}</strong> 
			<span class="eng">${cheezes[i].ig_price}원</span>
			<span class="cal">${cheezes[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="추가" >
			<label class="counts">0</label>
			<input type = "button" class="minus" value ="감소">
			
		</li>
		`;
	}
	let btnplus = `	
		<input type="button" value="이전으로" class="before4-button">
		<input type="button" value="주문하기" class="order-button">
		<input type="button" value="장바구니 담기" class="cart-button">
					`;
	list.innerHTML = tableStr;
	buttons.innerHTML = btnplus;
	plused();
	minused();
	cart();
	payment();
}
function nextButton() {
	const nextButton = document.querySelector(".next-button");
	nextButton.addEventListener("click", function() {
		let count = 0;
		let ss;
		let aa;
		const check = document.querySelectorAll(".check");
		for (let i = 0; i < check.length; i++) {
			if (check[i].checked) {
				count++;
				ss = i;
			}

		}
		if (count < 2 && count > 0) {
			item.push(breads[ss].ig_no);
			selectVegetables();
		} else {
			alert("빵은 1개만 선택가능합니다.");
		}
	});
}


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

function next1() {
	const next1Button = document.querySelector(".next1-button");
	next1Button.addEventListener("click", function() {
		item1.splice(0);
		let counts = document.querySelectorAll('.counts');
		let igno = document.querySelectorAll(".ig_id");
		for (let i = 0; i < counts.length; i++) {
			let number = counts[i].innerText;
			if (number > 0) {
				for (let j = 0; j < number; j++) {
					item1.push(parseInt(igno[i].value));
				}
			}

		}
		console.log(item);
		console.log(item1);
		selectPattys();
	})
}
function before1() {
	const before1Button = document.querySelector(".before1-button");
	before1Button.addEventListener("click", function() {
		selectBread();
	})
}

function next2() {
	let next2Button = document.querySelector(".next2-button");
	next2Button.addEventListener("click", function() {
		item2.splice(0);
		let counts = document.querySelectorAll('.counts');
		let igno = document.querySelectorAll(".ig_id");
		for (let i = 0; i < counts.length; i++) {
			let number = counts[i].innerText;
			if (number > 0) {
				for (let j = 0; j < number; j++) {
					item2.push(parseInt(igno[i].value));
				}
			}

		}
		console.log(item);
		console.log(item1);
		selectSauces();
	})
}
function next3() {
	let next3Button = document.querySelector(".next3-button");
	next3Button.addEventListener("click", function() {
		item3.splice(0);
		let counts = document.querySelectorAll('.counts');
		let igno = document.querySelectorAll(".ig_id");
		for (let i = 0; i < counts.length; i++) {
			let number = counts[i].innerText;
			if (number > 0) {
				for (let j = 0; j < number; j++) {
					item3.push(parseInt(igno[i].value));
				}
			}

		}
		console.log(item);
		console.log(item3);
		selectCheeses();
	})
}

function cart() {
	const cartButton = document.querySelector(".cart-button");
	cartButton.addEventListener("click", function() {

		item4.splice(0);
		let counts = document.querySelectorAll('.counts');
		let igno = document.querySelectorAll(".ig_id");
		for (let i = 0; i < counts.length; i++) {
			let number = counts[i].innerText;
			if (number > 0) {
				for (let j = 0; j < number; j++) {
					item4.push(parseInt(igno[i].value));
				}
			}

		}
		const reqData = [...item, ...item1, ...item2, ...item3, ...item4, ...item5, ...item6];
		let jsonReq = JSON.stringify(reqData);
		console.log(reqData);
		console.log(jsonReq);
		let url = "/240930subKingProject/api/v1/cart";
		fetch(url, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(reqData),
		}).then((resp) => resp.json())
			.then((data) => console.log(data))

		selectBread();
	})
}

function payment(){
	const orderButton = document.querySelector(".order-button");
	orderButton.addEventListener("click", function(){
		window.location.href = "http://localhost:8080/240930subKingProject/custom/payment";
		
	})
}

