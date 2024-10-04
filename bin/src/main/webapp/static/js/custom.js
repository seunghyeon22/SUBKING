const igName = document.querySelector(".ig_name");
const igPrice = document.querySelector(".ig_price");
const igKcal = document.querySelector(".ig_kcal");


loadData();


function loadData() {
	loadCustomData().then((igArr) => {
		console.log(igArr);
		const list = document.querySelector(".list");
		let tableStr = ``
		for (let i = 0; i < igArr.length; i++) {
			//<input type="hidden" class="ig_id" value = "${igArr[i].ig_no}">	
			tableStr += `	
								<li class="bread">
									<div class="img">
										<img alt="재료이름" src="">
									</div> 
									<strong class="tit">${ igArr[i].ig_name }</strong> 
									<span class="eng">${ igArr[i].ig_price }</span>
									<span class="cal">${ igArr[i].ig_kcal }</span>

								</li>
				`;
			console.log(igArr[i].ig_no);
		}
		list.innerHTML = tableStr;

	})
}

function loadCustomData() {
	let url = "/240930subKingProject/api/v1/custom";
	return fetch(url)
		.then((resp) => resp.json());
	console.log(resp);
}



