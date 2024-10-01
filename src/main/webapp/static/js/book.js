
// book.html 파일 다 읽고 나면 발생하는 이벤트 설정
window.addEventListener("load", onload);

let container;
let template;
let dialog;

const url = "/api/v1/book";

// 9월 27일 작성
let lastBookData;
let lastCard;

function onload(e) {
	console.log("페이지가 로드되었습니다.");

	container = document.querySelector(".container");
	template = document.querySelector("#template");

	// 9월 26일 작성
	let btnInsert = document.querySelector(".btn-insert");
	dialog = document.querySelector(".book-dialog");
	
	// 9월 27일 수정
	btnInsert.addEventListener("click", (e) => { 
		// 추가와 업데이트에 같은 폼을 사용하기 위해 분기를 나눠야 한다.		
		// 추가 버튼을 누르면 dialog에 data-mode="insert" 속성을 부여.		
		dialog.dataset.mode = "insert";
		dialog.showModal() 
	})
	
	
	dialog.addEventListener("close", dialogClosed);

	// 9월 25일 작성
	loadBookData()
		.then((dataArr) => {
			// 북 배열을 북 카드 배열로 바꿈
			dataArr.map((book) => {
				return createCard(book);
				// 9월 27일 함수 밖에서도 책 배열을 쓰기 위해 대입 연산
				lastBookData = dataArr;
				// 북 카드 배열의 북 카드를 container에 추가
			}).forEach((elem) => {
				container.append(elem);
			})
		})
}

function dialogClosed(e) {
	let form = dialog.querySelector("form");
	if (dialog.returnValue === "확인") {
		// 9월 27일 수정 및 추가, dialog의 속성 mode가 무엇 인지에 따라 행동이 바뀐다.
		if (dialog.dataset.mode === "insert") {
		postBookData()
			.then((book) => {
				// 배열의 첫번째 원소를 교체
				lastBookData.unshift(book);
				
				return createCard(book);
			}).then((card) => {
				container.prepend(card); // 새로운 요소를 부모 요소의 첫 번째(맨 앞)에 추가합니다.
			});
		} else if (dialog.dataset.mode === "update") {
			updateBookData()
				.then((book) => {
					let index = lastBookData.findIndex((b) => b.bookId == book.bookId);
					
					// index:배열에서 변경을 시작할 위치, 1(배열에서 제거할 요소의 수), book(제거한 요소를 book으로 교체)
					lastBookData.splice(index, 1, book);
					
					return createCard(book);
				}).then((card) => {
					// 요소 교체
					container.replaceChild(card, lastCard);
				});
		}
	}
	// 추가 다이얼로그에 남아있는 form 정보 삭제
	form.reset();
}

// 9월 27일 추가
function updateBookData() {
	let form = dialog.querySelector("form");
	let book = Object.fromEntries(new FormData(form));
	// form에는 bookId가 없으므로 속성값에서 bookId를 가져와야 함
	book.bookId = dialog.dataset.bookId;
	let bookJson = JSON.stringify(book);
	
	return fetch(url, {
		method: "put"
		, body: bookJson
	}).then((resp) => resp.json());
}

function postBookData() {

	let form = dialog.querySelector("form");
	let bookJson = JSON.stringify(Object.fromEntries(new FormData(form)));

	return fetch(url, {
		method: "post"
		, body: bookJson
	}).then((resp) => resp.json());
}
//// ////

// 책 정보 페이지에 그리기
function loadBookData() {
//	let url = "/api/v1/book";

	// url body의 response에 담겨 온 제이슨 정보 
	return fetch(url, { method: "get" })
		// json 정보를 parsing해서 book객체배열(dataArr)로 반환
		.then((resp) => resp.json())
}

function createCard(book) {
	let clone = document.importNode(template.content, true);

	// 속성값에 bookId 설정하기, data-이름="value"
	clone.querySelector(".card").dataset.bookId = book.bookId;

	clone.querySelector(".header").innerText = book.title;
	// 클래스 book의 1번째 자식
	clone.querySelector(".book > p:nth-child(1)").innerText = book.author;
	clone.querySelector(".book > p:nth-child(2)").innerText = book.price;
	clone.querySelector(".book > p:nth-child(3)").innerText = book.publisher;

	clone.querySelector(".btn-update").addEventListener("click", updateDialogOpen)
	clone.querySelector(".btn-delete").addEventListener("click", deleteBook)
	return clone;
}

function updateDialogOpen(e) {
	//	console.log("수정 버튼 이벤트 확인");

	let card = e.target.closest(".card");
	// 수정 되기 전 카드의 참조를 만들어둔다. dialogClosed에서 쓰기 위해
	lastCard = card;
	
	let bookId = card.dataset.bookId;
	// lastBookData: 모든 책 정보가 담긴 배열
	// find는 일치하는 정보가 있으면 바로 그걸 반환한다.(검색 진행 끊고)
	let book = lastBookData.find((book) => book.bookId == bookId);

	// 수정 버튼을 누를 경우 속성 mode = "update"로 바꿈
	dialog.dataset.mode = "update";
	
	dialog.dataset.bookId = bookId;
	
	dialog.querySelector("input[name=title]").value = book.title;
	dialog.querySelector("input[name=author]").value = book.author;
	dialog.querySelector("input[name=publisher]").value = book.publisher;
	dialog.querySelector("input[name=price]").value = book.price;
	
	dialog.showModal();
	
//	card.innerText = "바꾸지롱";
}

function deleteBook(e) {
	//	console.log("삭제 버튼 이벤트 확인")
	
	let card = e.target.closest(".card");

	// card의 속성 bookId를 가져온다.
	let bookId = card.dataset.bookId;
	
	// js에 문제 생기면 개발자 도구 콘솔을 봐라
	let index = lastBookData.findIndex((b) => b.bookId == bookId);				
	// 배열에서 index번호부터 1개만 삭제
	lastBookData.splice(index, 1);
	
	// bookId를 객체로 만들어야 json으로 보낼 수 있다.
	let book = { "bookId": bookId };
	let json = JSON.stringify(book);

	fetch(url, {
		method: "delete"
		, body: json
	}).then((resp) => {
		if (resp.status == 204) {
			card.remove();
		}
	})
}



