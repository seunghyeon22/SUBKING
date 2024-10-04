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
		})

}

// orders 테이블 데이터 get으로 가져와서 json 배열로 반환
function loadOrdersData() {
	return fetch(url, { method: "get" })
		.then((resp) => resp.json())
}

// orders를 1개의 카드 모양으로 만든다.
function createOrders(orders) {
	console.log("카드가 생성되었습니다.");
	
	let template = document.getElementById('order-template');
	let clone = document.importNode(template.content, true);
//	let content = clone.querySelector(".content")
		
	let milliseconds = orders.order_date;
	let date = parseDateToString(milliseconds);
	
	clone.querySelector(".content > p:nth-child(2)").innerText = "주문일시: " + date;
	clone.querySelector(".content > p:nth-child(3)").innerText = "주문상태: " + orders.order_state;
	clone.querySelector(".content > p:nth-child(4)").innerText = "총 가격: " + orders.order_price + "원";

	let btnOrderDetail = clone.querySelector(".order-details");
	let dialog = clone.querySelector(".order-dialog");
	let btnClose = clone.querySelector(".btn-close");
	//	let btnSameMenu = container.querySelector(".same-menu");
	//	btnSameMenu.addEventListener("click", (e) => {
	//		
	//	})

	if (btnOrderDetail && dialog && btnClose) {
		btnOrderDetail.addEventListener("click", (e) => {
			dialog.showModal();
		});
		btnClose.addEventListener("click", (e) => {
			dialog.close();
		})
	} else {
		console.error("btnOrderDetail 또는 dialog 요소를 찾을 수 없습니다.");
	}

	return clone;
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