const igName = document.querySelector(".ig_name");
const igPrice = document.querySelector(".ig_price");
const igKcal = document.querySelector(".ig_kcal");
const buttons = document.querySelector(".buttons");

let bread = document.querySelector(".bread");
let vegetable = document.querySelector(".vegetable");
let patty = document.querySelector(".patty");
let sauce = document.querySelector(".sauce");
let cheese = document.querySelector(".cheese");


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
	bread.classList.toggle("ative");
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < breads.length; i++) {
		tableStr += `	
		<li class="breads">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${breads[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${breads[i].ig_no}">
			<strong class="tit">${breads[i].ig_name}</strong> 
			<span class="eng">${breads[i].ig_price}원</span>
			<span class="cal">${breads[i].ig_kcal}kcal</span>
			<label class="checkbox-container">
			        <input type="checkbox" class ="check">
			        <span class="checkmark"></span>
			</label>	
			    
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
	vegetable.classList.toggle("ative");
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < vegetables.length; i++) {
		tableStr += `	
		<li class="vegetables">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${vegetables[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${vegetables[i].ig_no}">
			<strong class="tit">${vegetables[i].ig_name}</strong> 
			<span class="eng">${vegetables[i].ig_price}원</span>
			<span class="cal">${vegetables[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="+" >
						<label class="counts">0</label>
						<input type = "button" class="minus" value ="-">
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
	patty.classList.toggle("ative");
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < pattys.length; i++) {
		tableStr += `	
		<li class="pattys">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${pattys[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${pattys[i].ig_no}">
			<strong class="tit">${pattys[i].ig_name}</strong> 
			<span class="eng">${pattys[i].ig_price}원</span>
			<span class="cal">${pattys[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="+" >
						<label class="counts">0</label>
						<input type = "button" class="minus" value ="-">
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
	before2();
}

function selectSauces() {
	sauce.classList.toggle("ative");
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < sauces.length; i++) {
		tableStr += `	
		<li class="sauces">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${sauces[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${sauces[i].ig_no}">
			<strong class="tit">${sauces[i].ig_name}</strong> 
			<span class="eng">${sauces[i].ig_price}원</span>
			<span class="cal">${sauces[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="+" >
			<label class="counts">0</label>
			<input type = "button" class="minus" value ="-">
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
	before3();
}

function selectCheeses() {
	cheese.classList.toggle("ative");
	const list = document.querySelector(".list");
	let tableStr = ``;
	for (let i = 0; i < cheezes.length; i++) {
		tableStr += `	
		<li class="cheeses">
			<div class="img">
				<img alt="재료이름" src="data:image/jpeg;base64,${cheezes[i].ig_image}">
			</div>
			<input type="hidden" class="ig_id" value = "${cheezes[i].ig_no}">
			<strong class="tit">${cheezes[i].ig_name}</strong> 
			<span class="eng">${cheezes[i].ig_price}원</span>
			<span class="cal">${cheezes[i].ig_kcal}kcal</span>
			<input type = "button" class="plus"  value ="+" >
						<label class="counts">0</label>
						<input type = "button" class="minus" value ="-">
			
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
	before4();
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
			bread.classList.toggle("ative");
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
		vegetable.classList.toggle("ative");
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
		vegetable.classList.toggle("ative");
	})
}
function before2() {
	const before1Button = document.querySelector(".before2-button");
	before1Button.addEventListener("click", function() {
		selectVegetables();
		patty.classList.toggle("ative");
	})
}
function before3() {
	const before1Button = document.querySelector(".before3-button");
	before1Button.addEventListener("click", function() {
		selectPattys();
		sauce.classList.toggle("ative");
	})
}

function before4() {
	const before1Button = document.querySelector(".before4-button");
	before1Button.addEventListener("click", function() {
		selectSauces();
		cheese.classList.toggle("ative");
	})
}


function next2() {
	let next2Button = document.querySelector(".next2-button");
	next2Button.addEventListener("click", function() {
		patty.classList.toggle("ative");
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
		sauce.classList.toggle("ative");
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
		console.log(reqData);
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

function payment() {
	const orderButton = document.querySelector(".order-button");
	orderButton.addEventListener("click", function() {
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
		let url = "/240930subKingProject/api/v1/cart";
		fetch(url, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(reqData),
		}).then((resp) => window.location.href = "http://localhost:8080/240930subKingProject/custom/cart"
		)
	})
}

//function Recheck() {
//	let igno = document.querySelectorAll(".ig_id");
//	let check = document.querySelectorAll(".check")
//	const reqData = [...item, ...item1, ...item2, ...item3, ...item4, ...item5, ...item6];
//
//	for (let i = 0; i < reqData.length; i++) {
//		for (let j = 0; j < igno.length; j++) {
//			if (reqData[i] = igno[j].value) {
//				console.log(reqData[i]);
//				console.log(igno[j].value);
//				check[j].prop("checked", true);
//			}
//		}
//	}
//}
