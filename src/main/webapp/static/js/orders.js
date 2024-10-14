/**
 * orders 자바스크립트 작성 예정
 */

window.addEventListener("load", onload);

const url = "/240930subKingProject/api/v1/orders"



let container;
function onload(e) {
	console.log("페이지가 로드되었습니다.");
	container = document.querySelector(".section-container");

	loadOrdersData()
		.then((ordersArr) => {
			ordersArr.map((orders) => {
				return createOrders(orders);
			}).forEach((elem) => {
				container.append(elem);
			})
		});
}
// menu_id (custom_menu_id)에 맞는 재료 이름과 숫자 목록을 불러와서 json 배열로 반환
function loadIgListData(menu_id) {
	const igUrl = "/240930subKingProject/api/v1/ingredients/";
	return fetch(igUrl + menu_id, { method: "put" })
		.then((resp) => resp.json())
}

// orders 테이블 데이터 get으로 가져와서 json 배열로 반환
function loadOrdersData() {
	return fetch(url, { method: "get" })
		.then((resp) => resp.json())
}

// orderId에 맞는 json 정보를 parsing해서 menu 객체배열(menuArr)로 반환
function loadMenuData(orderId) {
	const menuUrl = "/240930subKingProject/api/v1/menu/"

	return fetch(menuUrl + orderId, { method: "get" })
		.then((resp) => resp.json())
}

// orders를 1개의 카드 모양으로 만든다.
function createOrders(orders) {
	console.log("카드가 생성되었습니다.");

	let template = document.getElementById('order-template');
	let clone = document.importNode(template.content, true);

	let milliseconds = orders.order_date;
	let date = parseDateToString(milliseconds);

	clone.querySelector(".content > p:nth-child(2)").innerText = "주문일시: " + date;
	clone.querySelector(".content > p:nth-child(3)").innerText = "주문상태: " + orders.order_state;
	clone.querySelector(".content > p:nth-child(4)").innerText = "총 가격: " + orders.order_price + "원";

	let btnOrderDetail = clone.querySelector(".order-details");
	let dialog = clone.querySelector(".order-dialog");

	// 장바구니 버튼 생성
	const ToCart = document.createElement('button');
	ToCart.className = 'btn-tocart';
	ToCart.textContent = '장바구니로 이동';
	ToCart.addEventListener("click", (e) => {
			window.location.href = 'http://localhost:8080/240930subKingProject/custom/cart';
		});

	// 닫기 버튼 생성
	const Close = document.createElement('button');
	Close.className = 'btn-close';
	Close.textContent = '닫기'
	Close.addEventListener("click", (e) => {
			dialog.close();
		});


	// 주문 상세 버튼에 dialog를 출력하는 이벤트 설정
	btnOrderDetail.addEventListener("click", (e) => {
		dialog.showModal();
	});

	// 닫기 버튼 설정
	let btnClose = clone.querySelector(".btn-close");
	btnClose.addEventListener("click", (e) => {
		dialog.close();
	});

	// 장바구니로 이동하는 버튼
	let btnTocart = clone.querySelector(".btn-tocart");
	btnTocart.addEventListener("click", (e) => {
		window.location.href = 'http://localhost:8080/240930subKingProject/custom/cart';
	});

	let orderId = orders.order_id;

	loadMenuData(orderId)
		.then((menuArr) => {
			menuArr.map((menus) => {
				return createMenus(menus, dialog);
			}).forEach((elem) => {
				dialog.append(elem);
				dialog.append(ToCart);
				dialog.append(Close);
			})
		});

	return clone;
}

function createMenus(menus, dialog) {

	let menuTemplate = document.getElementById('menu-template');
	let menuClone = document.importNode(menuTemplate.content, true);
	let igList = menuClone.querySelector(".ingredients-list");

	// 닫기 버튼
	//	let btnClose = menuClone.querySelector(".btn-close");
	//	btnClose.addEventListener("click", (e) => {
	//		dialog.close();
	//	});

	// 복사 버튼 
	let copyBtn = menuClone.querySelector(".btn-copy");
	copyBtn.addEventListener("click", (e) => {

		const menucopyUrl = "/240930subKingProject/api/v1/menucopy/";
		fetch(menucopyUrl + menus.menu_id, { method: "post" })
			.then((resp) => resp.json())
			.then((result) => {
				if (result === 1) {
					alert("장바구니에 담았습니다.");
				} else {
					alert("복사에 실패했습니다.");
				}
			})
			.catch((error) => {
				alert("복사에 실패했습니다.");
				console.error('Error:', error);
			});
	});

	menuClone.querySelector(".menu-name").innerText = menus.menu_name;
	menuClone.querySelector(".menu-kcal").innerText = menus.menu_all_kcal + "kcal";
	menuClone.querySelector(".menu-price").innerText = menus.menu_price + "원";

	// 지료 목룍
	let bread = menuClone.querySelector(".ingredients-bread");
	let vegetable = menuClone.querySelector(".ingredients-vegetable");
	let patty = menuClone.querySelector(".ingredients-patty");
	let cheeze = menuClone.querySelector(".ingredients-cheeze");
	let sauce = menuClone.querySelector(".ingredients-sauce");


	loadIgListData(menus.menu_id)
		.then((igArr) => {
			igArr.forEach((elem) => {
				//				let igName = document.createElement('li');
				//				igName.textContent = elem.ig_name + ": " + elem.custom_count + "개";
				//				igList.appendChild(igName);

				if (elem.ig_category == "빵") {
					let igbread = document.createElement('li');
					igbread.textContent = elem.ig_name + "(" + elem.custom_count + ")";
					igbread.classList.add("ig-li2");
					bread.appendChild(igbread);
				} else if (elem.ig_category == "야채") {
					let igvegetable = document.createElement('li');
					igvegetable.textContent = elem.ig_name + "(" + elem.custom_count + ")";
					igvegetable.classList.add("ig-li2");
					vegetable.appendChild(igvegetable);
				} else if (elem.ig_category == "패티") {
					let igpatty = document.createElement('li');
					igpatty.textContent = elem.ig_name + "(" + elem.custom_count + ")";
					igpatty.classList.add("ig-li2");
					patty.appendChild(igpatty);
				} else if (elem.ig_category == "치즈") {
					let igcheeze = document.createElement('li');
					igcheeze.textContent = elem.ig_name + "(" + elem.custom_count + ")";
					igcheeze.classList.add("ig-li2");
					cheeze.appendChild(igcheeze);
				} else if (elem.ig_category == "소스") {
					let igsauce = document.createElement('li');
					igsauce.textContent = elem.ig_name + "(" + elem.custom_count + ")";
					igsauce.classList.add("ig-li2");
					sauce.appendChild(igsauce);
				}
			})
		});
	return menuClone;
}

function parseDateToString(milliseconds) {
	let date = new Date(milliseconds);

	// 년, 월, 일, 시각 추출
	let year = date.getFullYear();
	let month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
	let day = date.getDate();
	let hours = date.getHours();
	let minutes = date.getMinutes();
	let seconds = date.getSeconds();

	let dateString = `${year}년 ${month}월 ${day}일 ${hours}시 ${minutes}분 ${seconds}초`;

	return dateString;
}