
const kcalInput = document.querySelector(".kcal_input");
const kcalBtn = document.querySelector(".kcal_button");
let orderBtn = document.querySelector(".order-button");
let cartBtn = document.querySelector(".cart-button");


kcalBtn.addEventListener("click", function() {
	let arr = [];
	let kcal = kcalInput.value;

	if (kcal >= 700 ) {
		if (kcal < 1500) {
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
							<img alt="상품이름" src="../static/image/img.png">
						</div> 
						<strong class="tit">커스텀 버거</strong> 
						<span class="text">${igname}</span>
						<span class="eng">${price} 원</span> 
						<span class="cal">${cal}kcal</span> <input
						type="button" class="plus" value="+"> 
						<label class="counts">0</label> 
						<input type="button" class="minus" value="-">
					</li>						
					`;
					}
					list.innerHTML = menu;
					plused();
					minused();

					cartBtn.addEventListener("click", async function() {
						let counts = document.querySelectorAll('.counts');
						let ig_nods = [];

						for (let i = 0; i < counts.length; i++) {
							if (counts[i].innerText > 0) {
								for (let n = 0; n < counts[i].innerText; n++) {
									let ig_nos = [];
									for (let j = 0; j < arr[i].length; j++) {
										ig_nos.push(arr[i][j].ig_no);
									}
									ig_nods.push(ig_nos);
								}
							}
						}

						let url = "/240930subKingProject/api/v1/cart";

						for (let i = 0; i < ig_nods.length; i++) {
							let ig_nos = [];
							for (let j = 0; j < ig_nods[i].length; j++) {
								ig_nos.push(ig_nods[i][j]);
							}

							try {
								let response = await fetch(url, {
									method: "POST",
									headers: {
										"Content-Type": "application/json",
									},
									body: JSON.stringify(ig_nos),
								});

								// 응답 처리 (필요시)
								console.log(await response.json());
							} catch (error) {
								console.error('Error:', error);
							}

						}
						window.location.href = "http://localhost:8080/240930subKingProject/custom/kcal";
					});
					orderBtn.addEventListener("click", async function() {
						let counts = document.querySelectorAll('.counts');
						let ig_nods = [];
						for (let i = 0; i < counts.length; i++) {
							if (counts[i].innerText > 0) {
								for (let n = 0; n < counts[i].innerText; n++) {
									let ig_nos = [];
									for (let j = 0; j < arr[i].length; j++) {
										ig_nos.push(arr[i][j].ig_no);
									}
									ig_nods.push(ig_nos);
								}
							}
						}

						let url = "/240930subKingProject/api/v1/cart";

						for (let i = 0; i < ig_nods.length; i++) {
							let ig_nos = [];
							for (let j = 0; j < ig_nods[i].length; j++) {
								ig_nos.push(ig_nods[i][j]);
							}

							try {
								let response = await fetch(url, {
									method: "POST",
									headers: {
										"Content-Type": "application/json",
									},
									body: JSON.stringify(ig_nos),
								});

								// 응답 처리 (필요시)
								console.log(await response.json());
							} catch (error) {
								console.error('Error:', error);
							}

						}
						window.location.href = "http://localhost:8080/240930subKingProject/custom/cart";

					});


					//			cartBtn.addEventListener("click", function() {
					//				let counts = document.querySelectorAll('.counts');
					//				let ig_nods = [];
					//				for (let i = 0; i < counts.length; i++) {
					//					if (counts[i].innerText > 0) {
					//						for (let n = 0; n < counts[i].innerText; n++) {
					//							let ig_nos = [];
					//							for (let j = 0; j < arr[i].length; j++) {
					//								ig_nos.push(arr[i][j].ig_no);
					//							}
					//							ig_nods.push(ig_nos);
					//						}
					//					}
					//
					//				}
					//				let url = "/240930subKingProject/api/v1/cart";
					//				for (let i = 0; i < ig_nods.length; i++) {
					//					let ig_nos = [];
					//					for (let j = 0; j < ig_nods[i].length; j++) {
					//						ig_nos.push(ig_nods[i][j]);
					//					}
					//					
					//					fetch(url, {
					//						method: "POST",
					//						headers: {
					//							"Content-Type": "application/json",
					//						},
					//						body: JSON.stringify(ig_nos),
					//					})
					//					//							.then((resp) => window.location.href = "http://localhost:8080/240930subKingProject/custom/kcal");
					//					console.log(ig_nos);
					//				}
					//			})
				});
		}else {
			alert("칼로리를 낮춰주세요.");
		}
	} else {
		alert("칼로리를 높혀주세요.");
	}
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
