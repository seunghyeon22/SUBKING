
let arr = [];
let allPrice = 0;
let address = document.querySelector(".addr");

loadData();


function goPopup() {
	var pop = window.open("http://localhost:8080/240930subKingProject/static/jsp/jusoPopup.jsp", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

function update() {
	goPopup();
}
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2, zipNo) {
	address = document.querySelector(".addr");
	address.innerText = "배달지역 : " + roadAddrPart1 + roadAddrPart2 + addrDetail;
}

function loadData() {

	loadCartData().then((cartArr) => {
		console.log(cartArr);

		for (let i = 0; i < cartArr.length; i++) {
			arr.push(cartArr[i]);
		}
		loadmenu();
//		addressLoad();
		befored();
	})
}

function loadCartData() {
	let url = "/240930subKingProject/api/v1/cart";
	return fetch(url)
		.then((resp) => resp.json());
}
//function addressLoad() {
//	address = document.querySelector(".addr");
//	address.innerText = "배달 지역 :  			" + sessionStorage.getItem("address");
//
//}


function loadmenu() {
	const list = document.querySelector(".list");
	let texttr = ``;
	for (let i = 0; i < arr.length; i++) {
		console.log(arr[i].menu_name);
		if (arr[i].menu_name !== null) {
			texttr += `
		<li>
									<div class="img">
										<img alt="상품이미지" src="../static/image/img.png">
									</div>
									<div class="texts">
										<dl>
										<dd><strong class="tit">${arr[i].menu_name}</strong></dd>
										<dd class ="info"><span>상품정보</span></dd>
										<dd><span>칼로리 :${arr[i].menu_all_kcal}kcal</span></dd>
										<dd><span>가격 : ${arr[i].menu_price}원</span></dd>
										</dl>
										<input type="hidden" class ="menu_id" value="${arr[i].menu_id}">
										<input type="hidden" class ="cart_id"  value="${arr[i].cart_id}">
										<input type ="button" class = "deleteBtn" value="취소">
										
										</div>
								</li>		
		
		`;
		} else {
			texttr = "";
		}
	}
	list.innerHTML = texttr;
	checka();
	menuInfo();
	deleteMenu();
}

function menuInfo() {
	let Info = document.querySelectorAll(".info");
	let menuId = document.querySelectorAll(".menu_id");
	let url = "/240930subKingProject/api/v1/menu"
	for (let i = 0; i < menuId.length; i++) {
		fetch(url, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(menuId[i].value)
		}).then((resp) => resp.json())
			.then((dataArr) => {
				let arr = [];
				let sp = ``;
				for (let i = 0; i < dataArr.length; i++) {
					arr.push(dataArr[i]);
					sp += `${arr[i].ig_name} : ${arr[i].custom_count}개  `;

				}
				Info[i].innerText = sp;
			})
	}
}



const IMP = window.IMP;
IMP.init('imp50777305'); // 본인 가맹점 식별코드로 변경


function requestPay() {
	IMP.request_pay({
		pg: 'kakaopay', // PG사 선택 (예: KG이니시스)
		pay_method: 'card', // 결제 수단 (예: 카드)
		merchant_uid: 'merchant_' + new Date().getTime(), // 고유한 주문번호 생성
		name: '결제테스트', // 상품명
		amount: allPrice, // 결제 금액
		buyer_email: 'iamport@siot.do', // 구매자 이메일
		buyer_name: '테스트', // 구매자 이름
		buyer_tel: '010-1234-1234', // 구매자 전화번호
		buyer_addr:address// 구매자 주소
		// ... 기타 필요한 파라미터 추가
	}, function(rsp) {
		if (rsp.success) {
			// 결제 성공 처리
			console.log('결제 성공:', rsp);
			let checkigno = [];
			let checked = document.querySelectorAll(".check");
			let menuId = document.querySelectorAll(".menu_id");
			let user_id = sessionStorage.getItem("user_id");
			for (let i = 0; i < menuId.length; i++) {
				checkigno.push(menuId[i].value);
			}
			let data = {
				menu_ids : checkigno,
				price : allPrice,
				address : address
			};
			let url = "/240930subKingProject/api/v1/orders"
			fetch((url), {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(data)
			}).then((resp) => resp.json());

			let urls = "/240930subKingProject/api/v1/cart"
			fetch((urls), {
				method: "DELETE",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(checkigno)
			}).then((resp) => resp.json());

			window.location.href = "http://localhost:8080/240930subKingProject/custom/orders";

		} else {
			// 결제 실패 처리
			console.log('결제 실패:', rsp);
			alert("주문이 완료되지 않았습니다.")
		}
	});
}
function checka() {
	let priceLbl = document.querySelector(".CartallPrice");
	//	let checked = document.querySelectorAll(".check");
	allPrice = 0;
	for (let i = 0; i < arr.length; i++) {
		allPrice += arr[i].menu_price;
	}
	priceLbl.innerText = "최종 결제 금액 : " + allPrice + "원";

	//	for (let i = 0; i < checked.length; i++) {
	//		checked[i].addEventListener("click", function() {
	//			allPrice = 0;
	//			for (let j = 0; j < checked.length; j++) {
	//				if (checked[j].checked) {
	//					allPrice += arr[j].menu_price;
	//				}
	//			}
	//			console.log(allPrice);
	//			priceLbl.innerText = "주문 금액 : " + allPrice + "원";
	//		})
	//
	//	}

}


function befored() {
	let before = document.querySelector(".before");
	before.addEventListener("click", function() {
		window.location.href = "http://localhost:8080/240930subKingProject/custom/custom";
	})
}

function deleteMenu() {
	let deleteBtn = document.querySelectorAll(".deleteBtn");
	let menu_ids = document.querySelectorAll(".menu_id");
	let cart_id = document.querySelectorAll(".cart_id");
	let checkigno = [];
	for (let i = 0; i < deleteBtn.length; i++) {
		deleteBtn[i].addEventListener("click", function() {
			checkigno.push(menu_ids[i].value);

			fetch("/240930subKingProject/api/v1/cart", {
				method: "DELETE",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(checkigno)
			}).then((resp) => resp.json()).then((data) => {
				alert("주문상품이 취소되었습니다.")
				window.location.href = "http://localhost:8080/240930subKingProject/custom/cart";

			})
		})
	}
}




//
//const axios = require('axios');
//
//const getAccessToken = () => {
//    try {
//        const response =  axios.post('https://api.iamport.kr/users/getToken', {
//            imp_key: '1363852651607640',
//            imp_secret: 'fb493bdc58fe01c79e12934fa674ec55371a7788e4f165c48087eaa33b6804361c7d5094515b4a0d',
//        });
//        const { access_token } = response.data.response;
//        console.log('Access Token:', access_token);
//        return access_token;
//    } catch (error) {
//        console.error('Error getting access token:', error.message);
//    }
//};
//
//// 호출 예시
//const accessToken = getAccessToken();